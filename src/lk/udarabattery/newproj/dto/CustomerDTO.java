/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.dto;

/**
 *
 * @author THARAKA
 */
public class CustomerDTO {
    private String cusid;
    private String cusname;
    private String nicno;
    private String cuscontact;
    private String cusaddress;

    public CustomerDTO() {
    }

    public CustomerDTO(String cusid, String cusname, String nicno, String cuscontact, String cusaddress) {
        this.cusid = cusid;
        this.cusname = cusname;
        this.nicno = nicno;
        this.cuscontact = cuscontact;
        this.cusaddress = cusaddress;
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

    @Override
    public String toString() {
        return "CustomerDTO{" + "cusid=" + cusid + ", cusname=" + cusname + ", nicno=" + nicno + ", cuscontact=" + cuscontact + ", cusaddress=" + cusaddress + '}';
    }
    
    
}
