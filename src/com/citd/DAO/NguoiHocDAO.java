/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citd.DAO;

import com.citd.uitls.JdbcHelper;
import com.citd.Entity.NguoiHoc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NguoiHocDAO extends CitdDAO<NguoiHoc, String>{
    String INSERT_SQL = "INSERT INTO NguoiHoc(MaNH,HoTen,NgaySinh,GioiTinh,DienThoai,Email,GhiChu,MaNV,NgayDK) VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?  WHERE MaNH=?";
    String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH=?";
    String DELETE_SQL2 = "DELETE FROM HocVien WHERE MaNH=?";
    String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH=?";

    @Override
    public void insert(NguoiHoc entity) {       
        try {
            JdbcHelper.update(INSERT_SQL,
                    entity.getMaNH(),entity.getHoTen(),entity.getNgaySinh(),entity.getGioiTinh(),
                    entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK());
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(NguoiHoc entity) {
        try {
            JdbcHelper.update(UPDATE_SQL,
                    entity.getHoTen(),entity.getNgaySinh(),entity.getGioiTinh(),
                    entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNH());
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(String id) {        
        try {
            //JdbcHelper.update(DELETE_SQL2, id);
            JdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<NguoiHoc>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);           
            while(rs.next()){
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                list.add(entity);
            }    
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<NguoiHoc> selectByKeyword(String keyword){
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? or MaNH LIKE ? or DienThoai LIKE ? or Email LIKE ? or MaNV LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%","%" + keyword + "%","%" + keyword + "%","%" + keyword + "%","%" + keyword + "%");
    }
    
    public List<NguoiHoc> selectNotInCourse(int makh,String keyword){
        String sql = "SELECT * FROM NguoiHoc "
                + " WHERE (HoTen LIKE ? or MaNH LIKE ? or DienThoai LIKE ? or Email LIKE ?) AND "
                + " MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return this.selectBySql(sql, "%" + keyword + "%","%" + keyword + "%","%" + keyword + "%","%" + keyword + "%", makh);
    }
    
}
