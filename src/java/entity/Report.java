/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Report {

    private int activityId;
    private int organizerId;
    private String text;
    private Date date;
    private String nameaccti;
    private String nameOrga;
    

    public Report(int activityId, int organizerId) {
        this.activityId = activityId;
        this.organizerId = organizerId;
    }

    public Report(int activityId, String text, Date date, String nameaccti) {
        this.activityId = activityId;
        this.text = text;
        this.date = date;
        this.nameaccti = nameaccti;
    }
    
    

    public Report(int activityId, int organizerId, String text, Date date, String nameaccti, String nameOrga) {
        this.activityId = activityId;
        this.organizerId = organizerId;
        this.text = text;
        this.date = date;
        this.nameaccti = nameaccti;
        this.nameOrga = nameOrga;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNameaccti() {
        return nameaccti;
    }

    public void setNameaccti(String nameaccti) {
        this.nameaccti = nameaccti;
    }

    public String getNameOrga() {
        return nameOrga;
    }

    public void setNameOrga(String nameOrga) {
        this.nameOrga = nameOrga;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    @Override
    public String toString() {
        return "Report{" + "activityId=" + activityId + ", organizerId=" + organizerId + ", text=" + text + ", date=" + date + '}';
    }

    public Report(int activityId, int organizerId, String text, Date date) {
        this.activityId = activityId;
        this.organizerId = organizerId;
        this.text = text;
        this.date = date;
    }

 

}