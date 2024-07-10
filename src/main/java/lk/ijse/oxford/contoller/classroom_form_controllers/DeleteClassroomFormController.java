package lk.ijse.oxford.contoller.classroom_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.BO.BOFactory;
import lk.ijse.oxford.BO.Custom.ClassroomBO;
import lk.ijse.oxford.BO.Custom.Impl.ClassroomBOImpl;
import lk.ijse.oxford.DTO.ClassroomDTO;
import lk.ijse.oxford.DTO.tm.ClassTm;
import lk.ijse.oxford.DAO.Custom.Impl.ClassroomDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteClassroomFormController {
    @FXML
    private Label lblClassCount;
    private int classCount;
    @FXML
    private Label lblClassCapacity;
    private int classCapacity;
    @FXML
    private JFXTextField txtClassId;
    @FXML
    private JFXTextField txtClassDesc;
    @FXML
    private TableColumn<?,?> colClassId;
    @FXML
    private TableColumn<?,?>colCapacity;
    @FXML
    private TableColumn<?,?>colClassDesc;
    @FXML
    private TableColumn<?,?>colSubId;
    @FXML
    private TableView tblClassroom;
    private List<ClassroomDTO> classroomList = new ArrayList<>();

    ClassroomBO classroomBO = (ClassroomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CLASSROOM);

    public void initialize(){
        this.classroomList = getAllClassrooms();
        setCellValueFactory();
        loadClassroomTable();
        try {
            classCount= classroomBO.getCount();
            classCapacity= classroomBO.getClassCapacity();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setClassCount(classCount);
        setClassCapacity(classCapacity);
    }
    private void setClassCount(int classCount) {
        lblClassCount.setText(String.valueOf(classCount));
    }

    private void setClassCapacity(int classCapacity) {
        lblClassCapacity.setText(String.valueOf(classCapacity));
    }

    private List<ClassroomDTO> getAllClassrooms() {
        List<ClassroomDTO> classroomList = null;
        try {
            classroomList = classroomBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return classroomList;

    }

    private void loadClassroomTable() {
        ObservableList<ClassTm> tmList = FXCollections.observableArrayList();

        for (ClassroomDTO classroom : classroomList) {
            ClassTm classTm = new ClassTm(
                    classroom.getClassId(),
                    classroom.getDescription(),
                    classroom.getCapacity(),
                    classroom.getSubId()
            );

            tmList.add(classTm);
        }
        tblClassroom.setItems(tmList);
    }

    private void setCellValueFactory() {
        colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colClassDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colSubId.setCellValueFactory(new PropertyValueFactory<>("subId"));
    }
    public void btnClassDeleteOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String id = txtClassId.getText();

            try {
                boolean isDeleted = classroomBO.delete(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Classroom Data is Deleted!").show();
                    initialize();
                    txtClassDesc.setText("");
                    txtClassId.setText("");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void classDeleteOnAction(MouseEvent mouseEvent) {
        ClassTm selectedItem = (ClassTm) tblClassroom.getSelectionModel().getSelectedItem();
        txtClassId.setText(selectedItem.getClassId());
        txtClassDesc.setText(selectedItem.getDescription());
    }

    public void txtDescCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DESC,txtClassDesc);
    }

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CID,txtClassId);
    }
    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.CID,txtClassId))return false;
        if(!Regex.setTextColor(TextFields.DESC,txtClassDesc))return false;
        return true;
    }
}
