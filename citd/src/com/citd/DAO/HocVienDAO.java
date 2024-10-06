/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citd.DAO;

import com.citd.Entity.HocVien;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.citd.uitls.JdbcHelper;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class HocVienDAO extends CitdDAO<HocVien, Integer>{

    String INSERT_SQL = "INSERT INTO HocVien(MaKH,MaNH,Diem) VALUES (?,?,?)";
    String UPDATE_SQL = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
    String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV=?";
    String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV=?";
    @Override
    public void insert(HocVien entity) {
        try {
            JdbcHelper.update(INSERT_SQL,
                    entity.getMaKH(),entity.getMaNH(),entity.getDiem());
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(HocVien entity) {
        try {
            JdbcHelper.update(UPDATE_SQL,
                    entity.getMaKH(),entity.getMaNH(),entity.getDiem(),
                    entity.getMaHV());
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            JdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<HocVien>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);           
            while(rs.next()){
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getInt("MaHV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setDiem(rs.getDouble("Diem"));               
                list.add(entity);
            }    
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<HocVien> selectByKhoaHoc(int maKH){
        String sql = "SELECT * FROM HocVien WHERE MaKH=?";
        return this.selectBySql(sql, maKH);
    }
    //-------------------------------
    public static int selectSoSVXuatSac(){
        try {
            ResultSet rs = JdbcHelper.query("select COUNT(diem) as SoSV from HocVien where diem >= 9");
            if(rs.next()){
                return rs.getInt("SoSV");
            }
            rs.getStatement().getConnection().close();
            return -1;          
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1; 
        }
    }
    public static int selectSoSVGioi(){
        try {
            ResultSet rs = JdbcHelper.query("select COUNT(diem) as SoSV from HocVien where diem >= 7.5 and diem < 9");
            if(rs.next()){
                return rs.getInt("SoSV");
            }
            rs.getStatement().getConnection().close();
            return -1; 
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return  -1;
        }
    }
    public static int selectSoSVKha(){
        try {
            ResultSet rs = JdbcHelper.query("select COUNT(diem) as SoSV from HocVien where diem >= 6.5 and diem < 7.5");
            if(rs.next()){
                return rs.getInt("SoSV");
            }
            rs.getStatement().getConnection().close();
            return -1; 
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return  -1;
        }
    }
    public static int selectSoSVTrungBinh(){
        try {
            ResultSet rs = JdbcHelper.query("select COUNT(diem) as SoSV from HocVien where diem >= 5 and diem < 6.5");
            if(rs.next()){
                return rs.getInt("SoSV");
            }
            rs.getStatement().getConnection().close();
            return -1; 
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return  -1;
        }
    }
    public static int selectSoSVChuaDat(){
        try {
            ResultSet rs = JdbcHelper.query("select COUNT(diem) as SoSV from HocVien where diem < 5");
            if(rs.next()){
                return rs.getInt("SoSV");
            }
            rs.getStatement().getConnection().close();
            return -1; 
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return  -1;
        }
    }
    
}
