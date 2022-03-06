/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ConnectController.fpmail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.ForgetPassword;

/**
 * FXML Controller class
 *
 * @author hasse
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private AnchorPane forgotpasswordscene;
    @FXML
    private Button btnconfirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void confirm(ActionEvent event) {
        ForgetPassword fp = new ForgetPassword();
        boolean test = fp.VerifyCode(Integer.parseInt(code.getText()), fpmail);
        if (test == true) {

            AnchorPane cp;
            try {
                cp = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
                forgotpasswordscene.getChildren().removeAll();
                forgotpasswordscene.getChildren().setAll(cp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}
