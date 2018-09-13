/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.view.util.tblmodel;

/**
 *
 * @author THARAKA
 */
public class CustomerTM {
    private String cusId;
    private String cusName;
    private String nicNO;
    private String cusContact;
    private String cusAddress;

    public CustomerTM() {
    }

    public CustomerTM(String cusId, String cusName, String nicNO, String cusContact, String cusAddress) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.nicNO = nicNO;
        this.cusContact = cusContact;
        this.cusAddress = cusAddress;
    }

    /**
     * @return the cusId
     */
    public String getCusId() {
        return cusId;
    }

    /**
     * @param cusId the cusId to set
     */
    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    /**
     * @return the cusName
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * @param cusName the cusName to set
     */
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * @return the nicNO
     */
    public String getNicNO() {
        return nicNO;
    }

    /**
     * @param nicNO the nicNO to set
     */
    public void setNicNO(String nicNO) {
        this.nicNO = nicNO;
    }

    /**
     * @return the cusContact
     */
    public String getCusContact() {
        return cusContact;
    }

    /**
     * @param cusContact the cusContact to set
     */
    public void setCusContact(String cusContact) {
        this.cusContact = cusContact;
    }

    /**
     * @return the cusAddress
     */
    public String getCusAddress() {
        return cusAddress;
    }

    /**
     * @param cusAddress the cusAddress to set
     */
    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    @Override
    public String toString() {
        return "CustomerTM{" + "cusId=" + cusId + ", cusName=" + cusName + ", nicNO=" + nicNO + ", cusContact=" + cusContact + ", cusAddress=" + cusAddress + '}';
    }
    
    
}
