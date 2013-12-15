package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.FaceAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceDetectTest extends TestCase {
  
  private static final String REKO_API_KEY = "MDFGUrzC3nd35KKP";
  private static final String REKO_API_SECRET = "FktOEvh5bdroQfhF";
  
  private List<FaceDetect.FaceDetectJobs> jobs = new ArrayList<FaceDetect.FaceDetectJobs>();
  
  public FaceDetectTest(String testName) {
    super(testName);
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    jobs.add(FaceDetect.FaceDetectJobs.Age);
    jobs.add(FaceDetect.FaceDetectJobs.Aggressive);
    jobs.add(FaceDetect.FaceDetectJobs.Emotion);
    jobs.add(FaceDetect.FaceDetectJobs.EyeClosed);
    jobs.add(FaceDetect.FaceDetectJobs.Gender);
    jobs.add(FaceDetect.FaceDetectJobs.Glass);
    jobs.add(FaceDetect.FaceDetectJobs.MouthOpenWide);
    jobs.add(FaceDetect.FaceDetectJobs.Part);
    jobs.add(FaceDetect.FaceDetectJobs.PartDetail);
    jobs.add(FaceDetect.FaceDetectJobs.Race);
  }
  
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test of getResponse method, of class FaceDetect.
   */
  public void testGetResponse_List_String() throws Exception {
    System.out.println("getResponse");
    String pictureUrl = "http://rekognition.com/static/img/people.jpg";
    FaceDetect instance = new FaceDetect(REKO_API_KEY, REKO_API_SECRET);
    FaceAdapter result = instance.getResponse(jobs, pictureUrl);
    System.out.println("result json : " + result.getResponseStr());
    assertEquals(result.getFaces().size(), 1);
    assertEquals(result.getFaces().get(0).getNose().getNoseX(), 170.1);
  }

  /**
   * Test of getResponse method, of class FaceDetect.
   */
//  public void testGetResponse_List_File() throws Exception {
//    System.out.println("getResponse");
//    File testFile = new File("/home/uglytroll/Downloads/people.jpg");
//    FaceDetect instance = new FaceDetect(REKO_API_KEY, REKO_API_SECRET);
//    FaceAdapter result = instance.getResponse(jobs, testFile);
//    System.out.println("result json : " + result.getResponseStr());
//    assertEquals(result.getFaces().size(), 1);
//    assertEquals(result.getFaces().get(0).getNose().getNoseX(), 170.1);
//  }
  
}
