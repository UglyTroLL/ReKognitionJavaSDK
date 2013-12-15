package com.imjojo.rekognition.http;

import com.imjojo.rekognition.http.model.HttpParameter;
import com.imjojo.rekognition.http.model.RekognitionAPIException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Zhibo Wei (uglytroll@dongxuexidu.com)
 */
public class HttpManager {

  private static HttpManager instance = null;

  private static final Logger logger = LogManager.getLogger(HttpManager.class.getName());

  private static final int MAX_CONNECTION = 150;
  private static final int CONNECTION_TIMEOUT_IN_MS = 2000;
  private static final int SOCKET_TIMEOUT_IN_MS = 2000;
  private static final int OK_RESPONSE_STATUS_CODE = 200;

  private static final String DEFAULT_CHARSET = "UTF-8";

  private final MultiThreadedHttpConnectionManager connectionManager;
  private final HttpClient client;

  public static synchronized HttpManager getInstance() {
    if (instance == null) {
      instance = new HttpManager();
    }
    return instance;
  }

  private HttpManager() {
    connectionManager = new MultiThreadedHttpConnectionManager();
    HttpConnectionManagerParams params = connectionManager.getParams();
    params.setDefaultMaxConnectionsPerHost(MAX_CONNECTION);
    params.setConnectionTimeout(CONNECTION_TIMEOUT_IN_MS);
    params.setSoTimeout(SOCKET_TIMEOUT_IN_MS);

    HttpClientParams clientParams = new HttpClientParams();
    clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
    client = new org.apache.commons.httpclient.HttpClient(clientParams,
            connectionManager);
  }

  public String get(String url, Collection<HttpParameter> params) throws RekognitionAPIException {
    if (params != null && !params.isEmpty()) {
      String encodedParams = encodeParameters(params);
      url = url + "?" + encodedParams;
    }
    GetMethod getmethod = new GetMethod(url);
    return httpRequest(getmethod);
  }

  public String get(String url) throws RekognitionAPIException {
    GetMethod getmethod = new GetMethod(url);
    return httpRequest(getmethod);
  }

  public String post(String url, Collection<HttpParameter> params) throws RekognitionAPIException {
    if (logger.isDebugEnabled()) {
      logger.debug("Got a POST request to " + url);
    }
    PostMethod postMethod = new PostMethod(url);
    for (HttpParameter postParam : params) {
      postMethod.addParameter(postParam.getName(), postParam.getValue());
    }
    HttpMethodParams param = postMethod.getParams();
    param.setContentCharset("UTF-8");
    return httpRequest(postMethod);
  }

  private String httpRequest(HttpMethod method) throws RekognitionAPIException {
    try {
      method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
      try {
        client.executeMethod(method);
      } catch (IOException ex) {
        logger.error("Error when access remote host", ex);
        throw new RekognitionAPIException(ex);
      }
      int responseCode = method.getStatusCode();

      if (responseCode != OK_RESPONSE_STATUS_CODE) {
        throw new RekognitionAPIException("Un-acceptable response code from server", responseCode);
      }

      try {
        String response = method.getResponseBodyAsString();
        if (logger.isDebugEnabled()) {
          logger.debug("Response : " + response);
        }
        return response;
      } catch (IOException ex) {
        logger.error("Error happended when read response from stream", ex);
        throw new RekognitionAPIException(ex);
      }

    } finally {
      method.releaseConnection();
    }

  }

  private String encodeParameters(Collection<HttpParameter> params) {
    StringBuilder buf = new StringBuilder();
    int j = 0;
    for (HttpParameter nvp : params) {
      if (j != 0) {
        buf.append("&");
      }
      j++;
      try {
        buf.append(URLEncoder.encode(nvp.getName(), DEFAULT_CHARSET)).append("=").append(URLEncoder.encode(nvp.getValue(), DEFAULT_CHARSET));
      } catch (UnsupportedEncodingException ex) {
        //System.out.println("Shouldn't come this far");
      }
    }
    return buf.toString();
  }

}
