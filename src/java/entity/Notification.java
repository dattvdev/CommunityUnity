package entity;

import java.util.Date;

/**
 *
 * @author ANH QUAN
 */
public class Notification {
    private int id,user_id;
    private String content;
    private Date date;
    private String link;
    private int reacter;

    public Notification() {
    }

    public Notification(int id, int user_id, String content, Date date, String link, int reacter) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.date = date;
        this.link = link;
        this.reacter = reacter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getReacter() {
        return reacter;
    }

    public void setReacter(int reacter) {
        this.reacter = reacter;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", user_id=" + user_id + ", content=" + content + ", date=" + date + ", link=" + link + ", reacter=" + reacter + '}';
    }

    

    }