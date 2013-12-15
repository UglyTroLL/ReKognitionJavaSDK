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
public class FaceDetect extends AbstractRekognitionAPI {
  
  private static final Logger logger = LogManager.getLogger(FaceDetect.class);
  
  public static enum FaceDetectJobs {
    Face {
      @Override
      public String getValue() {
        return "face";
      }
    },
    Aggressive {
      @Override
      public String getValue() {
        return "aggressive";
      }
    },
    Part {
      @Override
      public String getValue() {
        return "part";
      }
    },
    PartDetail {
      @Override
      public String getValue() {
        return "part_detail";
      }
    },
    Gender {
      @Override
      public String getValue() {
        return "gender";
      }
    },
    Emotion {
      @Override
      public String getValue() {
        return "emotion";
      }
    },
    Race {
      @Override
      public String getValue() {
        return "race";
      }
    },
    Age {
      @Override
      public String getValue() {
        return "age";
      }
    },
    Glass {
      @Override
      public String getValue() {
        return "glass";
      }
    },
    MouthOpenWide {
      @Override
      public String getValue() {
        return "mouth_open_wide";
      }
    },
    EyeClosed {
      @Override
      public String getValue() {
        return "eye_closed";
      }
    };

    public abstract String getValue();
  }

  public FaceDetect(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public FaceAdapter getResponse(List<FaceDetectJobs> jobs, String pictureUrl) throws RekognitionAPIException {
    if (!jobs.contains(FaceDetectJobs.Face)) {
      jobs.add(FaceDetectJobs.Face);
    }
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    HttpParameter jobParam = new HttpParameter("jobs", concatJobs(jobs));
    //System.out.println("=======Jobs : " + concatJobs(jobs));
    HttpParameter urlParam = new HttpParameter("urls", pictureUrl);
    params.add(jobParam);
    params.add(urlParam);
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  public FaceAdapter getResponse(List<FaceDetectJobs> jobs, File localFile) throws RekognitionAPIException, IOException {
    if (!jobs.contains(FaceDetectJobs.Face)) {
      jobs.add(FaceDetectJobs.Face);
    }
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    HttpParameter jobParam = new HttpParameter("jobs", concatJobs(jobs));
    
    HttpParameter urlParam = new HttpParameter("base64", new HttpParameter.Base64Field(localFile));
    params.add(jobParam);
    params.add(urlParam);
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  public FaceAdapter getResponse(List<FaceDetectJobs> jobs, byte[] imageContent) throws RekognitionAPIException {
    if (!jobs.contains(FaceDetectJobs.Face)) {
      jobs.add(FaceDetectJobs.Face);
    }
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    HttpParameter jobParam = new HttpParameter("jobs", concatJobs(jobs));
    
    HttpParameter urlParam = new HttpParameter("base64", new HttpParameter.Base64Field(imageContent));
    params.add(jobParam);
    params.add(urlParam);
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  private String concatJobs (List<FaceDetectJobs> jobs) {
    StringBuilder sb = new StringBuilder();
    for (FaceDetectJobs job : jobs) {
      sb.append(job.getValue()).append("_");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }
  
}
