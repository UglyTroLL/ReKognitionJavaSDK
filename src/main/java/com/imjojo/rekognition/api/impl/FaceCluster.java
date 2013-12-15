package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.AdapterInitException;
import com.imjojo.rekognition.adapter.FaceClusterAdapter;
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
public class FaceCluster extends AbstractRekognitionAPI{

  private static final Logger logger = LogManager.getLogger(FaceCluster.class);
  
  public FaceCluster(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public FaceClusterAdapter getResponse(String nameSpace, String userId, Integer aggressiveness) throws RekognitionAPIException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    params.add(new HttpParameter("jobs", "face_cluster"));
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (StringUtils.isNotBlank(userId)) {
      params.add(new HttpParameter("user_id", userId));
    }
    if (aggressiveness != null) {
      if (aggressiveness < 0) {
        aggressiveness = 0;
      } else if (aggressiveness > 100) {
        aggressiveness = 100;
      }
      params.add(new HttpParameter("aggressiveness", aggressiveness.toString()));
    }
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceClusterAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
}
