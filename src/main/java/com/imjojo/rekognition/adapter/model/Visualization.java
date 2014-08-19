package com.imjojo.rekognition.adapter.model;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class Visualization implements IRekognitionModel {
  
  private JSONObject visualizationObj;
  
  private String tag = null;
  private String url = null;
  private List<String> index = null;

  @Override
  public void loadDataFromJSONObject(JSONObject visualizationObj) {
    this.visualizationObj = visualizationObj;
  }

  public String getTagOrThrow() throws FieldNotFoundException {
    if (this.tag == null) {
      if (this.visualizationObj.has("tag")) {
        this.tag = this.visualizationObj.getString("tag");
      } else {
        throw new FieldNotFoundException("tag");
      }
    }
    return tag;
  }
  
  public String getTag() {
    if (this.tag == null) {
      if (this.visualizationObj.has("tag")) {
        this.tag = this.visualizationObj.getString("tag");
      } else {
        return null;
      }
    }
    return tag;
  }

  public String getUrlOrThrow() throws FieldNotFoundException {
    if (this.url == null) {
      if (this.visualizationObj.has("url")) {
        this.url = this.visualizationObj.getString("url");
      } else {
        throw new FieldNotFoundException("url");
      }
    }
    return url;
  }
  
  public String getUrl() {
    if (this.url == null) {
      if (this.visualizationObj.has("url")) {
        this.url = this.visualizationObj.getString("url");
      } else {
        return null;
      }
    }
    return url;
  }

  public List<String> getIndexOrThrow() throws FieldNotFoundException {
    if (this.index == null) {
      if (this.visualizationObj.has("index")) {
        JSONArray indexArray = this.visualizationObj.getJSONArray("index");
        this.index = new ArrayList<String>();
        for (int i = 0 ; i < indexArray.size() ; i ++) {
          this.index.add(indexArray.getString(i));
        }
      } else {
        throw new FieldNotFoundException("index");
      }
    } 
    return index;
  }
  
  public List<String> getIndex() {
    if (this.index == null) {
      if (this.visualizationObj.has("index")) {
        JSONArray indexArray = this.visualizationObj.getJSONArray("index");
        if (indexArray != null) {
            this.index = new ArrayList<String>();
            for (int i = 0 ; i < indexArray.size() ; i ++) {
              this.index.add(indexArray.getString(i));
            }
        }
      } else {
        return null;
      }
    } 
    return index;
  }
  
}
