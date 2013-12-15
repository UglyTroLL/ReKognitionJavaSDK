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
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceRecognize extends AbstractRekognitionAPI {
  
  private static final Logger logger = LogManager.getLogger(FaceRecognize.class);

  public FaceRecognize(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }
  
  public FaceAdapter recognizeFaceWithDetect(List<FaceDetect.FaceDetectJobs> detectJobs, String url, 
          String nameSpace, String userId, Integer numberReturn, List<String> tags) throws RekognitionAPIException {
    try {
      List<HttpParameter> params = generateParams(detectJobs, url, null, nameSpace, userId, numberReturn, tags, true);
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  public FaceAdapter recognizeFaceWithDetect(List<FaceDetect.FaceDetectJobs> detectJobs, File localFile, 
          String nameSpace, String userId, Integer numberReturn, List<String> tags) throws RekognitionAPIException, IOException {
    try {
      List<HttpParameter> params = generateParams(detectJobs, null, localFile, nameSpace, userId, numberReturn, tags, true);
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  public FaceAdapter recognizeFaceWithoutDetect(String url, String nameSpace, String userId, 
          Integer numberReturn, List<String> tags) throws RekognitionAPIException, IOException {
    try {
      List<HttpParameter> params = generateParams(null, url, null, nameSpace, userId, numberReturn, tags, false);
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  public FaceAdapter recognizeFaceWithoutDetect(File localFile, String nameSpace, String userId, 
          Integer numberReturn, List<String> tags) throws RekognitionAPIException, IOException {
    try {
      List<HttpParameter> params = generateParams(null, null, localFile, nameSpace, userId, numberReturn, tags, false);
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }
  
  private List<HttpParameter> generateParams (List<FaceDetect.FaceDetectJobs> detectJobs, String url, 
          File localFile, String nameSpace, String userId, Integer numberReturn, List<String> tags, boolean needDetect) throws IOException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    if (needDetect) {
      String detectJobsString = "";
      if (detectJobs != null && !detectJobs.isEmpty()) {
        detectJobsString = "_" + concatJobs(detectJobs);
      }
      params.add(new HttpParameter("jobs", "face_recognize" + detectJobsString));
    } else {
      params.add(new HttpParameter("jobs", "face_recognize_nodetect"));
    }
    if (StringUtils.isNotBlank(url)) {
      params.add(new HttpParameter("urls", url));
    }
    if (localFile != null) {
      params.add(new HttpParameter("base64", new HttpParameter.Base64Field(localFile)));
    }
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (StringUtils.isNotBlank(userId)) {
      params.add(new HttpParameter("user_id", userId));
    }
    if (numberReturn != null) {
      params.add(new HttpParameter("num_return", numberReturn.toString()));
    }
    if (tags != null && !tags.isEmpty()) {
      params.add(new HttpParameter("tags", concatTags(tags)));
    }
    return params;
  }
  
  private String concatJobs (List<FaceDetect.FaceDetectJobs> jobs) {
    StringBuilder sb = new StringBuilder();
    for (FaceDetect.FaceDetectJobs job : jobs) {
      sb.append(job.getValue()).append("_");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }
  
  private String concatTags (List<String> tags) {
    return StringUtils.join(tags, ";");
  }
  
}
