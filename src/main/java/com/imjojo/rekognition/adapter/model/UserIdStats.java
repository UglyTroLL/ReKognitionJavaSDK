package com.imjojo.rekognition.adapter.model;

import net.sf.json.JSONObject;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class UserIdStats implements IRekognitionModel {
  
  private JSONObject userIdStatsObj;
  
  private String userId;
  private Integer numberOfTags;
  private Integer numberOfImages;

  @Override
  public void loadDataFromJSONObject(JSONObject userIdStatsObj) {
    this.userIdStatsObj = userIdStatsObj;
  }

  public String getUserIdOrThrow() throws FieldNotFoundException {
    if (this.userId == null) {
      if (this.userIdStatsObj.has("user_id")) {
        this.userId = this.userIdStatsObj.getString("user_id");
      } else {
        throw new FieldNotFoundException("user_id_stats.user_id");
      }
    }
    return userId;
  }
  
  public String getUserId() {
    if (this.userId == null) {
      if (this.userIdStatsObj.has("user_id")) {
        this.userId = this.userIdStatsObj.getString("user_id");
      } else {
        return null;
      }
    }
    return userId;
  }

  public Integer getNumberOfTagsOrThrow() throws FieldNotFoundException {
    if (this.numberOfTags == null) {
      if (this.userIdStatsObj.has("num_tags")) {
        this.numberOfTags = this.userIdStatsObj.getInt("num_tags");
      } else {
        throw new FieldNotFoundException("user_id_stats.num_tags");
      }
    }
    return numberOfTags;
  }
  
  public Integer getNumberOfTags() {
    if (this.numberOfTags == null) {
      if (this.userIdStatsObj.has("num_tags")) {
        this.numberOfTags = this.userIdStatsObj.getInt("num_tags");
      } else {
        return null;
      }
    }
    return numberOfTags;
  }

  public Integer getNumberOfImagesOrThrow() throws FieldNotFoundException {
    if (this.numberOfImages == null) {
      if (this.userIdStatsObj.has("num_img")) {
        this.numberOfImages = this.userIdStatsObj.getInt("num_img");
      } else {
        throw new FieldNotFoundException("user_id_stats.num_img");
      }
    }
    return numberOfImages;
  }
  
  public Integer getNumberOfImages() {
    if (this.numberOfImages == null) {
      if (this.userIdStatsObj.has("num_img")) {
        this.numberOfImages = this.userIdStatsObj.getInt("num_img");
      } else {
        return null;
      }
    }
    return numberOfImages;
  }
  
}
