/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.buisness.custom;

import java.util.ArrayList;
import lk.udarabattery.newproj.business.SuperBO;
import lk.udarabattery.newproj.dto.CustomerDTO;

/**
 *
 * @author THARAKA
 */
public interface CustomerBO extends SuperBO{
    public boolean saveCustomer(CustomerDTO dto) throws Exception;   
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception;
    public boolean deleteCustomer(String Id) throws Exception;
    public CustomerDTO findID(String id)throws Exception;
}
