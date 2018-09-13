/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.view.util.tblmodel;

import java.math.BigDecimal;

/**
 *
 * @author THARAKA
 */
public class BatterySaleFormTM {
    private String code;
    private String type;
    private String category;
    private BigDecimal price;

    public BatterySaleFormTM() {
    }

    public BatterySaleFormTM(String code, String type, String category, BigDecimal price) {
        this.code = code;
        this.type = type;
        this.category = category;
        this.price = price;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BatterySaleFormTM{" + "code=" + code + ", type=" + type + ", category=" + category + ", price=" + price + '}';
    }
    
    
}