/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.buisness.custom;

import java.util.ArrayList;
import lk.udarabattery.newproj.business.SuperBO;
import lk.udarabattery.newproj.dto.BuyingOrderDetailDTO;

/**
 *
 * @author THARAKA
 */
public interface BuyingorderdetailBO extends SuperBO{
    public boolean saveBuyingOrderDetail(BuyingOrderDetailDTO bod)throws Exception;
    public boolean deleteBuyingOrderDetail(String bcode,String ordid)throws Exception;
    public BuyingOrderDetailDTO findID(String bcode,String ordid)throws Exception;
    public ArrayList<BuyingOrderDetailDTO> getAllBuyingOrder() throws Exception;
}
