package com.imjojo.rekognition.api.impl;

import com.imjojo.rekognition.adapter.JsonResponseAdapter;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FaceCrawlTest extends TestCase {
  
  private static final String REKO_API_KEY = "";
  private static final String REKO_API_SECRET = "";
  
  public FaceCrawlTest(String testName) {
    super(testName);
  }
  

  /**
   * Test of getResponse method, of class FaceCrawl.
   */
//  public void testGetResponse() throws Exception {
//    System.out.println("getResponse");
//    List<Long> idsToCrawl = new ArrayList<Long>();
//    idsToCrawl.add(567034905l);
//    long fbId = 100005033769797L;
//    String accessToken = "CAACEdEose0cBAJqEtnSa8UEOoVumjCMKBIUCV7LAY5hcupROW0oZAOBdHdTnbX2JCsAvqnh2LGpJKESBYPOWws6eYoKDSKcH7x7FqYZAvS2DsNEQZCwipkK8Ii3nOf6jFF9ZCf2krazbsLJRLUErfWlQabqEG8xZBgznVlZACRKd9w4l13xrKNNZCIUZAwwjjFEZD";
//    String nameSpace = "sdkunittest";
//    String userId = "zhibo";
//    FaceCrawl instance = new FaceCrawl(REKO_API_KEY, REKO_API_SECRET);
//    JsonResponseAdapter result = instance.getResponse(idsToCrawl, fbId, accessToken, nameSpace, userId);
//    System.out.println("result : " + result.getResponseStr());
//    assertEquals("Succeed.", result.getStatus());
//    assertTrue(result.getJsonObject().getInt("face_found") > 0);
//  }
  
}
