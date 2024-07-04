package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.Entity.Equipment;
import lk.ijse.oxford.Entity.EquipmentUpdate;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EquipmentDAO {
    
    public List<Equipment> getAll() throws SQLException, ClassNotFoundException ;
    
    public  boolean save(Equipment entity) throws SQLException, ClassNotFoundException ;
    
    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    
    public  boolean update(EquipmentUpdate entity) throws SQLException, ClassNotFoundException ;
    
    public  ResultSet getEquipmentCount() throws SQLException, ClassNotFoundException ;
}
