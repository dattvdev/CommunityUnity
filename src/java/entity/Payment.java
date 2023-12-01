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
public class Payment {

    private int paymentId;
    private int giverId;
    private int receiverId;
    private int eventId;
    private Date transactionDate;
    private String text;
    private double amount;
    private int status;
    private String giverName;
    private String photo;

    public Payment(Date transactionDate, String text, double amount, String giverName) {
        this.transactionDate = transactionDate;
        this.text = text;
        this.amount = amount;
        this.giverName = giverName;
    }

    public Payment(int paymentId, int giverId, int receiverId, int eventId, Date transactionDate, String text, double amount, int status, String giverName, String photo) {
        this.paymentId = paymentId;
        this.giverId = giverId;
        this.receiverId = receiverId;
        this.eventId = eventId;
        this.transactionDate = transactionDate;
        this.text = text;
        this.amount = amount;
        this.status = status;
        this.giverName = giverName;
        this.photo = photo;
    }

    public Payment(int paymentId, int giverId, int receiverId, int eventId, Date transactionDate, String text, double amount, int status) {
        this.paymentId = paymentId;
        this.giverId = giverId;
        this.receiverId = receiverId;
        this.eventId = eventId;
        this.transactionDate = transactionDate;
        this.text = text;
        this.amount = amount;
        this.status = status;
    }

    public Payment() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getGiverId() {
        return giverId;
    }

    public String getGiverName() {
        return giverName;
    }

    public void setGiverName(String giverName) {
        this.giverName = giverName;
    }

    public void setGiverId(int giverId) {
        this.giverId = giverId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTransactionDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String formattedDate = dateFormat.format(transactionDate);
        return formattedDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", giverId=" + giverId + ", receiverId=" + receiverId + ", eventId=" + eventId + " "+giverName+ ", transactionDate=" + getTransactionDate() + ", text=" + text + ", amount=" + amount + ", status=" + status + '}';
    }

}
