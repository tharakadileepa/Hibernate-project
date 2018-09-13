/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author THARAKA
 */

@Entity
@Table(name = "buyingorder")
public class BuyingOrder {

    @Id
    private String ordid;
//    private String cusid;
    @Temporal(TemporalType.DATE)
    private Date orddate;

    @OneToMany(mappedBy = "buyingOrder", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<BuyingOrderDetail> buyingOrderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cusid",referencedColumnName = "cusid", insertable = false,updatable = false)
    private Customer customer;
    public BuyingOrder() {
    }

    public BuyingOrder(String ordid, Date orddate, Customer customer) {
        this.ordid = ordid;
        this.orddate = orddate;
        this.customer = customer;
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
     * @return the cusid
     */

    public Date getOrddate() {
        return orddate;
    }

    /**
     * @param orddate the orddate to set
     */
    public void setOrddate(Date orddate) {
        this.orddate = orddate;
    }

    public List<BuyingOrderDetail> getBuyingOrderDetails() {
        return buyingOrderDetails;
    }

    public void setBuyingOrderDetails(List<BuyingOrderDetail> buyingOrderDetails) {
        this.buyingOrderDetails = buyingOrderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "BuyingOrder{" +
                "ordid='" + ordid + '\'' +
                ", orddate=" + orddate +
                ", customer=" + customer +
                '}';
    }
}
