package com.outsider.masterofprediction.dto;

public class CommentDTO {
    private String subjectTitle;
    private String commentContent;
    private int commentSubjectNo;

    // Getters and Setters

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentSubjectNo() {
        return commentSubjectNo;
    }

    public void setCommentSubjectNo(int commentSubjectNo) {
        this.commentSubjectNo = commentSubjectNo;
    }
}
