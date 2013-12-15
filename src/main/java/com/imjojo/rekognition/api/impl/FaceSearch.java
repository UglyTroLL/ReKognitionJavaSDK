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
public class FaceSearch extends AbstractRekognitionAPI {

  private static final Logger logger = LogManager.getLogger(FaceSearch.class);

  public FaceSearch(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }

  public FaceAdapter outSearchWithFaceDetect(List<String> tags, List<FaceDetect.FaceDetectJobs> faceDetectJobs,
          String url, String nameSpace, String userId, Integer numberReturn) throws RekognitionAPIException {

    try {
      List<HttpParameter> params = generateParams(faceDetectJobs, url, null, nameSpace, userId, numberReturn, tags, true);
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }

  public FaceAdapter outSearchWithFaceDetect(List<String> tags, List<FaceDetect.FaceDetectJobs> faceDetectJobs,
          File localFile, String nameSpace, String userId, Integer numberReturn) throws RekognitionAPIException {

    try {
      List<HttpParameter> params = generateParams(faceDetectJobs, null, localFile, nameSpace, userId, numberReturn, tags, true);
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.POST, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }

  public FaceAdapter outSearchWithNoFaceDetect(List<String> tags, String url, String nameSpace,
          String userId, Integer numberReturn) throws RekognitionAPIException {

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

  public FaceAdapter outSearchWithNoFaceDetect(List<String> tags, File localFile, String nameSpace,
          String userId, Integer numberReturn) throws RekognitionAPIException {
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
  
  public FaceAdapter innerSearch(String queryTag, String imageIndex, String nameSpace, String userId, Integer numReturn) throws RekognitionAPIException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    params.add(new HttpParameter("jobs", "face_inner_search"));
    if (StringUtils.isBlank(queryTag)) {
      throw new RekognitionAPIException("You need to provide query_tag for inner search");
    }
    params.add(new HttpParameter("query_tag", queryTag));
    if (StringUtils.isBlank(imageIndex)) {
      throw new RekognitionAPIException("You need to provide img_index for inner search");
    }
    params.add(new HttpParameter("img_index", imageIndex));
    if (StringUtils.isNotBlank(nameSpace)) {
      params.add(new HttpParameter("name_space", nameSpace));
    }
    if (StringUtils.isNotBlank(userId)) {
      params.add(new HttpParameter("user_id", userId));
    }
    if (numReturn != null) {
      params.add(new HttpParameter("num_return", numReturn));
    }
    try {
      return this.perform(REKO_API_HOST_NAME, params, HttpMethod.GET, new FaceAdapter());
    } catch (AdapterInitException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }

  private List<HttpParameter> generateParams(List<FaceDetect.FaceDetectJobs> detectJobs, String url,
          File localFile, String nameSpace, String userId, Integer numberReturn, List<String> tags, boolean needDetect) throws IOException {
    List<HttpParameter> params = new ArrayList<HttpParameter>();
    StringBuilder sb = new StringBuilder("face_search");
    if (tags != null && !tags.isEmpty()) {
      sb.append(concatTags(tags));
    }
    if (needDetect) {
      if (detectJobs != null && !detectJobs.isEmpty()) {
        sb.append("_").append(concatJobs(detectJobs));
      }
    } else {
      sb.append("_nodetect");
    }
    params.add(new HttpParameter("jobs", sb.toString()));
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
    return params;
  }

  private String concatJobs(List<FaceDetect.FaceDetectJobs> jobs) {
    StringBuilder sb = new StringBuilder();
    for (FaceDetect.FaceDetectJobs job : jobs) {
      sb.append(job.getValue()).append("_");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  private String concatTags(List<String> tags) {
    return "[" + StringUtils.join(tags, ";") + "]";
  }

}
