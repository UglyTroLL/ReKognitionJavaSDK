package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.FaceAdapter;
import com.imjojo.rekognition.adapter.model.Face;
import java.io.File;
import junit.framework.TestCase;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceAddTest extends TestCase {
  
  private static final String REKO_API_KEY = "";
  private static final String REKO_API_SECRET = "";
  
  private static final String URL = "http://rekognition.com/static/img/people.jpg";
  
  public FaceAddTest(String testName) {
    super(testName);
  }

  /**
   * Test of getResponse method, of class FaceAdd.
   */
//  public void testGetResponse_6args_1() throws Exception {
//    System.out.println("getResponse");
//    boolean aggessive = false;
//    boolean nodetect = false;
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    String tag = "sdkunittestweb";
//    FaceAdd instance = new FaceAdd(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.getResponse(URL, aggessive, nodetect, nameSpace, userId, tag);
//    assertEquals(result.getFaces().size(), 1);
//    Face face = result.getFaces().get(0);
//    assertNotNull(face.getImageIndex());
//  }

  /**
   * Test of getResponse method, of class FaceAdd.
   */
//  public void testGetResponse_6args_2() throws Exception {
//    System.out.println("getResponse");
//    File localPictureFile = new File("/home/uglytroll/Downloads/people.jpg");
//    boolean aggessive = false;
//    boolean nodetect = false;
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    String tag = "sdkunittestlocal";
//    FaceAdd instance = new FaceAdd(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.getResponse(localPictureFile, aggessive, nodetect, nameSpace, userId, tag);
//    assertEquals(result.getFaces().size(), 1);
//    Face face = result.getFaces().get(0);
//    assertNotNull(face.getImageIndex());
//  }
  
}
