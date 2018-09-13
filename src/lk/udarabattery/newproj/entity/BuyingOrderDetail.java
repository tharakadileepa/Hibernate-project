/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author THARAKA
 */
@Entity
@Table(name = "buyingorderdetail")
public class BuyingOrderDetail {

    @EmbeddedId
    private BuyingOrderDetail_PK buyingOrderDetail_PK;
    private BigDecimal bprice;

    @ManyToOne
    @JoinColumn(name="bcode", referencedColumnName = "bcode", insertable = false,updatable = false)
    private Battery battery;

    @ManyToOne
    @JoinColumn(name="ordid", referencedColumnName = "ordid", insertable = false,updatable = false)
    private BuyingOrder buyingOrder;

    public BuyingOrderDetail() {
    }

    public BuyingOrderDetail(BuyingOrderDetail_PK buyingOrderDetail_PK, BigDecimal bprice) {
        this.buyingOrderDetail_PK = buyingOrderDetail_PK;
        this.bprice = bprice;
    }

    public BuyingOrderDetail(String bcode,String ordid,BigDecimal bprice) {
       this.buyingOrderDetail_PK=new BuyingOrderDetail_PK(bcode, ordid);
       this.bprice=bprice;
    }

    

    /**
     * @return the buyingOrderDetail_PK
     */
    public BuyingOrderDetail_PK getBuyingOrderDetail_PK() {
        return buyingOrderDetail_PK;
    }

    /**
     * @param buyingOrderDetail_PK the buyingOrderDetail_PK to set
     */
    public void setBuyingOrderDetail_PK(BuyingOrderDetail_PK buyingOrderDetail_PK) {
        this.buyingOrderDetail_PK = buyingOrderDetail_PK;
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

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public BuyingOrder getBuyingOrder() {
        return buyingOrder;
    }

    public void setBuyingOrder(BuyingOrder buyingOrder) {
        this.buyingOrder = buyingOrder;
    }

    @Override
    public String toString() {
        return "BuyingOrderDetail{" + "buyingOrderDetail_PK=" + buyingOrderDetail_PK + ", bprice=" + bprice + '}';
    }

}
