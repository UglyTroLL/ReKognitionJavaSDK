package com.imjojo.rekognition.http;


import com.imjojo.rekognition.http.model.RekognitionAPIException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class HttpManagerTest extends TestCase {
  
  public HttpManagerTest(String testName) {
    super(testName);
  }

//  public void testMassiveHttpCall() throws InterruptedException {
//    
//    String[] urls = {"http://www.github.com", "http://www.bing.com","http://www.facebook.com",
//      "http://www.airbnb.com","http://www.twitter.com", "http://www.google.com", "https://www.varnish-cache.org"};
//    CountDownLatch latch = new CountDownLatch(urls.length);
//    for (String url : urls) {
//      GoHttpClient ghc = new GoHttpClient(url, latch);
//      new Thread(ghc).start();
//    }
//    assertTrue(latch.await(2, TimeUnit.MINUTES));
//  }
  
  private class GoHttpClient implements Runnable {
    private final String url;
    private final CountDownLatch latch;
    
    public GoHttpClient (String url, CountDownLatch latch) {
      this.url = url;
      this.latch = latch;
    }

    @Override
    public void run() {
      System.out.println("url " + url + " started");
      for (int i = 0 ; i < 100 ; i ++) {
        System.out.println("url " + url + ", i : " + i);
        HttpManager hm = HttpManager.getInstance();
        try {
          String response = hm.get(url);
          System.out.println("url : " + url + " response length : " + response.length() + ", i : " + i);
          if (response.length() <= 100) {
            return;
          }
          //assertNotNull(response);
        } catch (RekognitionAPIException ex) {
          Logger.getLogger(HttpManagerTest.class.getName()).log(Level.SEVERE, null, ex);
          //fail("url : " + url + ", i = " + i);
        }
      }
      latch.countDown();
    }
    
  }
  
}
