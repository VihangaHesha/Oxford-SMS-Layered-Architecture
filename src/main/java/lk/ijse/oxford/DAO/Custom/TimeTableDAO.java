package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.Entity.TimeTable;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TimeTableDAO {
    public List<TimeTable> getAll() throws SQLException, ClassNotFoundException ;
}