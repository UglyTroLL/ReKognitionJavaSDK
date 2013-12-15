package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.AdapterInitException;
import com.imjojo.rekognition.adapter.FaceClusterAdapter;
import com.imjojo.rekognition.adapter.JsonResponseAdapter;
import com.imjojo.rekognition.api.AbstractRekognitionAPI;
import com.imjojo.rekognition.http.model.HttpParameter;
import com.imjojo.rekognition.http.model.RekognitionAPIException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceCrawl extends AbstractRekognitionAPI {
  
  private static final Logger logger = LogManager.getLogger(FaceCrawl.class);

  public FaceCrawl(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public JsonResponseAdapter getResponse (List<Long> idsToCrawl, long fbId, String accessToken, String nameSpace, String userId) throws RekognitionAPIException {
    if (idsToCrawl == null || idsToCrawl.isEmpty()) {
      throw new RekognitionAPIException("Please provide the facebook ids to crawl");
    }
    if (StringUtils.isBlank(accessToken)) {
      throw new RekognitionAPIException("Access Token is needed for crawling facebook pictures");
    }
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    params.add(new HttpParameter("jobs", "face_crawl_" + concatFacebookIds(idsToCrawl)));
    params.add(new HttpParameter("fb_id", fbId));
    params.add(new HttpParameter("access_token", accessToken));
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (StringUtils.isNotBlank(userId)) {
      params.add(new HttpParameter("user_id", userId));
    }
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.GET, new JsonResponseAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  private String concatFacebookIds (List<Long> idsToCrawl) {
    return "[" + StringUtils.join(idsToCrawl, ";") + "]";
  }
  
}
