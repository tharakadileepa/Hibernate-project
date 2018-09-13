/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.buisness.custom;

import java.util.ArrayList;
import lk.udarabattery.newproj.business.SuperBO;
import lk.udarabattery.newproj.dto.BuyingOrderDTO;

/**
 *
 * @author THARAKA
 */
public interface BuyingorderBO extends SuperBO{
    public boolean saveBuyingOrder(BuyingOrderDTO bo)throws Exception;
    public boolean deleteBuyingOrder(String id)throws Exception;
    public BuyingOrderDTO findID(String id)throws Exception;
    public ArrayList<BuyingOrderDTO> getAllBuyingOrder() throws Exception;
}
