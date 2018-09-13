/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lk.udarabattery.newproj.buisness.custom.CustomerBO;
import lk.udarabattery.newproj.dao.DAOFactory;
import lk.udarabattery.newproj.dao.custom.CustomerDAO;
import lk.udarabattery.newproj.database.HibernateUtil;
import lk.udarabattery.newproj.dto.CustomerDTO;
import lk.udarabattery.newproj.entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author THARAKA
 */
public class CustomerBOImpl implements CustomerBO{

    private CustomerDAO customerDAO;
    private SessionFactory sessionFactory;

    public CustomerBOImpl() {
        this.customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    CustomerDAO cdto=(CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            customerDAO.setSession(session);
            session.beginTransaction();
            Customer customer = new Customer(dto.getCusid(), dto.getCusname(), dto.getNicno(),dto.getCuscontact(),dto.getCusaddress());
            customerDAO.save(customer);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            customerDAO.setSession(session);
            session.beginTransaction();

            List<Customer> allCustomers = customerDAO.loadAll();

            session.getTransaction().commit();

            ArrayList<CustomerDTO> dtos = new ArrayList<>();

            for (Customer customer : allCustomers) {
                CustomerDTO customerDTO = new CustomerDTO(customer.getCusid(),customer.getCusname(),customer.getNicno(),customer.getCuscontact(),customer.getCusaddress());
                dtos.add(customerDTO);
            }

            return dtos;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public boolean deleteCustomer(String Id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            customerDAO.setSession(session);
            session.beginTransaction();
            customerDAO.delete(Id);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }


    @Override
    public CustomerDTO findID(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            customerDAO.setSession(session);
            session.beginTransaction();
            Customer daoid = customerDAO.findID(id);
            CustomerDTO customerDTO=new CustomerDTO(daoid.getCusid(),daoid.getCusname(),daoid.getNicno(),daoid.getCuscontact(),daoid.getCusaddress());
            session.getTransaction().commit();
            return customerDTO;
        }catch (HibernateException exp){
            return null;
        }
    }

}
