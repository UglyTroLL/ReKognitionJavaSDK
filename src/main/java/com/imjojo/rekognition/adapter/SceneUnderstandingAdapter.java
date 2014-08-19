package com.imjojo.rekognition.adapter;

import com.imjojo.rekognition.adapter.model.Scene;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class SceneUnderstandingAdapter extends JsonResponseAdapter {
  
  private static final Logger logger = Logger.getLogger(SceneUnderstandingAdapter.class);
  
  private JSONArray sceneArray = null;
  private List<Scene> scenes = null;

  @Override
  public void setResponseString(String responseStr) throws AdapterInitException {
    super.setResponseString(responseStr);
    if (this.getJsonObject().has("scene_understanding")) {
      this.sceneArray = this.getJsonObject().getJSONArray("scene_understanding");
    } else {
      throw new AdapterInitException("Cannot init SceneUnderstandingAdapter since scene_understanding is missing from the resposne json");
    }
  }
  
  public List<Scene> getScenes () {
    if (this.scenes == null) {
      this.scenes = new ArrayList<Scene>();
      for (int i = 0 ; i < this.sceneArray.size() ; i ++) {
        JSONObject sceneObj = this.sceneArray.getJSONObject(i);
        Scene scene = new Scene();
        scene.loadDataFromJSONObject(sceneObj);
        this.scenes.add(scene);
      }
    }
    return this.scenes;
  }

}
