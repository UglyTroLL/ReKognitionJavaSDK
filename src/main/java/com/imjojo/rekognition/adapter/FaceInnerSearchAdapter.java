package com.imjojo.rekognition.adapter;

import com.imjojo.rekognition.adapter.model.Face;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceInnerSearchAdapter extends JsonResponseAdapter {
  
  private List<Face.Match> matches = null;
  
  private JSONArray matchesArray = null;
  
  @Override
  public void setResponseString(String responseStr) throws AdapterInitException {
    super.setResponseString(responseStr);
    if (this.getJsonObject().has("matches")) {
      this.matchesArray = this.getJsonObject().getJSONArray("matches");
    }
  }

  public List<Face.Match> getMatches() {
    if (this.matchesArray != null) {
      if (this.matches == null) {
        this.matches = new ArrayList<Face.Match>();
        for (int i = 0 ; i < this.matchesArray.size() ; i ++) {
          JSONObject matchObj = this.matchesArray.getJSONObject(i);
          Face.Match match = new Face.Match();
          match.loadDataFromJSONObject(matchObj);
          this.matches.add(match);
        }
      }
    }
    return this.matches;
  }
  
}
