package lk.ijse.oxford.contoller.user_form_controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.BO.BOFactory;
import lk.ijse.oxford.DAO.Custom.Impl.UserDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;

public class EditContactFormController {
    @FXML
    private JFXTextField txtUserId;
    @FXML
    private JFXTextField txtContact;

    UserDAOImpl userDAO = (UserDAOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void btnContactSaveOnAction(ActionEvent actionEvent) {
        if (isValidate()){

            String id = txtUserId.getText();
            String contact = txtContact.getText();

            try {
                boolean isUpdated = userDAO.updateContact(id,contact);
                /*boolean isUpdated = UserDAOImpl.updateContact(id,contact);*/
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Contact Saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }
    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.CONTACT,txtContact))return false;
        if(!Regex.setTextColor(TextFields.USERID,txtUserId))return false;
        return true;
    }

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.USERID,txtUserId);
    }

    public void txtnameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContact);
    }
}
