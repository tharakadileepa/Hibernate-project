/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lk.udarabattery.newproj.buisness.custom.BuyingorderdetailBO;
import lk.udarabattery.newproj.dao.DAOFactory;
import lk.udarabattery.newproj.dao.custom.BuyingOrderDetailDAO;
import lk.udarabattery.newproj.dao.custom.CustomerDAO;
import lk.udarabattery.newproj.database.HibernateUtil;
import lk.udarabattery.newproj.dto.BuyingOrderDetailDTO;
import lk.udarabattery.newproj.dto.CustomerDTO;
import lk.udarabattery.newproj.entity.BuyingOrderDetail;
import lk.udarabattery.newproj.entity.BuyingOrderDetail_PK;
import lk.udarabattery.newproj.entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author THARAKA
 */
public class BuyingOrderDetailBOImpl implements BuyingorderdetailBO{

    private BuyingOrderDetailDAO buyingOrderDetailDAO;
    private SessionFactory sessionFactory;

    public BuyingOrderDetailBOImpl() {
        this.buyingOrderDetailDAO =(BuyingOrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BUYINGORDERDETAIL);
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    @Override
    public boolean saveBuyingOrderDetail(BuyingOrderDetailDTO bod) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            buyingOrderDetailDAO.setSession(session);
            session.beginTransaction();
            BuyingOrderDetail buyingOrderDetail = new BuyingOrderDetail(bod.getBcode(),bod.getOrdid(),bod.getBprice());
            buyingOrderDetailDAO.save(buyingOrderDetail);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }

   

    @Override
    public BuyingOrderDetailDTO findID(String bcode, String ordid) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            buyingOrderDetailDAO.setSession(session);
            session.beginTransaction();
            BuyingOrderDetail bod = buyingOrderDetailDAO.findID(new BuyingOrderDetail_PK(bcode, ordid));
            BuyingOrderDetailDTO buyingOrderDetailDTO=new BuyingOrderDetailDTO(bod.getBuyingOrderDetail_PK().getBcode(),bod.getBuyingOrderDetail_PK().getOrdid(),bod.getBprice());
            session.getTransaction().commit();
            return buyingOrderDetailDTO;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public ArrayList<BuyingOrderDetailDTO> getAllBuyingOrder() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            buyingOrderDetailDAO.setSession(session);
            session.beginTransaction();

            List<BuyingOrderDetail> buyingOrderDetails = buyingOrderDetailDAO.loadAll();

            session.getTransaction().commit();

            ArrayList<BuyingOrderDetailDTO> dtos = new ArrayList<>();



            for (BuyingOrderDetail orderDetail : buyingOrderDetails) {
                BuyingOrderDetailDTO buyingOrderDetailDTO=new BuyingOrderDetailDTO(orderDetail.getBuyingOrderDetail_PK().getBcode(),orderDetail.getBuyingOrderDetail_PK().getOrdid(),orderDetail.getBprice());
                dtos.add(buyingOrderDetailDTO);
            }

            return dtos;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public boolean deleteBuyingOrderDetail(String bcode, String ordid) throws Exception {

        try (Session session = sessionFactory.openSession()) {
            buyingOrderDetailDAO.setSession(session);
            session.beginTransaction();
            buyingOrderDetailDAO.delete(new BuyingOrderDetail_PK(bcode,ordid));
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }
    
}
