/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.business;

import lk.udarabattery.newproj.business.custom.impl.BatteryBOImpl;
import lk.udarabattery.newproj.business.custom.impl.BuyingOrderBOImpl;
import lk.udarabattery.newproj.business.custom.impl.BuyingOrderDetailBOImpl;
import lk.udarabattery.newproj.business.custom.impl.CustomerBOImpl;
import lk.udarabattery.newproj.business.custom.impl.OrderBOimpl;

/**
 *
 * @author THARAKA
 */
public class BOFactory {

    private static BOFactory bOFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (bOFactory == null) {
            bOFactory = new BOFactory();
        }
        return bOFactory;

    }

    public enum BOTypes {
        BATTERY, BUYINGORDER, BUYINGORDERDETAIL, CUSTOMER, order;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case BATTERY:
                return new BatteryBOImpl();
            case BUYINGORDER:
                return new BuyingOrderBOImpl();
            case BUYINGORDERDETAIL:
                return new BuyingOrderDetailBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case order:
                return new OrderBOimpl();
            default:
                return null;

        }

    }

}
