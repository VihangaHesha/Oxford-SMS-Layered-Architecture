package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import lk.ijse.oxford.DTO.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardFormController implements Initializable {
    @FXML
    private StackPane dashContainer;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
            dashContainer.getChildren().removeAll();
            dashContainer.getChildren().setAll(fxml);
        } catch (IOException e) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee_form.fxml"));
        Parent fxml = loader.load();
        EmployeeFormController employeeFormController = loader.getController();
        employeeFormController.setUser(user);
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student_form.fxml"));
        Parent fxml = loader.load();
        StudentFormController studentFormController = loader.getController();
        studentFormController.setUser(user);

        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnEquipmentOnAction(ActionEvent actionEvent) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("/view/equipment_form.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnUserOnAction(ActionEvent actionEvent ) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user_form.fxml"));
        Parent fxml = loader.load();
        UserFormController userController = loader.getController();
        userController.setUser(user);

        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnClassroomOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/classroom_form.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }
}
