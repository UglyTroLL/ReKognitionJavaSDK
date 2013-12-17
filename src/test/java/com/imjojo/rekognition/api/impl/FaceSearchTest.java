package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.FaceAdapter;
import com.imjojo.rekognition.adapter.FaceInnerSearchAdapter;
import com.imjojo.rekognition.adapter.model.FieldNotFoundException;
import java.io.File;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceSearchTest extends TestCase {
  
  private static final String REKO_API_KEY = "";
  private static final String REKO_API_SECRET = "";
  
  public FaceSearchTest(String testName) {
    super(testName);
  }

  /**
   * Test of outSearchWithFaceDetect method, of class FaceSearch.
   */
//  public void testOutSearchWithFaceDetect_6args_1() throws Exception {
//    System.out.println("outSearchWithFaceDetect");
//    List<String> tags = null;
//    List<FaceDetect.FaceDetectJobs> faceDetectJobs = null;
//    String url = "http://rekognition.com/static/img/people.jpg";
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    Integer numberReturn = 100;
//    FaceSearch instance = new FaceSearch(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.outSearchWithFaceDetect(tags, faceDetectJobs, url, nameSpace, userId, numberReturn);
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
   * Test of outSearchWithFaceDetect method, of class FaceSearch.
   */
//  public void testOutSearchWithFaceDetect_6args_2() throws Exception {
//    System.out.println("outSearchWithFaceDetect");
//    List<String> tags = null;
//    List<FaceDetect.FaceDetectJobs> faceDetectJobs = null;
//    File localFile = new File("/home/uglytroll/Downloads/people.jpg");
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    Integer numberReturn = 100;
//    FaceSearch instance = new FaceSearch(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.outSearchWithFaceDetect(tags, faceDetectJobs, localFile, nameSpace, userId, numberReturn);
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
   * Test of outSearchWithNoFaceDetect method, of class FaceSearch.
   */
//  public void testOutSearchWithNoFaceDetect_5args_1() throws Exception {
//    System.out.println("outSearchWithNoFaceDetect");
//    List<String> tags = null;
//    String url = "http://rekognition.com/static/img/people.jpg";
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    Integer numberReturn = 100;
//    FaceSearch instance = new FaceSearch(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.outSearchWithNoFaceDetect(tags, url, nameSpace, userId, numberReturn);
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

  /**
   * Test of innerSearch method, of class FaceSearch.
   */
//  public void testInnerSearch() throws Exception {
//    System.out.println("innerSearch");
//    String queryTag = "sdkunittestweb";
//    String imageIndex = "1243602";
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    Integer numReturn = 100;
//    FaceSearch instance = new FaceSearch(REKO_API_KEY, REKO_API_SECRET);
//    FaceInnerSearchAdapter result = instance.innerSearch(queryTag, imageIndex, nameSpace, userId, numReturn);
//    assertEquals(2, result.getMatches().size());
//    
//  }
  
}
