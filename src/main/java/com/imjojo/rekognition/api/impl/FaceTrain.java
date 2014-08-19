package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.AdapterInitException;
import com.imjojo.rekognition.adapter.JsonResponseAdapter;
import com.imjojo.rekognition.api.AbstractRekognitionAPI;
import com.imjojo.rekognition.http.model.HttpParameter;
import com.imjojo.rekognition.http.model.RekognitionAPIException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceTrain extends AbstractRekognitionAPI {
  
  private static final Logger logger = Logger.getLogger(FaceTrain.class);

  public FaceTrain(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public JsonResponseAdapter trainWithCertainTags(String nameSpace, String userId, String... tags) throws RekognitionAPIException {
    if (tags == null || tags.length == 0) {
      throw new RekognitionAPIException("Tags is empty, please provide tags if you are doing sycn face train");
    }
    String concatTags = concatTags(tags);
    List<HttpParameter> params = generateFaceTrainParameters("face_train_sync", nameSpace, userId, concatTags);
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new JsonResponseAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  public JsonResponseAdapter trainWithAllTags(String nameSpace, String userId) throws RekognitionAPIException {
    List<HttpParameter> params = generateFaceTrainParameters("face_train", nameSpace, userId, null);
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new JsonResponseAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  private List<HttpParameter> generateFaceTrainParameters (String job, String nameSpace, String userId, String tags) {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    params.add(new HttpParameter("jobs", job));
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (StringUtils.isNotBlank(userId)) {
      params.add(new HttpParameter("user_id", userId));
    }
    if (StringUtils.isNotBlank(tags)) {
      params.add(new HttpParameter("tags", tags));
    }
    return params;
  }
  
  private String concatTags (String... tags) {
    StringBuilder sb = new StringBuilder();
    for (String tag : tags) {
      sb.append(tag).append(";");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }
  
}
