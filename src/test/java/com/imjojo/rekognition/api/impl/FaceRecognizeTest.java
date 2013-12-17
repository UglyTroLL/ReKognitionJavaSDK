package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.FaceAdapter;
import com.imjojo.rekognition.adapter.model.FieldNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceRecognizeTest extends TestCase {
  
  private static final String REKO_API_KEY = "";
  private static final String REKO_API_SECRET = "";
  
  public FaceRecognizeTest(String testName) {
    super(testName);
  }

  /**
   * Test of recognizeFaceWithDetect method, of class FaceRecognize.
   */
//  public void testRecognizeFaceWithDetect_6args_1() throws Exception {
//    System.out.println("recognizeFaceWithDetect");
//    List<FaceDetect.FaceDetectJobs> detectJobs = null;
//    String url = "http://rekognition.com/static/img/people.jpg";
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    Integer numberReturn = 2;
//    List<String> tags = null;
//    FaceRecognize instance = new FaceRecognize(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.recognizeFaceWithDetect(detectJobs, url, nameSpace, userId, numberReturn, tags);
//    assertEquals(1, result.getFaces().size());
//    try {
//      result.getFaces().get(0).getBoundingBox();
//    } catch (FieldNotFoundException ex) {
//      fail();
//    }
//    assertEquals(2, result.getFaces().get(0).getMatches().size());
//    assertTrue(result.getFaces().get(0).getMatches().get(0).getTag().equals("sdkunittestweb") || 
//            result.getFaces().get(0).getMatches().get(0).getTag().equals("sdkunittestlocal"));
//    assertTrue(result.getFaces().get(0).getMatches().get(0).getScore() > 0.9);
//  }

  /**
   * Test of recognizeFaceWithDetect method, of class FaceRecognize.
   */
//  public void testRecognizeFaceWithDetect_6args_2() throws Exception {
//    System.out.println("recognizeFaceWithDetect");
//    List<FaceDetect.FaceDetectJobs> detectJobs = null;
//    File localFile = new File("/home/uglytroll/Downloads/people.jpg");
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    Integer numberReturn = 2;
//    List<String> tags = new ArrayList<String>();
//    tags.add("sdkunittestweb");
//    FaceRecognize instance = new FaceRecognize(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.recognizeFaceWithDetect(detectJobs, localFile, nameSpace, userId, numberReturn, tags);
//    assertEquals(1, result.getFaces().size());
//    try {
//      result.getFaces().get(0).getBoundingBox();
//    } catch (FieldNotFoundException ex) {
//      fail();
//    }
//    assertEquals(1, result.getFaces().get(0).getMatches().size());
//    assertTrue(result.getFaces().get(0).getMatches().get(0).getTag().equals("sdkunittestweb"));
//    assertTrue(result.getFaces().get(0).getMatches().get(0).getScore() > 0.9);
//  }

  /**
   * Test of recognizeFaceWithoutDetect method, of class FaceRecognize.
   */
//  public void testRecognizeFaceWithoutDetect_5args_1() throws Exception {
//    System.out.println("recognizeFaceWithoutDetect");
//    String url = "http://rekognition.com/static/img/people.jpg";
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    Integer numberReturn = 2;
//    List<String> tags = null;
//    FaceRecognize instance = new FaceRecognize(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.recognizeFaceWithoutDetect(url, nameSpace, userId, numberReturn, tags);
//    System.out.println("result : " + result.getResponseStr());
//    assertEquals(1, result.getFaces().size());
//    try {
//      result.getFaces().get(0).getEyes();
//      fail();
//    } catch (FieldNotFoundException ex) {
//      
//    }
//    assertEquals(2, result.getFaces().get(0).getMatches().size());
//    assertTrue(result.getFaces().get(0).getMatches().get(0).getTag().equals("sdkunittestweb") || 
//            result.getFaces().get(0).getMatches().get(0).getTag().equals("sdkunittestlocal"));
//    assertTrue(result.getFaces().get(0).getMatches().get(0).getScore() > 0.9);
//  }

  
}
