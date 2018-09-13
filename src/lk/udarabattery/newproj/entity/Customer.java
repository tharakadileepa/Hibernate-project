/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.entity;

import javax.persistence.CascadeType;
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
public class Customer {

    @Id
 private String cusid;
 private String cusname;
 private String nicno;
 private String cuscontact;
 private String cusaddress;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "customer")
    private
    List<BuyingOrder> orders = new ArrayList<>();

    public Customer(String cusid, String cusname, String nicno, String cuscontact, String cusaddress) {
        this.cusid = cusid;
        this.cusname = cusname;
        this.nicno = nicno;
        this.cuscontact = cuscontact;
        this.cusaddress = cusaddress;
    }

    public Customer() {
    }

    /**
     * @return the cusid
     */
    public String getCusid() {
        return cusid;
    }

    /**
     * @param cusid the cusid to set
     */
    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    /**
     * @return the cusname
     */
    public String getCusname() {
        return cusname;
    }

    /**
     * @param cusname the cusname to set
     */
    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    /**
     * @return the nicno
     */
    public String getNicno() {
        return nicno;
    }

    /**
     * @param nicno the nicno to set
     */
    public void setNicno(String nicno) {
        this.nicno = nicno;
    }

    /**
     * @return the cuscontact
     */
    public String getCuscontact() {
        return cuscontact;
    }

    /**
     * @param cuscontact the cuscontact to set
     */
    public void setCuscontact(String cuscontact) {
        this.cuscontact = cuscontact;
    }

    /**
     * @return the cusaddress
     */
    public String getCusaddress() {
        return cusaddress;
    }

    /**
     * @param cusaddress the cusaddress to set
     */
    public void setCusaddress(String cusaddress) {
        this.cusaddress = cusaddress;
    }

    public List<BuyingOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<BuyingOrder> orders) {
        this.orders = orders;
    }

    public void addOrder(BuyingOrder order){
        order.setCustomer(this);
        this.getOrders().add(order);
    }

    @Override
    public String toString() {
        return "Customer{" + "cusid=" + cusid + ", cusname=" + cusname + ", nicno=" + nicno + ", cuscontact=" + cuscontact + ", cusaddress=" + cusaddress + '}';
    }


}