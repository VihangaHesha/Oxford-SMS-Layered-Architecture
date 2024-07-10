package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.EquipmentDAO;
import lk.ijse.oxford.Entity.Equipment;
import lk.ijse.oxford.Entity.EquipmentUpdate;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.EquipmentDTO;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {
    
    @Override
    public List<Equipment> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM equipment");

        List<Equipment> equipmentList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String desc = resultSet.getString(2);
            int qty = Integer.parseInt(resultSet.getString(3));

            Equipment equipment = new Equipment(id,desc,qty);
            equipmentList.add(equipment);
        }
        return equipmentList;
    }
    @Override
    public  boolean save(Equipment entity) throws SQLException, ClassNotFoundException {
            return  SQLUtil.execute("INSERT INTO Equipment VALUES (?,?,?)",
                    entity.getEquipId(),
                    entity.getDescription(),
                    entity.getQty());
            
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
            
            return SQLUtil.execute("DELETE FROM Equipment WHERE EquipId =?",id);
    }

    @Override
    public boolean update(Equipment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public  boolean update(EquipmentUpdate entity) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE Student SET Description = ? , Qty = ?  WHERE EquipId = ?",
                    entity.getDescription(),
                    entity.getQty(),
                    entity.getEquipId());
    }

    @Override
    public  ResultSet getCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) equipment_count FROM Equipment");
        return resultSet;
    }

    @Override
    public Equipment searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet currentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}

