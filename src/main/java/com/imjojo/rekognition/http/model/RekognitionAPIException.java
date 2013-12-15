package com.imjojo.rekognition.http.model;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class RekognitionAPIException extends Exception {

  private int httpResponseStatusCode = 0;

  public RekognitionAPIException(String msg) {
    super(msg);
  }

  public RekognitionAPIException(Exception cause) {
    super(cause);
  }

  public RekognitionAPIException(String msg, int statusCode) {
    super(msg);
    this.httpResponseStatusCode = statusCode;
  }

  public RekognitionAPIException(String msg, Exception cause, int statusCode) {
    super(msg, cause);
    this.httpResponseStatusCode = statusCode;

  }

  /**
   * @return the httpResponseStatusCode
   */
  public int getHttpResponseStatusCode() {
    return httpResponseStatusCode;
  }

}
