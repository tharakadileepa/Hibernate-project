/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.udarabattery.newproj.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author THARAKA
 */
public class StartUp extends Application {

    public static void navigateToHome(Node node, Stage stage) {
        
            TranslateTransition tt = new TranslateTransition(Duration.millis(300),node );
            tt.setFromX(0);
            tt.setToX(-node.getScene().getWidth());
            tt.play();
            
            Platform.runLater(()->{
            
                try {
                    Parent root = FXMLLoader.load(StartUp.class.getResource("/lk/udarabattery/newproj/view/MainForm.fxml"));
                    Scene mainScene = new Scene(root);
                    stage.setScene(mainScene);
                    
                    TranslateTransition tt1 = new TranslateTransition(Duration.millis(300), root.lookup("AnchorPane"));
                    tt1.setToX(0);
                    tt1.setFromX(-mainScene.getWidth());
                    tt1.play();
                    
                    stage.centerOnScreen();
                } catch (IOException ex) {
                    Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
                  
                }
            
            });
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root=FXMLLoader.load(this.getClass().getResource("/lk/udarabattery/newproj/view/MainForm.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("UDARA BATTERY SALES");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
