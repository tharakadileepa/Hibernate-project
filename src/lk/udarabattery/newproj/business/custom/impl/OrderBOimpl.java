/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.business.custom.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import lk.udarabattery.newproj.buisness.custom.OrderBO;
import lk.udarabattery.newproj.dao.DAOFactory;
import lk.udarabattery.newproj.dao.custom.BuyingOrderDAO;
import lk.udarabattery.newproj.dao.custom.BuyingOrderDetailDAO;
import lk.udarabattery.newproj.dao.custom.CustomerDAO;
import lk.udarabattery.newproj.database.DBconnection;
import lk.udarabattery.newproj.database.HibernateUtil;
import lk.udarabattery.newproj.dto.BuyingOrderDTO;
import lk.udarabattery.newproj.dto.BuyingOrderDetailDTO;
import lk.udarabattery.newproj.entity.BuyingOrder;
import lk.udarabattery.newproj.entity.BuyingOrderDetail;
import lk.udarabattery.newproj.entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author THARAKA
 */
public class OrderBOimpl implements OrderBO {

    private BuyingOrderDAO buyingOrderDAO;
    private BuyingOrderDetailDAO buyingOrderDetailDAO;
    private CustomerDAO customerDAO;
    private SessionFactory sessionFactory;


    public OrderBOimpl() {
        this.buyingOrderDAO= (BuyingOrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BUYINGORDER);
        this.buyingOrderDetailDAO= (BuyingOrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BUYINGORDERDETAIL);
        this.customerDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public boolean placeorder(BuyingOrderDTO bo, BuyingOrderDetailDTO bod) throws Exception {


            try (Session session=sessionFactory.openSession()){
            buyingOrderDAO.setSession(session);
            buyingOrderDetailDAO.setSession(session);
            customerDAO.setSession(session);
            session.beginTransaction();
            Customer customer = customerDAO.findID(bo.getCusid());
            BuyingOrder buyingOrder=new BuyingOrder(bo.getOrdid(),bo.getOrddate(),customer);
            buyingOrderDAO.save(buyingOrder);
            BuyingOrderDetail buyingOrderDetail = new BuyingOrderDetail(bod.getBcode(),bod.getOrdid(),bod.getBprice());
            buyingOrderDetailDAO.save(buyingOrderDetail);
            session.getTransaction().commit();
            return true;
    }/*catch (
    HibernateException exp){
        return false;
    }*/


//       Connection con=null;
//       try{
//       con = DBconnection.getInstance().getConnection();
//       con.setAutoCommit(false);
//         BuyingOrderDTO bb=new BuyingOrderDTO(bo.getOrdid(), bo.getCusid(), bo.getOrddate());
//         boolean result1 = bOImpl.saveBuyingOrder(bb);
//
//            if (result1 == true) {
//
//               BuyingOrderDetailDTO detailDTO=new BuyingOrderDetailDTO(bod.getBcode(), bod.getOrdid(),bod.getBprice());
//               boolean result2 = bOdImpl.saveBuyingOrderDetail(detailDTO);
//
//                    if (result2) {
//                        con.commit();
//                        return true;
//                    }
//                    else{
//                        con.rollback();
//                        return  result2;
//                    }
//
//            } else {
//                con.rollback();
//                return false;
//
//            }
//
//        } catch (Exception e) {
//            con.rollback();
//            throw e;
//        } finally {
//
//            con.setAutoCommit(true);
//
//        }
//

    }

}
       