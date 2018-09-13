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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.udarabattery.newproj.buisness.custom.CustomerBO;
import lk.udarabattery.newproj.business.BOFactory;
import lk.udarabattery.newproj.business.SuperBO;
import lk.udarabattery.newproj.dto.CustomerDTO;
import lk.udarabattery.newproj.main.StartUp;
import lk.udarabattery.newproj.view.util.tblmodel.CustomerTM;

/**
 * FXML Controller class
 *
 * @author THARAKA
 */
public class CustomerController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtcusID;
    @FXML
    private JFXTextField txtcusContact;
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtCusAddress;
    @FXML
    private JFXTextField txtcusName;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<CustomerTM> tblCustomer;

    private CustomerBO customerBO;

    public CustomerController() {
        this.customerBO  = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    }
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cusId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cusName"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nicNO"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("cusContact"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        
        
    loadAllCustomers();  
    }    

    @FXML
    private void searchOnClick(ActionEvent event) {
        try {
            String customerId = txtcusID.getText();
            CustomerDTO customer = customerBO.findID(customerId);
            txtcusName.setText(customer.getCusname());
            txtcusContact.setText(customer.getCuscontact());
            txtNIC.setText(customer.getNicno());
            txtCusAddress.setText(customer.getCusaddress());
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void addOnClick(ActionEvent event) {
        
        String customer=txtcusName.getText();
        String nic=txtNIC.getText();
        String contact=txtcusContact.getText();
        if(customer.matches("^[a-zA-Z]+$")==false || nic.matches("^[0-9]{9}[v|V]$")==false || contact.matches("^[+|0-9]+")==false){
            new Alert(Alert.AlertType.ERROR, "Invalid name or nic or contact", ButtonType.OK).show();
            return;
        }
        else{
       
        try {
            
            CustomerDTO customerDTO=new CustomerDTO(txtcusID.getText(), txtcusName.getText(), txtNIC.getText(), txtcusContact.getText(), txtCusAddress.getText());
            
            boolean s = customerBO.saveCustomer(customerDTO);
            loadAllCustomers();
            if (s) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added succesfully", ButtonType.OK).show();
            }else{
                
            new Alert(Alert.AlertType.ERROR, "not ADded", ButtonType.OK).show();
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void deleteOnClick(ActionEvent event) {
        if(tblCustomer.getSelectionModel().getSelectedIndex()>=0){
            deleteCustomer();
            loadAllCustomers();
            
        }else{
            new Alert(Alert.AlertType.ERROR, "Please select a customer to delete..", ButtonType.OK).show();
        }
        
    }

    private void loadAllCustomers() {
        try {
            ArrayList<CustomerDTO> AllCustomers = customerBO.getAllCustomers();
            ArrayList<CustomerTM> addCustomers = new ArrayList<>();
            for (CustomerDTO AllCustomer : AllCustomers) {
                CustomerTM customer = new CustomerTM(AllCustomer.getCusid(), AllCustomer.getCusname(), AllCustomer.getNicno(), AllCustomer.getCuscontact(), AllCustomer.getCusaddress());
                addCustomers.add(customer);
            }
            tblCustomer.setItems(FXCollections.observableArrayList(addCustomers));
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void deleteCustomer() {
        try {
            CustomerTM customer = tblCustomer.getSelectionModel().getSelectedItem();
            String id = customer.getCusId();
            boolean result = customerBO.deleteCustomer(id);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Customer has been deleted successfully..", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error when deleting customer..", ButtonType.OK).show();
            }   } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }
}
