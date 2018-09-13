/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import lk.udarabattery.newproj.database.DBconnection;


/**
 *
 * @author ranjith-suranga
 */
public class CrudUtil {
    
    private static PreparedStatement getPreparedStatement(String sql, Object... params) throws Exception{
        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        for(int i=0;i < params.length;i++){
            System.out.println(params[i]);
            pstm.setObject(i+1, params[i]);
        }

        return pstm;
    }
    
    public static ResultSet executeQuery(String sql, Object... params) throws Exception{
        
        return getPreparedStatement(sql, params).executeQuery();
        
    }
    
    public static boolean executeUpdate(String sql, Object... params) throws Exception{
        
        return getPreparedStatement(sql, params).executeUpdate() > 0;
        
    }
    
}
