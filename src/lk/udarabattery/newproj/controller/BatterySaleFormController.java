/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import com.sun.media.sound.AlawCodec;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.udarabattery.newproj.buisness.custom.BatteryBO;
import lk.udarabattery.newproj.buisness.custom.BuyingorderBO;
import lk.udarabattery.newproj.buisness.custom.BuyingorderdetailBO;
import lk.udarabattery.newproj.buisness.custom.CustomerBO;
import lk.udarabattery.newproj.buisness.custom.OrderBO;
import lk.udarabattery.newproj.business.BOFactory;
import lk.udarabattery.newproj.database.DBconnection;
import lk.udarabattery.newproj.dto.BatteryDTO;
import lk.udarabattery.newproj.dto.BuyingOrderDTO;
import lk.udarabattery.newproj.dto.BuyingOrderDetailDTO;
import lk.udarabattery.newproj.dto.CustomerDTO;
import lk.udarabattery.newproj.main.StartUp;
import lk.udarabattery.newproj.view.util.tblmodel.BatterySaleFormTM;
import lk.udarabattery.newproj.view.util.tblmodel.BatterydetailTM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author THARAKA
 */
public class BatterySaleFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private JFXDatePicker txtOrderDate;
    @FXML
    private JFXComboBox<String> cmbCustomerId;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXComboBox<String> cmbBatteryCode;
    @FXML
    private JFXTextField txtBatteryType;
    @FXML
    private JFXTextField txtBatteryPrice;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private TableView<BatterySaleFormTM> tblOrderDetails;
    @FXML
    private JFXButton btnPlaceOrder;
    @FXML
    private JFXTextField txtCustomerContact;
    @FXML
    private JFXTextField txtBatteryCategory;
    
    private ArrayList<BatterySaleFormTM> batterydetails = new ArrayList<>();
    private OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.order);
    
    private BuyingorderBO buyingorderBO;
    private BuyingorderdetailBO buyingorderdetailBO;
    private BatteryBO batteryBO;
    private CustomerBO customerBO;
    @FXML
    private JFXButton btnShowReport;

    public BatterySaleFormController() {
        this.batteryBO= (BatteryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BATTERY);
        this.buyingorderBO=  (BuyingorderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BUYINGORDER);
        this.buyingorderdetailBO= (BuyingorderdetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BUYINGORDERDETAIL);
        this.customerBO= (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("category"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        
        customerCodComboBox();
        batteryCodAutoFill();
    }    


    @FXML
    private void addBattery(ActionEvent event) throws Exception{
        String price=txtBatteryPrice.getText();
         if(price.matches("^[0-9]+$|^[0-9]+.[0-9]{2}$")==false){
            new Alert(Alert.AlertType.ERROR, "Invalid PRICE", ButtonType.OK).show();
            return;
        }
        BatterySaleFormTM formTM=new BatterySaleFormTM(cmbBatteryCode.getSelectionModel().getSelectedItem(), txtBatteryType.getText(), txtBatteryCategory.getText(), new BigDecimal(txtBatteryPrice.getText()));
        batterydetails.add(formTM);
        tblOrderDetails.setItems(FXCollections.observableArrayList(batterydetails));
     
    }

    @FXML
    private void removeBattery(ActionEvent event) {
        String batterCode = cmbBatteryCode.getValue();
        if(tblOrderDetails.getSelectionModel().getSelectedIndex()>=0){
            BatterySaleFormTM form = tblOrderDetails.getSelectionModel().getSelectedItem();
            tblOrderDetails.getItems().removeAll(form);
            batterydetails.remove(form);
            
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Please select a Battery  item detail to remove", ButtonType.OK).show();
        }
    }

    @FXML
    private void clickOnPlaceOrder(ActionEvent event) throws Exception{
         LocalDate localDate = txtOrderDate.getValue();
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        boolean placeorder = orderBO.placeorder(new BuyingOrderDTO(txtOrderId.getText(), cmbCustomerId.getValue(), date), new BuyingOrderDetailDTO(cmbBatteryCode.getValue(), txtOrderId.getText(), new BigDecimal(txtBatteryPrice.getText())));
        if(placeorder==true){
            new Alert(Alert.AlertType.INFORMATION, "Done").show();
        }
    }

    private void customerCodComboBox() {
         try {
            ArrayList<String> customerArray = new ArrayList<>();
            ArrayList<CustomerDTO> customers = new ArrayList<>();
            customers = customerBO.getAllCustomers();
            for (CustomerDTO customer : customers) {
                customerArray.add(customer.getCusid());
            }
             

        cmbCustomerId.setItems(FXCollections.observableArrayList(customerArray));
        } catch (Exception ex) {
            Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void batteryCodAutoFill() {
        try {
            ArrayList<String> batteryArray = new ArrayList<>();
            ArrayList<BatteryDTO> batteryDTOs = new ArrayList<>();
            batteryDTOs = batteryBO.getAllBattery();
            
            for (BatteryDTO batteryDTO : batteryDTOs) {
                batteryArray.add(batteryDTO.getBcode());
            }
            
            
            cmbBatteryCode.setItems(FXCollections.observableArrayList(batteryArray));
        } catch (Exception ex) {
            Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectCusID(ActionEvent event) {
        try {
            String customerId = cmbCustomerId.getValue();
            CustomerDTO customer = customerBO.findID(customerId);
            txtCustomerName.setText(customer.getCusname());
            txtCustomerContact.setText(customer.getCuscontact());
            
        } catch (Exception ex) {
            Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectBatteryCode(ActionEvent event) {
        try {
            String BatteryCode = cmbBatteryCode.getValue();
            BatteryDTO battery = batteryBO.findID(BatteryCode);
            txtBatteryType.setText(battery.getBtype());
            txtBatteryCategory.setText(battery.getBcategory());
        } catch (Exception ex) {
            Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private Boolean updatebuyingorderTable() {
//        
//            String orderId = txtOrderId.getText();
//            LocalDate localDate = txtOrderDate.getValue();
//            Date date = new Date();
//            try {
//                date = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
//            } catch (ParseException ex) {
//                Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            String customerId = cmbCustomerId.getValue();
//            BuyingOrderDTO bodto=new BuyingOrderDTO(orderId, customerId, date);
//            Boolean result = false;
//          
//            try {
//                result = buyingorderBO.saveBuyingOrder(bodto);
//            } catch (Exception ex) {
//                Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        return result;
//    }
//    
//
//    private Boolean updatebuyingOrderDetailTable() {
//        String orderId = txtOrderId.getText();
//        String BatteryCode = cmbBatteryCode.getId();
//        String BatteryPrice = txtBatteryPrice.getText();
//        Boolean result = true;
//        BuyingOrderDetailDTO boddto=new BuyingOrderDetailDTO(BatteryCode, orderId, new BigDecimal(BatteryPrice));
//            
//            try {
//                result = buyingorderdetailBO.saveBuyingOrderDetail(boddto);
//            } catch (Exception ex) {
//                result = false;
//               Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//        
//    return result; 
//}

    @FXML
    private void navigateToHome(MouseEvent event) {
        StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());
        
    }

    @FXML
    private void ShowReport(ActionEvent event) {
        
            try {
                
                InputStream inputStream=getClass().getResourceAsStream("/lk/udarabattery/newproj/reports/udarabattery.jasper");
                HashMap map=new HashMap();
                LocalDate localDate = txtOrderDate.getValue();
                Date date = new Date();
                date = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                        map.put("date", date);
                        JasperPrint fillReport;
                        try {
                            fillReport = JasperFillManager.fillReport(inputStream, map, DBconnection.getInstance().getConnection());
                            JasperViewer.viewReport(fillReport);
                            JasperExportManager.exportReportToPdf(fillReport);
                        } catch (Exception ex) {
                            Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
            } catch (ParseException ex) {
                Logger.getLogger(BatterySaleFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        
    }
}
