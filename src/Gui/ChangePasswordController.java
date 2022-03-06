/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ConnectController.fpmail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ForgetPassword;

/**
 * FXML Controller class
 *
 * @author hasse
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private TextField password1;
    @FXML
    private Button btnmodifier;
    @FXML
    private TextField password2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifierEmail(ActionEvent event) {
        String newpassword = password1.getText();
        String confirmpassword = password2.getText();
        if (newpassword.equals(confirmpassword)) {
            ForgetPassword fp = new ForgetPassword();
            fp.modifypassword(fpmail, newpassword);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("modification impossible");
            alert.setContentText("les  deux mots de passes ne sont pas compatible ");
            alert.showAndWait();

        }

    }

}
