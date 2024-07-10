package lk.ijse.oxford.contoller.equipment_form_controllers;

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
import lk.ijse.oxford.BO.Custom.EquipmentBO;
import lk.ijse.oxford.BO.Custom.Impl.EquipmentBOImpl;
import lk.ijse.oxford.DTO.EquipmentDTO;
import lk.ijse.oxford.DTO.tm.EquipmentTm;
import lk.ijse.oxford.DAO.Custom.Impl.EquipmentDAOImpl;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditEquipmentFormController {
    @FXML
    private Label lblEquipCount;
    private int equipCount;
    @FXML
    private JFXTextField txtEquipId;
    @FXML
    private JFXTextField txtEquiptDesc;
    @FXML
    private JFXTextField txtEquipQty;
    @FXML
    private TableColumn<?,?> colEquipmentId;
    @FXML
    private TableColumn<?,?>colEquipmentQty;
    @FXML
    private TableColumn<?,?>colEquipmentDesc;
    @FXML
    private TableView tblEquipment;

    private List<EquipmentDTO> equipmentList = new ArrayList<>();
    EquipmentBO equipmentBO = (EquipmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EQUIPMENT);

    public void initialize(){
        this.equipmentList = getAllEquipment();
        setCellValueFactory();
        loadEquipmentTable();
        try {
            equipCount= equipmentBO.getCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setEquipmentCount(equipCount);
    }

    private void setEquipmentCount(int equipCount) {
        lblEquipCount.setText(String.valueOf(equipCount));
    }


    private List<EquipmentDTO> getAllEquipment() {
        List<EquipmentDTO> equipmentList = null;
        try {
            equipmentList = equipmentBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return equipmentList;
    }

    private void loadEquipmentTable() {
        ObservableList<EquipmentTm> tmList = FXCollections.observableArrayList();

        for (EquipmentDTO equipment : equipmentList) {
            EquipmentTm equipmentTm = new EquipmentTm(
                    equipment.getEquipId(),
                    equipment.getDescription(),
                    equipment.getQty()
            );

            tmList.add(equipmentTm);
        }
        tblEquipment.setItems(tmList);
    }

    private void setCellValueFactory() {
        colEquipmentId.setCellValueFactory(new PropertyValueFactory<>("equipId"));
        colEquipmentDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colEquipmentQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    public void btnEquipmentEditOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String id = txtEquipId.getText();
            String desc = txtEquiptDesc.getText();
            int qty = Integer.parseInt(txtEquipQty.getText());

            EquipmentDTO equipment = new EquipmentDTO(id,desc,qty);

            try {
                boolean isUpdated = equipmentBO.update(equipment);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Data Is Updated!").show();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void equipEditOnAction(MouseEvent mouseEvent) {
        EquipmentTm selectedItem = (EquipmentTm) tblEquipment.getSelectionModel().getSelectedItem();
        txtEquipId.setText(selectedItem.getEquipId());
        txtEquiptDesc.setText(selectedItem.getDescription());
        txtEquipQty.setText(String.valueOf(selectedItem.getQty()));
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.DESC,txtEquiptDesc))return false;
        if(!Regex.setTextColor(TextFields.QTY,txtEquipQty))return false;
        if(!Regex.setTextColor(TextFields.EQID,txtEquipId))return false;
        return true;
    }
    public void txtClassDescCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DESC,txtEquiptDesc);
    }

    public void txtClassIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EQID,txtEquipId);
    }

    public void txtQtyCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.QTY,txtEquipQty);
    }
}
