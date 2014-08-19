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
public class FaceDelete extends AbstractRekognitionAPI {
  
  private static final Logger logger = Logger.getLogger(FaceDelete.class);

  public FaceDelete(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public JsonResponseAdapter getResponse (String nameSpace, String userId, String tag, List<String> imageIndex) throws RekognitionAPIException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    params.add(new HttpParameter("jobs", "face_delete"));
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (StringUtils.isNotBlank(userId)) {
      params.add(new HttpParameter("user_id", userId));
    }
    if (StringUtils.isNotBlank(tag)) {
      params.add(new HttpParameter("tag", tag));
    }
    if (imageIndex != null && !imageIndex.isEmpty()) {
      params.add(new HttpParameter("img_index", StringUtils.join(imageIndex, ";")));
    }
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.GET, new JsonResponseAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
}
