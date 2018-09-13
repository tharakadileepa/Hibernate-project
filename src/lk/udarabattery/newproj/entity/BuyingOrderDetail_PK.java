/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author THARAKA
 */

@Embeddable
public class BuyingOrderDetail_PK implements Serializable {
    private String bcode;
    private String ordid;

    public BuyingOrderDetail_PK() {
    }

    public BuyingOrderDetail_PK(String bcode, String ordid) {
        this.bcode = bcode;
        this.ordid = ordid;
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
     * @return the ordid
     */
    public String getOrdid() {
        return ordid;
    }

    /**
     * @param ordid the ordid to set
     */
    public void setOrdid(String ordid) {
        this.ordid = ordid;
    }

  
    
}
