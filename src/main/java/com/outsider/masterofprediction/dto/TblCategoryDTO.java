package com.outsider.masterofprediction.dto;


public class TblCategoryDTO {

  private long categoryNo;
  private String categoryName;

  public TblCategoryDTO() {
  }

  public TblCategoryDTO(long categoryNo, String categoryName) {
    this.categoryNo = categoryNo;
    this.categoryName = categoryName;
  }

  public long getCategoryNo() {
    return categoryNo;
  }

  public void setCategoryNo(long categoryNo) {
    this.categoryNo = categoryNo;
  }


  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }


  @Override
  public String toString() {
    return "TblCategoryDTO" + '{' + 
            "categoryNo='" + categoryNo + '\'' + ',' +
            "categoryName='" + categoryName + '\'' +            '}';
  }
}
