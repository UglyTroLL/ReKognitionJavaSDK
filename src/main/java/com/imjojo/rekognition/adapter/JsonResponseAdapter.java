package com.imjojo.rekognition.adapter;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class JsonResponseAdapter implements IRekognitionResponseAdapter {
  
  JSONObject rootJson;
  
  String responseStr;
  
  JSONObject usageJsonObject;
  
  Integer quota = null;
  String status = null;
  String apiId = null;
  
  private static final Logger logger = LogManager.getLogger(JsonResponseAdapter.class.getName());

  @Override
  public void setResponseString(String responseStr) throws AdapterInitException {
    if (responseStr == null || responseStr.isEmpty()) {
      throw new AdapterInitException("Empty/Null response string provided");
    }
    try {
      this.responseStr = responseStr;
      this.rootJson = JSONObject.fromObject(responseStr);
    } catch (JSONException ex) {
      logger.error("Cannot init json object from " + responseStr, ex);
      throw new AdapterInitException("Cannot init json object from " + responseStr);
    }
  }

  /**
   * @return the rootJson
   */
  public JSONObject getJsonObject() {
    return rootJson;
  }

  /**
   * @return the responseStr
   */
  public String getResponseStr() {
    return responseStr;
  }
  
  public Integer getQuota() {
    if (quota == null && this.rootJson.has("usage")) {
      if (this.rootJson.getJSONObject("usage").has("quota")) {
        this.quota = this.rootJson.getJSONObject("usage").getInt("quota");
      }
    }
    return this.quota;
  }
  
  public String getStatus() {
    if (status == null && this.rootJson.has("usage")) {
      if (this.rootJson.getJSONObject("usage").has("status")) {
        this.status = this.rootJson.getJSONObject("usage").getString("status");
      }
    }
    return this.status;
  }
  
  public String getApiId() {
    if (apiId == null && this.rootJson.has("usage")) {
      if (this.rootJson.getJSONObject("usage").has("api_id")) {
        this.apiId = this.rootJson.getJSONObject("usage").getString("api_id");
      }
    }
    return this.apiId;
  }
}
