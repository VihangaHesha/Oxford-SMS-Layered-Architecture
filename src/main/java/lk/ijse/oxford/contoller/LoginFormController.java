package lk.ijse.oxford.contoller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.UserDTO;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    public JFXTextField txtUsername;
    @FXML
    public JFXPasswordField pfPassword;
    @FXML
    public Parent rootNode;

    private UserDTO user;


    public void btnLoginOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String pw = pfPassword.getText();
        if(isValid()){
            try {
                checkCredentionals(username,pw);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "OOPS! something went wrong").show();
            }
        }
    }

    private void checkCredentionals(String username, String pw) throws SQLException, IOException {

        String sql="SELECT * FROM user WHERE Name =? ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,username);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String dbPw = resultSet.getString(3);
            if (dbPw.equals(pw)){
                user = new UserDTO(resultSet.getString(1),resultSet.getString(2)
                        ,resultSet.getString(3),resultSet.getString(4)
                        ,resultSet.getString(5));
                navigateToDashboard();
            }else {
                new Alert(Alert.AlertType.ERROR , "Incorrect Password! Please try again!").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Cannot find Username!").show();
        }

    }

    private void navigateToDashboard() throws IOException {

//        BorderPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
        Parent root = loader.load();
        DashboardFormController dashController = loader.getController();
        dashController.setUser(user);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        stage.setOnShown(event -> currentStage.close());

        stage.show();


    }

    public void linkActionOnForgetPassword(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/forgetPassword_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("New Password");

        stage.show();
    }

    public void txtUserNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.UNAME,txtUsername);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(TextFields.UNAME,txtUsername)) return false;
        if (!Regex.setTextColor(TextFields.PW,pfPassword)) return false;
        return true;
    }
    public void txtPwOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PW,pfPassword);
    }
}
