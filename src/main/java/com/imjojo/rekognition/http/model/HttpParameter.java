package com.imjojo.rekognition.http.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class HttpParameter {

  private final String name;
  private final String value;

  public HttpParameter(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public HttpParameter(String name, double value) {
    this.name = name;
    this.value = String.valueOf(value);
  }

  public HttpParameter(String name, int value) {
    this.name = name;
    this.value = String.valueOf(value);
  }
  
  public HttpParameter(String name, long value) {
    this.name = name;
    this.value = String.valueOf(value);
  }

  public HttpParameter(String name, Base64Field base64Field) {
    this.name = name;
    this.value = base64Field.getBase64EncodedValue();
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  public static class Base64Field {

    private final String plainText;
    private final String base64EncodedValue;

    private static final String DEFAULT_IMAGE_TYPE = "png";

    public Base64Field(byte[] bytes) {
      this.base64EncodedValue = Base64.encodeBase64String(bytes);
      this.plainText = new String(bytes);
    }

    public Base64Field(File imageFile) throws IOException {
      BufferedImage bi = ImageIO.read(imageFile);
      byte[] bytes = parseBufferedImageToBytes(bi);
      this.plainText = new String(bytes);
      this.base64EncodedValue = Base64.encodeBase64String(bytes);
    }

    public Base64Field(String plainText) {
      this.plainText = plainText;
      byte[] bytes = plainText.getBytes();
      this.base64EncodedValue = Base64.encodeBase64String(bytes);
    }

    /**
     * @return the plainText
     */
    public String getPlainText() {
      return plainText;
    }

    /**
     * @return the base64EncodedValue
     */
    public String getBase64EncodedValue() {
      return base64EncodedValue;
    }

    private static byte[] parseBufferedImageToBytes(BufferedImage originalImage) {
      ByteArrayOutputStream baos = null;
      try {
        baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, DEFAULT_IMAGE_TYPE, baos);
        baos.flush();
        originalImage.flush();
        return baos.toByteArray();
      } catch (IOException ex) {
        return null;
      } finally {
        try {
          if (baos != null) {
            baos.close();
          }
        } catch (IOException ex) {
          
        }
      }
    }

  }

}
