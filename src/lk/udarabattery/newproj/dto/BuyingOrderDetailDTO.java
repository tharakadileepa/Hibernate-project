/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.dto;

import java.math.BigDecimal;

/**
 *
 * @author THARAKA
 */
public class BuyingOrderDetailDTO {
    private String bcode;
    private String ordid;
    private BigDecimal bprice;

    public BuyingOrderDetailDTO() {
    }

    public BuyingOrderDetailDTO(String bcode, String ordid) {
        this.bcode = bcode;
        this.ordid = ordid;
    }

    public BuyingOrderDetailDTO(String bcode, String ordid, BigDecimal bprice) {
        this.bcode = bcode;
        this.ordid = ordid;
        this.bprice = bprice;
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

    /**
     * @return the bprice
     */
    public BigDecimal getBprice() {
        return bprice;
    }

    /**
     * @param bprice the bprice to set
     */
    public void setBprice(BigDecimal bprice) {
        this.bprice = bprice;
    }

    @Override
    public String toString() {
        return "BuyingOrderDetailDTO{" + "bcode=" + bcode + ", ordid=" + ordid + ", bprice=" + bprice + '}';
    }
    
    
}
