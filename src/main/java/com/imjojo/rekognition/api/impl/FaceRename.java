package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.AdapterInitException;
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
public class FaceRename extends AbstractRekognitionAPI {
  
  private static final Logger logger = LogManager.getLogger(FaceRename.class);

  public FaceRename(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public JsonResponseAdapter getResponse (String existingTagName, String newTagName, String nameSpace, 
          String userId, String imageIndex) throws RekognitionAPIException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    if (StringUtils.isBlank(existingTagName)) {
      throw new RekognitionAPIException("You have to provide the existing tag name in rename API");
    }
    if (StringUtils.isBlank(newTagName)) {
      throw new RekognitionAPIException("You have to provide the new tag name in rename API");
    }
    params.add(new HttpParameter("jobs", "face_rename"));
    params.add(new HttpParameter("tag", existingTagName));
    params.add(new HttpParameter("new_tag", newTagName));
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (StringUtils.isNotBlank(userId)) {
      params.add(new HttpParameter("user_id", userId));
    }
    if (StringUtils.isNotBlank(imageIndex)) {
      params.add(new HttpParameter("img_index", imageIndex));
    }
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new JsonResponseAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
}
