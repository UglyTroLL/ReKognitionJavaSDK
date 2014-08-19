package com.imjojo.rekognition.adapter;

import com.imjojo.rekognition.adapter.model.Face;
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
public class FaceAdapter extends JsonResponseAdapter {
  
  private static final String FACE_DETECTION_KEY = "face_detection";
  
  private static final Logger logger = Logger.getLogger(FaceAdapter.class.getName());
  
  private JSONArray faceDetectedJsonArray = null;
  
  private final List<Face> faces = new ArrayList<Face>();

  @Override
  public void setResponseString(String responseStr) throws AdapterInitException {
    super.setResponseString(responseStr);
    try {
      this.faceDetectedJsonArray = this.getJsonObject().getJSONArray(FACE_DETECTION_KEY);
    } catch (JSONException ex) {
      logger.error("Cannot get face_detection field from json object" + responseStr, ex);
      throw new AdapterInitException("Cannot get face_detection field from json object");
    }
  }
  
  public List<Face> getFaces() {
    if (this.faces.isEmpty()) {
      if (this.faceDetectedJsonArray != null) {
        for (int i = 0 ; i < this.faceDetectedJsonArray.size() ; i ++) {
          JSONObject faceObj = this.faceDetectedJsonArray.getJSONObject(i);
          Face face = new Face();
          face.loadDataFromJSONObject(faceObj);
          this.faces.add(face);
        }
      }
    }
    return this.faces;
  }
  
}
