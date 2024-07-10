package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.DAO.CrudDAO;
import lk.ijse.oxford.Entity.Equipment;
import lk.ijse.oxford.Entity.EquipmentUpdate;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EquipmentDAO extends CrudDAO<Equipment> {
    public  boolean update(EquipmentUpdate entity) throws SQLException, ClassNotFoundException;

}
