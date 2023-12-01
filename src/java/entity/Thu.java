/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author datka
 */
public class Thu {

    private String tenNguoiGui;
    private String nguoiNhan;
    private Date ngayGui;
    private double soTien;
    private String noiDung;
    private VolunteerActivity hoatdong;
    public Thu() {
    }

    public Thu(String tenNguoiGui, String nguoiNhan, Date ngayGui, double soTien, String noiDung, VolunteerActivity hoatdong) {
        this.tenNguoiGui = tenNguoiGui;
        this.nguoiNhan = nguoiNhan;
        this.ngayGui = ngayGui;
        this.soTien = soTien;
        this.noiDung = noiDung;
        this.hoatdong = hoatdong;
    }

    public VolunteerActivity getHoatdong() {
        return hoatdong;
    }

    public void setHoatdong(VolunteerActivity hoatdong) {
        this.hoatdong = hoatdong;
    }

   

    public String getTenNguoiGui() {
        return tenNguoiGui;
    }

    public void setTenNguoiGui(String tenNguoiGui) {
        this.tenNguoiGui = tenNguoiGui;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public Date getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(Date ngayGui) {
        this.ngayGui = ngayGui;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    
}
