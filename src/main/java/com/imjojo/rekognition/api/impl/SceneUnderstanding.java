package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.AdapterInitException;
import com.imjojo.rekognition.adapter.SceneUnderstandingAdapter;
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
public class SceneUnderstanding extends AbstractRekognitionAPI {
  
  private static final Logger logger = Logger.getLogger(SceneUnderstanding.class);

  public SceneUnderstanding(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public SceneUnderstandingAdapter getResposne (String url) throws RekognitionAPIException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    if (StringUtils.isBlank(url)) {
      throw new RekognitionAPIException("You have to provide an url for scene understanding");
    }
    params.add(new HttpParameter("jobs", "scene"));
    params.add(new HttpParameter("urls", url));
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new SceneUnderstandingAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
}
