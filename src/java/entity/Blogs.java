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
public class Blogs {

    private int blogId;
    private String title;
    private String content;
    private String shortContent;
    private String author;
    private Date date;
    private String category;
    private String photo;
    private int pending;

    public Blogs() {
    }

    public Blogs(int blogId, String title, String content, String author, Date date, String category, String photo, String shortContent, int pending) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
        this.photo = photo;
        this.shortContent = shortContent;
        this.pending = pending;

    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public int getPending() {
   
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    @Override
    public String toString() {
        return "Blogs{" + "blogId=" + blogId + ", title=" + title + ", content=" + content + ", shortContent=" + shortContent + ", author=" + author + ", date=" + getDate() + ", category=" + category + ", photo=" + photo + ", pending=" + getPending() + '}';
    }

  

}
