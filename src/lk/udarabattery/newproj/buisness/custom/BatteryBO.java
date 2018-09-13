/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.buisness.custom;

import java.util.ArrayList;
import lk.udarabattery.newproj.business.SuperBO;
import lk.udarabattery.newproj.dto.BatteryDTO;

/**
 *
 * @author THARAKA
 */
public interface BatteryBO extends SuperBO{
    public boolean saveBattery(BatteryDTO battery)throws Exception;
    public boolean deleteBattery(String id)throws Exception;
    public BatteryDTO findID(String id)throws Exception;
    public ArrayList<BatteryDTO> getAllBattery() throws Exception;
}
