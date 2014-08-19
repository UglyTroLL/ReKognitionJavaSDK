package com.imjojo.rekognition.adapter;

import com.imjojo.rekognition.adapter.model.Visualization;
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
public class FaceVisualizationAdapter extends JsonResponseAdapter {
  
  private JSONArray visualizationArray;
  
  private List<Visualization> visualizations = null;
  
  private static final Logger logger = Logger.getLogger(FaceVisualizationAdapter.class.getName());
  
  @Override
  public void setResponseString(String responseStr) throws AdapterInitException {
    super.setResponseString(responseStr);
    try {
      this.visualizationArray = this.getJsonObject().getJSONArray("visualization");
    } catch (JSONException ex) {
      logger.error("Cannot get visualization field from json object" + responseStr, ex);
      throw new AdapterInitException("Cannot get visualization field from json object");
    }
  }

  /**
   * @return the visualizations
   */
  public List<Visualization> getVisualizations() {
    if (this.visualizations == null) {
      this.visualizations = new ArrayList<Visualization>();
      for (int i = 0 ; i < this.visualizationArray.size() ; i ++) {
        JSONObject visualizationObj = this.visualizationArray.getJSONObject(i);
        Visualization visualization = new Visualization();
        visualization.loadDataFromJSONObject(visualizationObj);
        this.visualizations.add(visualization);
      }
    }
    return visualizations;
  }
  
  
}
