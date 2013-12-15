package com.imjojo.rekognition.adapter;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public interface IRekognitionResponseAdapter {
  
  void setResponseString(String responseStr) throws AdapterInitException;
  
}
