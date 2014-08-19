package com.imjojo.rekognition.adapter.model;

import net.sf.json.JSONObject;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class Scene implements IRekognitionModel {
  
  private JSONObject sceneObj = null;
  
  private String label = null;
  private Double score = null;

  @Override
  public void loadDataFromJSONObject(JSONObject sceneObj) {
    this.sceneObj = sceneObj;
  }

  public String getLabelOrThrow() throws FieldNotFoundException {
    if (this.label == null) {
      if (this.sceneObj.has("label")) {
        this.label = this.sceneObj.getString("label");
      } else {
        throw new FieldNotFoundException("scene.label");
      }
    }
    return label;
  }
  
  public String getLabel() {
    if (this.label == null) {
      if (this.sceneObj.has("label")) {
        this.label = this.sceneObj.getString("label");
      } else {
        return null;
      }
    }
    return label;
  }

  public Double getScoreOrThrow() throws FieldNotFoundException {
    if (this.score == null) {
      if (this.sceneObj.has("score")) {
        this.score = this.sceneObj.getDouble("score");
      } else {
        throw new FieldNotFoundException("scene.score");
      }
    }
    return score;
  }
  
  public Double getScore() {
    if (this.score == null) {
      if (this.sceneObj.has("score")) {
        this.score = this.sceneObj.getDouble("score");
      } else {
        return null;
      }
    }
    return score;
  }
  
}
