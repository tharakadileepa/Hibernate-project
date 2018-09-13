/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THARAKA
 */

@Entity
public class Battery {

    @Id
    private String bcode;
    private String btype;
    private String bcategory;

    @OneToMany(mappedBy = "battery")
    private
    List<BuyingOrderDetail> buyingOrderDetails = new ArrayList<>();

    public Battery() {
    }

    public Battery(String bcode, String btype, String bcategory) {
        this.bcode = bcode;
        this.btype = btype;
        this.bcategory = bcategory;
    }

    /**
     * @return the bcode
     */
    public String getBcode() {
        return bcode;
    }

    /**
     * @param bcode the bcode to set
     */
    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    /**
     * @return the btype
     */
    public String getBtype() {
        return btype;
    }

    /**
     * @param btype the btype to set
     */
    public void setBtype(String btype) {
        this.btype = btype;
    }

    /**
     * @return the bcategory
     */
    public String getBcategory() {
        return bcategory;
    }

    /**
     * @param bcategory the bcategory to set
     */
    public void setBcategory(String bcategory) {
        this.bcategory = bcategory;
    }

    public List<BuyingOrderDetail> getBuyingOrderDetails() {
        return buyingOrderDetails;
    }

    public void setBuyingOrderDetails(List<BuyingOrderDetail> buyingOrderDetails) {
        this.buyingOrderDetails = buyingOrderDetails;
    }

    @Override
    public String toString() {
        return "Battery{" + "bcode=" + bcode + ", btype=" + btype + ", bcategory=" + bcategory + '}';
    }


}

   
  