package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.AdapterInitException;
import com.imjojo.rekognition.adapter.FaceAdapter;
import com.imjojo.rekognition.adapter.FaceVisualizationAdapter;
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
public class FaceVisualize extends AbstractRekognitionAPI {
  
  private static final Logger logger = LogManager.getLogger(FaceVisualize.class);

  public FaceVisualize(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public FaceVisualizationAdapter getResponse (boolean needDisplayImage, boolean showUntaggedFace, 
          String nameSpace, String userId, List<String> tags, Integer numOfTagsReturn, Integer numOfImageReturnPerTag) throws RekognitionAPIException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    StringBuilder jobsSb = new StringBuilder();
    jobsSb.append("face_visualize");
    if (!needDisplayImage) {
      jobsSb.append("_no_image");
    }
    if (showUntaggedFace) {
      jobsSb.append("_show_default_tag");
    }
    params.add(new HttpParameter("jobs", jobsSb.toString()));
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (StringUtils.isNotBlank(userId)) {
      params.add(new HttpParameter("user_id", userId));
    }
    if (tags != null && !tags.isEmpty()) {
      params.add(new HttpParameter("tags", StringUtils.join(tags, ";")));
    }
    if (numOfTagsReturn != null) {
      params.add(new HttpParameter("num_tag_return", numOfTagsReturn));
    }
    if (numOfImageReturnPerTag != null) {
      params.add(new HttpParameter("num_img_return_pertag", numOfImageReturnPerTag));
    }
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.GET, new FaceVisualizationAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
}
