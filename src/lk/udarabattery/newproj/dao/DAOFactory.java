/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.dao;

import lk.udarabattery.newproj.dao.custom.impl.BatteryDAOImpl;
import lk.udarabattery.newproj.dao.custom.impl.BuyingOrderDAOImpl;
import lk.udarabattery.newproj.dao.custom.impl.BuyingOrderDetailDAOImpl;
import lk.udarabattery.newproj.dao.custom.impl.CustomerDAOImpl;

/**
 *
 * @author THARAKA
 */
public class DAOFactory {
    
    private static DAOFactory dAOFactory;
    

    private DAOFactory(){
    }
    
    public static DAOFactory getInstance(){
        if (dAOFactory==null){
            dAOFactory=new DAOFactory();
        }
        return dAOFactory;
    }
    
    public enum DAOTypes{
        BATTERY,BUYINGORDER,BUYINGORDERDETAIL,CUSTOMER;
    }
    
    public SuperDAO getDAO(DAOTypes types){
        switch(types)
        {
            case BATTERY:
                return new BatteryDAOImpl();
            case BUYINGORDER:
                return new BuyingOrderDAOImpl();
            case BUYINGORDERDETAIL:
                return new BuyingOrderDetailDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;    
        }
        
        
        
    }
    
    
    
}
