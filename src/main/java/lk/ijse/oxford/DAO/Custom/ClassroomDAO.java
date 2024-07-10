package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.DAO.CrudDAO;
import lk.ijse.oxford.Entity.Classroom;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClassroomDAO extends CrudDAO<Classroom> {
    public  ResultSet getClassCapacity() throws SQLException, ClassNotFoundException ;

}
