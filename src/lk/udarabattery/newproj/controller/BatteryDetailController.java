/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.udarabattery.newproj.buisness.custom.BatteryBO;
import lk.udarabattery.newproj.business.BOFactory;
import lk.udarabattery.newproj.business.SuperBO;
import lk.udarabattery.newproj.dto.BatteryDTO;
import lk.udarabattery.newproj.main.StartUp;
import lk.udarabattery.newproj.view.util.tblmodel.BatterydetailTM;

/**
 * FXML Controller class
 *
 * @author THARAKA
 */
public class BatteryDetailController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private TableView<BatterydetailTM> tblBattery;
    @FXML
    private JFXTextField txtbatteryCode;
    @FXML
    private JFXTextField txtbatCategory;
    @FXML
    private JFXTextField txtBatteryType;
    
    private BatteryBO batteryBO;

    public BatteryDetailController() {
        this.batteryBO  = (BatteryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BATTERY);
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tblBattery.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bcode"));
        tblBattery.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("btype"));
        tblBattery.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bcategory"));
        
        LoadAllBattery();
    }    

    @FXML
    private void addonClick(ActionEvent event) {
        String Type=txtBatteryType.getText();
        String Category=txtbatCategory.getText();
         if(Type.matches("^[n|N]{1}[0-9]+$")==false || Category.matches("^[A-Za-z]+$")==false){
            new Alert(Alert.AlertType.ERROR, "Invalid type or category", ButtonType.OK).show();
            return;
        }
         else{
        try {
            BatteryDTO batteryDTO=new BatteryDTO(txtbatteryCode.getText() ,txtBatteryType.getText(), txtbatCategory.getText());
            boolean s = batteryBO.saveBattery(batteryDTO);
            LoadAllBattery();
            if (s) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added succesfully", ButtonType.OK).show();
            }else{
                
                new Alert(Alert.AlertType.ERROR, "not ADded", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(BatteryDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    }

    @FXML
    private void deleteOnClick(ActionEvent event) {
        if(tblBattery.getSelectionModel().getSelectedIndex()>=0){
            deleteBattery();
            LoadAllBattery();
            
            
        }else{
            new Alert(Alert.AlertType.ERROR, "Please select a Battery to delete..", ButtonType.OK).show();
        }
        
    }

    @FXML
    private void searchOnClick(ActionEvent event) {
        try {
            String BatteryCode = txtbatteryCode.getText();
            BatteryDTO batteryDTO = batteryBO.findID(BatteryCode);
            txtBatteryType.setText(batteryDTO.getBtype());
            txtbatCategory.setText(batteryDTO.getBcategory());
        } catch (Exception ex) {
            Logger.getLogger(BatteryDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteBattery() {
        try {
            BatterydetailTM item = tblBattery.getSelectionModel().getSelectedItem();
            String id = item.getBcode();
            
            boolean result = batteryBO.deleteBattery(id);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Battery Item has been deleted successfully..", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error when deleting Item..", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(BatteryDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoadAllBattery() {
        try {
            ArrayList<BatteryDTO> allItems = batteryBO.getAllBattery();
            ArrayList<BatterydetailTM> addItems = new ArrayList<>();
            for (BatteryDTO allItem : allItems) {
                BatterydetailTM item = new BatterydetailTM(allItem.getBcode(), allItem.getBtype(), allItem.getBcategory());
                addItems.add(item);
            }
            tblBattery.setItems(FXCollections.observableArrayList(addItems));
        } catch (Exception ex) {
            Logger.getLogger(BatteryDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }
    
}
