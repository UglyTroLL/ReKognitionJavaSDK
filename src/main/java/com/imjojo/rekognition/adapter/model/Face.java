package com.imjojo.rekognition.adapter.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class Face implements IRekognitionModel {
  
  private JSONObject faceObj;

  private Emotion emotion = null;
  private Pose pose = null;
  private Nose nose = null;
  private Mouth mouth = null;
  private Eyes eyes = null;
  private BoundingBox boundingBox = null;
  private Race race = null;
  private List<Match> matches = null;

  @Override
  public void loadDataFromJSONObject(JSONObject faceObj) {
    this.faceObj = faceObj;
  }

  public double getConfidence() throws FieldNotFoundException {
    if (this.faceObj.has("confidence")) {
      return this.faceObj.getDouble("confidence");
    } else {
      throw new FieldNotFoundException("confidence");
    }
  }

  public double getSmile() throws FieldNotFoundException {
    if (this.faceObj.has("smile")) {
      return this.faceObj.getDouble("smile");
    } else {
      throw new FieldNotFoundException("smile");
    }
  }

  public double getGlasses() throws FieldNotFoundException {
    if (this.faceObj.has("glasses")) {
      return this.faceObj.getDouble("glasses");
    } else {
      throw new FieldNotFoundException("glasses");
    }
  }

  public double getEyeClosed() throws FieldNotFoundException {
    if (this.faceObj.has("eye_closed")) {
      return this.faceObj.getDouble("eye_closed");
    } else {
      throw new FieldNotFoundException("eye_closed");
    }
  }

  public double getMouthOpenWide() throws FieldNotFoundException {
    if (this.faceObj.has("mouth_open_wide")) {
      return this.faceObj.getDouble("mouth_open_wide");
    } else {
      throw new FieldNotFoundException("mouth_open_wide");
    }
  }

  public double getGender() throws FieldNotFoundException {
    if (this.faceObj.has("sex")) {
      return this.faceObj.getDouble("sex");
    } else {
      throw new FieldNotFoundException("sex");
    }
  }
  
  public String getImageIndex() throws FieldNotFoundException {
    if (this.faceObj.has("img_index")) {
      return this.faceObj.getString("img_index");
    } else {
      throw new FieldNotFoundException("img_index");
    }
  }

  public Emotion getEmotion() throws FieldNotFoundException {
    if (this.emotion == null) {
      this.emotion = new Emotion();
      this.emotion.loadDataFromJSONObject(faceObj);
    }
    return emotion;
  }

  public Pose getPose() throws FieldNotFoundException {
    if (this.pose == null) {
      this.pose = new Pose();
      this.pose.loadDataFromJSONObject(faceObj);
    }
    return pose;
  }

  public Nose getNose() throws FieldNotFoundException {
    if (this.nose == null) {
      this.nose = new Nose();
      this.nose.loadDataFromJSONObject(faceObj);
    }
    return nose;
  }

  public Mouth getMouth() throws FieldNotFoundException {
    if (this.mouth == null) {
      this.mouth = new Mouth();
      this.mouth.loadDataFromJSONObject(faceObj);
    }
    return mouth;
  }

  public Eyes getEyes() throws FieldNotFoundException {
    if (this.eyes == null) {
      this.eyes = new Eyes();
      this.eyes.loadDataFromJSONObject(faceObj);
    }
    return eyes;
  }

  public BoundingBox getBoundingBox() throws FieldNotFoundException {
    if (this.boundingBox == null) {
      this.boundingBox = new BoundingBox();
      this.boundingBox.loadDataFromJSONObject(faceObj);
    }
    return boundingBox;
  }

  public Race getRace() throws FieldNotFoundException {
    if (this.race == null) {
      this.race = new Race();
      this.race.loadDataFromJSONObject(faceObj);
    }
    return race;
  }

  public List<Match> getMatches() throws FieldNotFoundException {
    if (this.matches == null) {
      this.matches = new ArrayList<Match>();
      try {
        JSONArray mathcesArray = this.faceObj.getJSONArray("matches");
        for (int i = 0 ; i < mathcesArray.size() ; i ++) {
          JSONObject matchObj = mathcesArray.getJSONObject(i);
          Match match = new Match();
          match.loadDataFromJSONObject(matchObj);
          this.matches.add(match);
        }
      } catch (Exception ex) {
        throw new FieldNotFoundException("matches");
      }
    }
    return matches;
  }
  
  public class Match implements IRekognitionModel {
    
    private JSONObject matchObj;
    
    private String tag = null;
    private Double score = null;
    private String imageIndex = null;

    @Override
    public void loadDataFromJSONObject(JSONObject matchObj) {
      this.matchObj = matchObj;
    }

    public String getTag() throws FieldNotFoundException {
      if (this.tag == null) {
        if (this.matchObj.has("tag")) {
          this.tag = this.matchObj.getString("tag");
        } else {
          throw new FieldNotFoundException("matches.tag");
        }
      }
      return tag;
    }

    public Double getScore() throws FieldNotFoundException {
      if (this.score == null) {
        if (this.matchObj.has("score")) {
          this.score = Double.valueOf(matchObj.getString("score"));
        } else {
          throw new FieldNotFoundException("matches.score");
        }
      }
      return this.score;
    }

    public String getImageIndex() throws FieldNotFoundException {
      if (this.imageIndex == null) {
        if (this.matchObj.has("img_index")) {
          this.imageIndex = matchObj.getString("img_index");
        } else {
          throw new FieldNotFoundException("matches.img_index");
        }
      }
      return imageIndex;
    }
    
  }
  
  public class Race implements IRekognitionModel {
    
    private final Map<String, Double> raceMap = new HashMap<String, Double>();

    @Override
    public void loadDataFromJSONObject(JSONObject faceObj) throws FieldNotFoundException {
      try {
        JSONObject raceObj = faceObj.getJSONObject("race");
        Iterator<String> keysIter = raceObj.keys();
        while (keysIter.hasNext()) {
          String key = keysIter.next();
          this.getRaceMap().put(key, raceObj.getDouble(key));
        }
      } catch (Exception ex) {
        throw new FieldNotFoundException("race");
      }
    }

    /**
     * @return the raceMap
     */
    public Map<String, Double> getRaceMap() {
      return raceMap;
    }
    
  }

  public class Emotion implements IRekognitionModel {

    private final Map<String, Double> emotionMap = new HashMap<String, Double>();

    @Override
    public void loadDataFromJSONObject(JSONObject faceObj) throws FieldNotFoundException {
      try {
        JSONObject emotionObj = faceObj.getJSONObject("emotion");
        Iterator<String> keysIter = emotionObj.keys();
        while (keysIter.hasNext()) {
          String key = keysIter.next();
          this.getEmotionMap().put(key, emotionObj.getDouble(key));
        }
      } catch (Exception ex) {
        throw new FieldNotFoundException("emotion");
      }
    }

    /**
     * @return the emotionMap
     */
    public Map<String, Double> getEmotionMap() {
      return emotionMap;
    }
  }

  public class Pose implements IRekognitionModel {

    private Double roll = null;
    private Double yaw = null;
    private Double pitch = null;

    /**
     * @return the roll
     */
    public Double getRoll() {
      return roll;
    }

    /**
     * @return the yaw
     */
    public Double getYaw() {
      return yaw;
    }

    /**
     * @return the pitch
     */
    public Double getPitch() {
      return pitch;
    }

    @Override
    public void loadDataFromJSONObject(JSONObject faceObj) throws FieldNotFoundException {
      try {
        JSONObject poseObj = faceObj.getJSONObject("pose");
        if (poseObj.has("roll")) {
          this.roll = poseObj.getDouble("roll");
        }
        if (poseObj.has("yaw")) {
          this.yaw = poseObj.getDouble("yaw");
        }
        if (poseObj.has("pitch")) {
          this.pitch = poseObj.getDouble("pitch");
        }
      } catch (Exception ex) {
        throw new FieldNotFoundException("pose");
      }
    }
  }

  public class Nose implements IRekognitionModel {

    private double noseX;
    private double noseY;

    /**
     * @return the noseX
     */
    public double getNoseX() {
      return noseX;
    }

    /**
     * @return the noseY
     */
    public double getNoseY() {
      return noseY;
    }

    @Override
    public void loadDataFromJSONObject(JSONObject faceObj) throws FieldNotFoundException {
      try {
        this.noseX = faceObj.getJSONObject("nose").getDouble("x");
        this.noseY = faceObj.getJSONObject("nose").getDouble("y");
      } catch (Exception ex) {
        throw new FieldNotFoundException("nose");
      }
    }

  }

  public class Mouth implements IRekognitionModel {

    private double mouthLeftX;
    private double mouthLeftY;
    private double mouthRightX;
    private double mouthRightY;

    /**
     * @return the mouthLeftX
     */
    public double getMouthLeftX() {
      return mouthLeftX;
    }

    /**
     * @return the mouthLeftY
     */
    public double getMouthLeftY() {
      return mouthLeftY;
    }

    /**
     * @return the mouthRightX
     */
    public double getMouthRightX() {
      return mouthRightX;
    }

    /**
     * @return the mouthRightY
     */
    public double getMouthRightY() {
      return mouthRightY;
    }

    @Override
    public void loadDataFromJSONObject(JSONObject faceObj) throws FieldNotFoundException {
      try {
        JSONObject leftMouthObj = faceObj.getJSONObject("mouth_l");
        JSONObject rightMouthObj = faceObj.getJSONObject("mouth_r");
        this.mouthLeftX = leftMouthObj.getDouble("x");
        this.mouthLeftY = leftMouthObj.getDouble("y");
        this.mouthRightX = rightMouthObj.getDouble("x");
        this.mouthRightY = rightMouthObj.getDouble("y");
      } catch (Exception ex) {
        throw new FieldNotFoundException("mouth");
      }
    }
  }

  public class Eyes implements IRekognitionModel {

    private double leftEyeX;
    private double leftEyeY;
    private double rightEyeX;
    private double rightEyeY;

    /**
     * @return the leftEyeX
     */
    public double getLeftEyeX() {
      return leftEyeX;
    }

    /**
     * @return the leftEyeY
     */
    public double getLeftEyeY() {
      return leftEyeY;
    }

    /**
     * @return the rightEyeX
     */
    public double getRightEyeX() {
      return rightEyeX;
    }

    /**
     * @return the rightEyeY
     */
    public double getRightEyeY() {
      return rightEyeY;
    }

    @Override
    public void loadDataFromJSONObject(JSONObject faceObj) throws FieldNotFoundException {
      try {
        JSONObject leftEyeObject = faceObj.getJSONObject("eye_left");
        JSONObject rightEyeObject = faceObj.getJSONObject("eye_right");
        this.leftEyeX = leftEyeObject.getDouble("x");
        this.leftEyeY = leftEyeObject.getDouble("y");
        this.rightEyeX = rightEyeObject.getDouble("x");
        this.rightEyeY = rightEyeObject.getDouble("y");
      } catch (Exception ex) {
        throw new FieldNotFoundException("eyes");
      }
    }

  }

  public class BoundingBox implements IRekognitionModel {

    private double topLeftX;
    private double topLeftY;
    private double width;
    private double height;

    /**
     * @return the topLeftX
     */
    public double getTopLeftX() {
      return topLeftX;
    }

    /**
     * @return the topLeftY
     */
    public double getTopLeftY() {
      return topLeftY;
    }

    /**
     * @return the width
     */
    public double getWidth() {
      return width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
      return height;
    }

    @Override
    public void loadDataFromJSONObject(JSONObject faceObj) throws FieldNotFoundException {
      try {
        JSONObject boundingBoxObj = faceObj.getJSONObject("boundingbox");
        JSONObject topLeftObj = boundingBoxObj.getJSONObject("tl");
        JSONObject sizeObj = boundingBoxObj.getJSONObject("size");
        this.topLeftX = topLeftObj.getDouble("x");
        this.topLeftY = topLeftObj.getDouble("y");
        this.height = sizeObj.getDouble("height");
        this.width = sizeObj.getDouble("width");
      } catch (Exception ex) {
        throw new FieldNotFoundException("boudingbox");
      }
    }
  }

}
