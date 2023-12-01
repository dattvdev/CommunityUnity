package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account implements Serializable {

    private int accId;
    private String email;
    private String photo;
    private String userName;
    private String password;
    private String fullName;
    private int status;
    private int sex;
    private String phone;
    private int role;
    private String address;
    private Date birtDay;

    public Account() {
    }

    public Account(int accId, String email, String photo, String userName, String password, String fullName, int status, String phone, int role, String address, Date date, int sex) {
        this.accId = accId;
        this.email = email;
        this.photo = photo;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.birtDay = date;
        this.sex = sex;

    }

    public Account(int accId, String email, String photo, String userName, String password, String fullName, int status, String phone, int role, String address, Date d) {
        this.accId = accId;
        this.email = email;
        this.photo = photo;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.birtDay = d;

    }

    public Account(int accId, String email, String photo, String userName, String password, String fullName, int status, String phone, int role, String address) {
        this.accId = accId;
        this.email = email;
        this.photo = photo;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
        this.phone = phone;
        this.role = role;
        this.address = address;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account(int AccId, String Email, String Password, String FullName, int Status, String Phone, int Role) {
        this.accId = AccId;
        this.email = Email;
        this.password = Password;
        this.fullName = FullName;
        this.status = Status;
        this.phone = Phone;
        this.role = Role;
    }

    public Account(int accId, String email, String photo, String password, String fullName, int status, String phone, int role) {
        this.accId = accId;
        this.email = email;
        this.photo = photo;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
        this.phone = phone;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAccId() {
        return accId;
    }

    public String getSex() {
        if(sex==1) return "Male";
        return "Female";
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getBirtDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(birtDay);
        return formattedDate;
    }

    public void setBirtDay(Date birtDay) {
        this.birtDay = birtDay;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {

        return "Account{" + "accId=" + accId + ", email=" + email + ", photo=" + photo + ", userName=" + userName + ", password=" + password + ", fullName=" + fullName + ", status=" + status + ", sex=" + getSex() + ", phone=" + phone + ", role=" + role + ", address=" + address + ", birtDay=" + getBirtDay() + '}';
    }

}
