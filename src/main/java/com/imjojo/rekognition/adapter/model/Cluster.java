package com.imjojo.rekognition.adapter.model;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class Cluster implements IRekognitionModel{
  
  private final List<Long> imgIndex = new ArrayList<Long>();
  
  private JSONObject clusterObj;

  @Override
  public void loadDataFromJSONObject(JSONObject clusterObj) {
    this.clusterObj = clusterObj;
  }

  public String getTag() throws FieldNotFoundException {
    if (this.clusterObj.has("tag")) {
      return this.clusterObj.getString("tag");
    } else {
      throw new FieldNotFoundException("tag");
    }
  }

  public List<Long> getImgIndex() throws FieldNotFoundException {
    if (this.imgIndex.isEmpty() && this.clusterObj.has("img_index")) {
      JSONArray indiceArray = this.clusterObj.getJSONArray("img_index");
      for (int i = 0 ; i < indiceArray.size() ; i ++) {
        this.imgIndex.add(indiceArray.getLong(i));
      }
    }
    if (this.imgIndex.isEmpty()) {
      throw new FieldNotFoundException("img_index");
    } else {
      return this.imgIndex;
    }
  }
  

}
