package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.EquipmentBO;
import lk.ijse.oxford.DAO.Custom.EquipmentDAO;
import lk.ijse.oxford.DAO.Custom.Impl.EquipmentDAOImpl;
import lk.ijse.oxford.DTO.EmployeeDTO;
import lk.ijse.oxford.DTO.EquipmentDTO;
import lk.ijse.oxford.Entity.Equipment;
import lk.ijse.oxford.Entity.EquipmentUpdate;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentBOImpl implements EquipmentBO {
    EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
    @Override
    public List<EquipmentDTO> getAll() throws SQLException, ClassNotFoundException {

        List<Equipment> equipmentList = equipmentDAO.getAll();
        List<EquipmentDTO> equipmentDTOS = new ArrayList<>();

        for (Equipment equipment : equipmentList){
            EquipmentDTO equipmentDTO = new EquipmentDTO(
                    equipment.getEquipId(),
                    equipment.getDescription(),
                    equipment.getQty());
            equipmentDTOS.add(equipmentDTO);
        }
        return equipmentDTOS;
    }
    @Override
    public boolean save(EquipmentDTO dto) throws SQLException, ClassNotFoundException {

        return equipmentDAO.save(new Equipment(
                dto.getEquipId(),
                dto.getDescription(),
                dto.getQty()));

    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return equipmentDAO.delete(id);

    }
    @Override
    public  boolean update(EquipmentDTO dto) throws SQLException, ClassNotFoundException {
        EquipmentUpdate equipment = new EquipmentUpdate(
                dto.getDescription(),
                dto.getQty(),
                dto.getEquipId()
        );
        return equipmentDAO.update(equipment);
    }
    @Override
    public  int getEquipmentCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = equipmentDAO.getEquipmentCount();
        int equipmentCount=0;
        if (resultSet.next()){
            equipmentCount = resultSet.getInt("equipment_count");
        }
        return equipmentCount;
    }
}
