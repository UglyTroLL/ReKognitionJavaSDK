package com.imjojo.rekognition.adapter.model;

/**
 *
 * @author Zhibo Wei (zhibo@imjojo.com)
 */
public class FieldNotFoundException extends Exception {
  
  private final String missingField;
  
  public FieldNotFoundException(String field) {
    super("Field : " + field + " is missing");
    this.missingField = field;
  }

  /**
   * @return the missingField
   */
  public String getMissingField() {
    return missingField;
  }
  
}
