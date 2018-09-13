/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.buisness.custom;

import lk.udarabattery.newproj.business.SuperBO;
import lk.udarabattery.newproj.dto.BuyingOrderDTO;
import lk.udarabattery.newproj.dto.BuyingOrderDetailDTO;

/**
 *
 * @author THARAKA
 */
public interface OrderBO extends SuperBO{
    public boolean placeorder(BuyingOrderDTO bo,BuyingOrderDetailDTO bod) throws Exception;
}
