/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citd.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.citd.uitls.JdbcHelper;
/**
 *
 * @author Admin
 */
public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs =  JdbcHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i = 0; i < cols.length; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;          
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    
    }
    
    public List<Object[]> getBangDiem(Integer makh){
        String sql = "{CALL sp_BangDiem(?)}";
        String[] cols = {"MaNH","HoTen","Diem"};
        return this.getListOfArray(sql, cols, makh);
    }
    
    public List<Object[]> getLuongNguoiHoc(){
        String sql = "{CALL sp_LuongNguoiHoc}";
        String[] cols = {"Nam","SoLuong","DauTien","CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<Object[]> getDiemChuyenDe(){
        String sql = "{CALL sp_DiemChuyenDe}";
        String[] cols = {"ChuyenDe","SoHV","ThapNhat","CaoNhat","TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<Object[]> getDoanhThu(int nam){
        String sql = "{CALL sp_DoanhThu(?)}";
        String[] cols = {"ChuyenDe","SoKH","SoHV","DoanhThu","ThapNhat","CaoNhat","TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }
    
    public List<Object[]> getAllBangDiem(){
        String sql = "SELECT nh.MaNH, nh.HoTen, hv.Diem FROM HocVien hv "
                + "JOIN NguoiHoc nh ON nh.MaNH = hv.MaNH "
                + "ORDER BY hv.Diem DESC";
        String[] cols = {"MaNH","HoTen","Diem"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<Object[]> getThongTin(String maNH,int maKH){
        String sql = "select NguoiHoc.MaNH,HoTen,Diem,KhoaHoc.MaKH,Email " +
                        "from HocVien " +
                        "join NguoiHoc on NguoiHoc.MaNH = HocVien.MaNH " +
                        "join KhoaHoc on KhoaHoc.MaKH = HocVien.MaKH " +
                        "where  HocVien.MaNH = ? and HocVien.MaKH = ?";
        String[] cols = {"MaNH","HoTen","Diem","MaKH","Email"};
        return this.getListOfArray(sql, cols, maNH, maKH);
    }
    
}
