package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.AdapterInitException;
import com.imjojo.rekognition.adapter.FaceAdapter;
import com.imjojo.rekognition.api.AbstractRekognitionAPI;
import com.imjojo.rekognition.http.model.HttpParameter;
import com.imjojo.rekognition.http.model.RekognitionAPIException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceAdd extends AbstractRekognitionAPI {
  
  private static final Logger logger = LogManager.getLogger(FaceAdd.class);
  
  private enum FaceAddJobs {
    FaceAdd {
      @Override
      public String getValue() {
        return "face_add";
      }
    },
    Aggressive {
      @Override
      public String getValue() {
        return "aggressive";
      }
    },
    NoDetect {
      @Override
      public String getValue() {
        return "nodetect";
      }
    };

    public abstract String getValue();
  }

  public FaceAdd(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public FaceAdapter getResponse(String pictureUrl, boolean aggessive, boolean nodetect, 
          String nameSpace, String userId, String tag) throws RekognitionAPIException, IOException {
    List<HttpParameter> params = generateParameters(pictureUrl, aggessive, nodetect, nameSpace, userId, tag, null);
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  public FaceAdapter getResponse(File localPictureFile, boolean aggessive, boolean nodetect, 
          String nameSpace, String userId, String tag) throws RekognitionAPIException, IOException {
    List<HttpParameter> params = generateParameters(null, aggessive, nodetect, nameSpace, userId, tag, localPictureFile);
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  private List<HttpParameter> generateParameters(String pictureUrl, boolean aggessive, 
          boolean nodetect, String nameSpace, String userId, String tag, File localPictureFile) throws IOException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    HttpParameter jobParam = new HttpParameter("jobs", getJobsString(aggessive, nodetect));
    params.add(jobParam);
    if (pictureUrl != null) {
      params.add(new HttpParameter("urls", pictureUrl));
    }
    if (nameSpace != null) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (userId != null) {
      params.add(new HttpParameter("user_id", userId));
    }
    if (tag != null) {
      params.add(new HttpParameter("tag", tag));
    }
    if (localPictureFile != null) {
      params.add(new HttpParameter("base64", new HttpParameter.Base64Field(localPictureFile)));
    }
    return params;
  }
  
  private String getJobsString (boolean aggessive, boolean nodetect) {
    List<FaceAddJobs> jobs = new ArrayList<FaceAddJobs>();
    jobs.add(FaceAddJobs.FaceAdd);
    if (aggessive) {
      jobs.add(FaceAddJobs.Aggressive);
    }
    if (nodetect) {
      jobs.add(FaceAddJobs.NoDetect);
    }
    return concatJobs(jobs);
  }
  
  private String concatJobs (List<FaceAddJobs> jobs) {
    StringBuilder sb = new StringBuilder();
    for (FaceAddJobs job : jobs) {
      sb.append(job.getValue()).append("_");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }
  
}
