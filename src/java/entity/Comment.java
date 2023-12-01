/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ytbhe
 */
public class Comment {

    private int commentId;
    private int postId;
    private String commentContent;
    private int commentAuthorId;
    private String commentAuthorName;
    private Date commentDate;
    private String photoUser;

    public Comment() {
    }

    public Comment(int commentId, int postId, String commentContent, int commentAuthorId, String commentAuthorName, Date commentDate, String photo) {
        this.commentId = commentId;
        this.postId = postId;
        this.commentContent = commentContent;
        this.commentAuthorId = commentAuthorId;
        this.commentAuthorName = commentAuthorName;
        this.commentDate = commentDate;
        this.photoUser = photo;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }
    
    

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentAuthorId() {
        return commentAuthorId;
    }

    public void setCommentAuthorId(int commentAuthorId) {
        this.commentAuthorId = commentAuthorId;
    }

    public String getCommentAuthorName() {
        return commentAuthorName;
    }

    public void setCommentAuthorName(String commentAuthorName) {
        this.commentAuthorName = commentAuthorName;
    }

    public String getCommentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(commentDate);
        return date;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", postId=" + postId + ", commentContent=" + commentContent + ", commentAuthorId=" + commentAuthorId + ", commentAuthorName=" + commentAuthorName + ", commentDate=" + commentDate + ", photoUser=" + photoUser + '}';
    }


}
