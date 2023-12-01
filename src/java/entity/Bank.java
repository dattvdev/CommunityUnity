/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ytbhe
 */
public class Bank {

    private int id;
    private int or_id;
    private String numberCard;
    private String NameCard;
    private String NameBank;

    public Bank() {
    }

    public Bank(int id, int or_id, String numberCard, String NameCard, String NameBank) {
        this.id = id;
        this.or_id = or_id;
        this.numberCard = numberCard;
        this.NameCard = NameCard;
        this.NameBank = NameBank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOr_id() {
        return or_id;
    }

    public void setOr_id(int or_id) {
        this.or_id = or_id;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getNameCard() {
        return NameCard;
    }

    public void setNameCard(String NameCard) {
        this.NameCard = NameCard;
    }

    public String getNameBank() {
        return NameBank;
    }

    public void setNameBank(String NameBank) {
        this.NameBank = NameBank;
    }

    @Override
    public String toString() {
        return "Bank{" + "id=" + id + ", or_id=" + or_id + ", numberCard=" + numberCard + ", NameCard=" + NameCard + ", NameBank=" + NameBank + '}';
    }

}
