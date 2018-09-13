/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.udarabattery.newproj.buisness.custom.BuyingorderBO;
import lk.udarabattery.newproj.dao.DAOFactory;
import lk.udarabattery.newproj.dao.custom.BuyingOrderDAO;
import lk.udarabattery.newproj.dao.custom.BuyingOrderDetailDAO;
import lk.udarabattery.newproj.dao.custom.CustomerDAO;
import lk.udarabattery.newproj.database.HibernateUtil;
import lk.udarabattery.newproj.dto.BatteryDTO;
import lk.udarabattery.newproj.dto.BuyingOrderDTO;
import lk.udarabattery.newproj.dto.CustomerDTO;
import lk.udarabattery.newproj.entity.BuyingOrder;
import lk.udarabattery.newproj.entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author THARAKA
 */
public class BuyingOrderBOImpl implements BuyingorderBO{

    private BuyingOrderDAO buyingOrderDAO;
    private CustomerDAO customerDAO;
    private SessionFactory sessionFactory;

    public BuyingOrderBOImpl() {
        this.buyingOrderDAO =(BuyingOrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BUYINGORDER);
        this.customerDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    
    @Override
    public boolean saveBuyingOrder(BuyingOrderDTO bo) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            buyingOrderDAO.setSession(session);
            customerDAO.setSession(session);
            session.beginTransaction();
            Customer customer=customerDAO.findID(bo.getCusid());
            BuyingOrder buyingOrder=new BuyingOrder(bo.getOrdid(),bo.getOrddate(),customer);
            buyingOrderDAO.save(buyingOrder);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }


    @Override
    public BuyingOrderDTO findID(String id) throws Exception {
       try (Session session = sessionFactory.openSession()) {
            buyingOrderDAO.setSession(session);
           customerDAO.setSession(session);
            session.beginTransaction();
           BuyingOrder orders = buyingOrderDAO.findID(id);
           BuyingOrderDTO buyingOrderDTO=new BuyingOrderDTO(orders.getOrdid(),orders.getCustomer().getCusid(),orders.getOrddate());
           session.getTransaction().commit();
            return buyingOrderDTO;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public ArrayList<BuyingOrderDTO> getAllBuyingOrder() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            buyingOrderDAO.setSession(session);
            session.beginTransaction();

            List<BuyingOrder> buyingOrders = buyingOrderDAO.loadAll();

            session.getTransaction().commit();

            ArrayList<BuyingOrderDTO> dtos = new ArrayList<>();



            for (BuyingOrder order : buyingOrders) {
                BuyingOrderDTO buyingOrderDTO=new BuyingOrderDTO(order.getOrdid(),order.getCustomer().getCusid(),order.getOrddate());
                dtos.add(buyingOrderDTO);
            }

            return dtos;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public boolean deleteBuyingOrder(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            buyingOrderDAO.setSession(session);
            session.beginTransaction();
            buyingOrderDAO.delete(id);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }
    
}
