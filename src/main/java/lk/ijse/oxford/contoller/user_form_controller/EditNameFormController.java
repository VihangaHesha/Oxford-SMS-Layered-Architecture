package lk.ijse.oxford.contoller.user_form_controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.BO.BOFactory;
import lk.ijse.oxford.DAO.Custom.Impl.UserDAOImpl;
import lk.ijse.oxford.DAO.Custom.UserDAO;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;

public class EditNameFormController {
    @FXML
    private JFXTextField txtUserId;
    @FXML
    private JFXTextField txtUsername;
    UserDAO userDAO = (UserDAO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.USERID,txtUserId);
    }

    public void txtnameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.UNAME,txtUsername);
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.UNAME,txtUsername))return false;
        if(!Regex.setTextColor(TextFields.USERID,txtUserId))return false;
        return true;
    }
    public void btnUsernameSaveOnAction(ActionEvent actionEvent) {
       if (isValidate()){
           String uId = txtUserId.getText();
           String name = txtUsername.getText();

           try {
               boolean isUpdated = userDAO.updateName(uId,name);
               if (isUpdated) {
                   new Alert(Alert.AlertType.CONFIRMATION, "Username Saved!").show();
               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           } catch (ClassNotFoundException e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           }
       }
    }
}
