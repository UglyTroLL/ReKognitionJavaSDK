package com.imjojo.rekognition.api;

import com.imjojo.rekognition.adapter.AdapterInitException;
import com.imjojo.rekognition.adapter.IRekognitionResponseAdapter;
import com.imjojo.rekognition.http.HttpManager;
import com.imjojo.rekognition.http.model.HttpParameter;
import com.imjojo.rekognition.http.model.RekognitionAPIException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public abstract class AbstractRekognitionAPI {
  
  protected static final String REKO_API_HOST_NAME = "http://rekognition.com/func/api/";
  
  private final List<HttpParameter> keyAndSecret;
  
  private static final String API_KEY_NAME = "api_key";
  private static final String API_SECRET_NAME = "api_secret";
  
  protected enum HttpMethod {
    GET, POST
  }
  
  private final HttpManager httpClient;
  
  public AbstractRekognitionAPI(String apiKey, String apiSecret) {
    this.httpClient = HttpManager.getInstance();
    keyAndSecret = new ArrayList<HttpParameter>();
    HttpParameter apiKeyParam = new HttpParameter(API_KEY_NAME, apiKey);
    HttpParameter apiSecretParam = new HttpParameter(API_SECRET_NAME, apiSecret);
    keyAndSecret.add(apiKeyParam);
    keyAndSecret.add(apiSecretParam);
  }
  
  protected <T extends IRekognitionResponseAdapter> T perform(String url, 
          Collection<HttpParameter> params, HttpMethod method, T adapter) 
          throws RekognitionAPIException, AdapterInitException {
    params.addAll(this.keyAndSecret);
    if (method == HttpMethod.GET) {
      String response = this.httpClient.get(url, params);
      adapter.setResponseString(response);
    } else {
      String response = this.httpClient.post(url, params);
      adapter.setResponseString(response);
    }
    return adapter;
  }
  
}
