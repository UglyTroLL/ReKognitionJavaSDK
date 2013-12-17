package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.FaceVisualizationAdapter;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceVisualizeTest extends TestCase {
  
  private static final String REKO_API_KEY = "";
  private static final String REKO_API_SECRET = "";
  
  public FaceVisualizeTest(String testName) {
    super(testName);
  }

  /**
   * Test of getResponse method, of class FaceVisualize.
   */
//  public void testGetResponse() throws Exception {
//    System.out.println("getResponse");
//    boolean needDisplayImage = true;
//    boolean showUntaggedFace = true;
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    List<String> tags = null;
//    Integer numOfTagsReturn = 100;
//    Integer numOfImageReturnPerTag = 100;
//    FaceVisualize instance = new FaceVisualize(REKO_API_KEY, REKO_API_SECRET);
//    FaceVisualizationAdapter result = instance.getResponse(needDisplayImage, showUntaggedFace, nameSpace, userId, tags, numOfTagsReturn, numOfImageReturnPerTag);
//    System.out.println("result : " + result.getResponseStr());
//    assertTrue(result.getVisualizations().size() > 0);
//    assertTrue(result.getVisualizations().get(0).getIndex().size() > 0);
//  }
  
}
