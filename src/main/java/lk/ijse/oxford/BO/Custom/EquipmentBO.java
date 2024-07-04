package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.DTO.EquipmentDTO;
import lk.ijse.oxford.Entity.Equipment;
import lk.ijse.oxford.Entity.EquipmentUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EquipmentBO {
    public List<EquipmentDTO> getAll() throws SQLException, ClassNotFoundException ;
    
    public boolean save(EquipmentDTO dto) throws SQLException, ClassNotFoundException ;
    
    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    
    public  boolean update(EquipmentDTO dto) throws SQLException, ClassNotFoundException ;
    
    public  int getEquipmentCount() throws SQLException, ClassNotFoundException ;
}
