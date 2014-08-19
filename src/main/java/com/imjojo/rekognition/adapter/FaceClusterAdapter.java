package com.imjojo.rekognition.adapter;

import com.imjojo.rekognition.adapter.model.Cluster;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceClusterAdapter extends JsonResponseAdapter {
  
  private static final Logger logger = Logger.getLogger(FaceClusterAdapter.class.getName());
  
  private JSONArray clustersArray = null;
  
  private final List<Cluster> clusters = new ArrayList<Cluster>();
  
  @Override
  public void setResponseString(String responseStr) throws AdapterInitException {
    super.setResponseString(responseStr);
    try {
      this.clustersArray = this.getJsonObject().getJSONArray("clusters");
    } catch (JSONException ex) {
      logger.error("Cannot get clusters field from json object" + responseStr, ex);
      throw new AdapterInitException("Cannot get clusters field from json object");
    }
  }
  
  public List<Cluster> getClusters () {
    if (this.clusters.isEmpty()) {
      if (this.clustersArray != null) {
        for (int i = 0 ; i < this.clustersArray.size() ; i ++) {
          JSONObject clusterObj = this.clustersArray.getJSONObject(i);
          Cluster cluster = new Cluster();
          cluster.loadDataFromJSONObject(clusterObj);
          this.clusters.add(cluster);
        }
      }
    }
    return this.clusters;
  }
  
}
