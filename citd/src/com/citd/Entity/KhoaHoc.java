/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citd.Entity;

import java.util.Date;
import com.citd.uitls.XDate;

/**
 *
 * @author Admin
 */
public class KhoaHoc {
    private int MaKH;
    private String MaCD;
    private double HocPhi;
    private int Thoiluong;
    private Date NgayKG;
    private String GhiChu;
    private String MaNV;
    private Date NgayTao;

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaCD() {
        return MaCD;
    }

    public void setMaCD(String MaCD) {
        this.MaCD = MaCD;
    }

    public double getHocPhi() {
        return HocPhi;
    }

    public void setHocPhi(double HocPhi) {
        this.HocPhi = HocPhi;
    }

    public int getThoiluong() {
        return Thoiluong;
    }

    public void setThoiluong(int Thoiluong) {
        this.Thoiluong = Thoiluong;
    }

    public Date getNgayKG() {
        return NgayKG;
    }

    public void setNgayKG(Date NgayKG) {
        this.NgayKG = NgayKG;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }
    
    @Override
    public String toString(){
        return MaCD + " " + "(" + XDate.toString(NgayKG, "yyyy-MM-dd") + ")";
    }
    
}
