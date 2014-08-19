package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.AdapterInitException;
import com.imjojo.rekognition.adapter.FaceStatsAdapter;
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
public class FaceStats extends AbstractRekognitionAPI {
  
  private static final Logger logger = Logger.getLogger(FaceStats.class);

  public FaceStats(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public FaceStatsAdapter getNameSpaceStats () throws RekognitionAPIException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    params.add(new HttpParameter("jobs", "face_name_space_stats"));
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceStatsAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  public FaceStatsAdapter getUserIdStats (String nameSpace) throws RekognitionAPIException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    params.add(new HttpParameter("jobs", "face_user_id_stats"));
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceStatsAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
}
