package com.imjojo.rekognition.adapter.model;

import net.sf.json.JSONObject;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public interface IRekognitionModel {
  
  void loadDataFromJSONObject(JSONObject jsonObj) throws FieldNotFoundException;
  
}
