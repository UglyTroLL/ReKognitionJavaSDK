package com.imjojo.rekognition.adapter;

import com.imjojo.rekognition.adapter.model.NameSpaceStats;
import com.imjojo.rekognition.adapter.model.UserIdStats;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceStatsAdapter extends JsonResponseAdapter {
  
  private JSONArray nameSpaceStatsArray = null;
  private JSONArray userIdStatsArray = null;
  
  private List<NameSpaceStats> nameSpaceStats = null;
  private List<UserIdStats> userIdStats = null;

  @Override
  public void setResponseString(String responseStr) throws AdapterInitException {
    super.setResponseString(responseStr);
    if (this.getJsonObject().has("name_space_stats")) {
      this.nameSpaceStatsArray = this.getJsonObject().getJSONArray("name_space_stats");
    }
    if (this.getJsonObject().has("user_id_stats")) {
      this.userIdStatsArray = this.getJsonObject().getJSONArray("user_id_stats");
    }
  }
  
  public List<NameSpaceStats> getNameSpaceStats () {
    if (this.nameSpaceStatsArray != null) {
      if (this.nameSpaceStats == null) {
        nameSpaceStats = new ArrayList<NameSpaceStats>();
        for (int i = 0 ; i < this.nameSpaceStatsArray.size() ; i ++) {
          JSONObject nameSpaceObj = this.nameSpaceStatsArray.getJSONObject(i);
          NameSpaceStats nss = new NameSpaceStats();
          nss.loadDataFromJSONObject(nameSpaceObj);
          this.nameSpaceStats.add(nss);
        }
      }
      return this.nameSpaceStats;
    } else {
      return null;
    }
  }
  
  public List<UserIdStats> getUserIdStats() {
    if (this.userIdStatsArray != null) {
      if (this.userIdStats == null) {
        this.userIdStats = new ArrayList<UserIdStats>();
        for (int i = 0 ; i < this.userIdStatsArray.size() ; i ++) {
          JSONObject userIdObj = this.userIdStatsArray.getJSONObject(i);
          UserIdStats uis = new UserIdStats();
          uis.loadDataFromJSONObject(userIdObj);
          this.userIdStats.add(uis);
        }
      }
      return this.userIdStats;
    } else {
      return null;
    }
  }

  
}
