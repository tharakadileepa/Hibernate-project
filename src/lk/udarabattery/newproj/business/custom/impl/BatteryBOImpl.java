/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.udarabattery.newproj.buisness.custom.BatteryBO;
import lk.udarabattery.newproj.dao.DAOFactory;
import lk.udarabattery.newproj.dao.SuperDAO;
import lk.udarabattery.newproj.dao.custom.BatteryDAO;
import lk.udarabattery.newproj.database.HibernateUtil;
import lk.udarabattery.newproj.dto.BatteryDTO;
import lk.udarabattery.newproj.dto.BuyingOrderDTO;
import lk.udarabattery.newproj.entity.Battery;
import lk.udarabattery.newproj.entity.BuyingOrder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author THARAKA
 */
public class BatteryBOImpl implements BatteryBO{

    private BatteryDAO batteryDAO;
    private SessionFactory sessionFactory;

    public BatteryBOImpl(){
        this.batteryDAO = (BatteryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BATTERY);
        sessionFactory= HibernateUtil.getSessionFactory();
    }


    @Override
    public boolean saveBattery(BatteryDTO battery) throws Exception {
        try (Session session = sessionFactory.openSession()){
            batteryDAO.setSession(session);
            session.beginTransaction();
            Battery battery1=new Battery(battery.getBcode(),battery.getBtype(),battery.getBcategory());
            batteryDAO.save(battery1);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }

    }


    @Override
    public BatteryDTO findID(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            batteryDAO.setSession(session);
            session.beginTransaction();
            Battery batteries = batteryDAO.findID(id);
            BatteryDTO batteryDTO=new BatteryDTO(batteries.getBcode(),batteries.getBtype(),batteries.getBcategory());
            session.getTransaction().commit();
            return batteryDTO;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public ArrayList<BatteryDTO> getAllBattery() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            batteryDAO.setSession(session);
            session.beginTransaction();

            List<Battery> batteries = batteryDAO.loadAll();

            session.getTransaction().commit();

            ArrayList<BatteryDTO> dtos = new ArrayList<>();



            for (Battery battery : batteries) {
                BatteryDTO batteryDTO=new BatteryDTO(battery.getBcode(),battery.getBtype(),battery.getBcategory());
                dtos.add(batteryDTO);
            }

            return dtos;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public boolean deleteBattery(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            batteryDAO.setSession(session);
            session.beginTransaction();
            batteryDAO.delete(id);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }
    
}
