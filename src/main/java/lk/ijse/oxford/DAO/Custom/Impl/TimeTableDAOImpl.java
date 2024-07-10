package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.TimeTableDAO;
import lk.ijse.oxford.DTO.TimeTableDTO;
import lk.ijse.oxford.Entity.TimeTable;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeTableDAOImpl implements TimeTableDAO {
    @Override
    public  List<TimeTable> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM timetable");
        List<TimeTable> timeTableDTOS = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String timePeriod = resultSet.getString(2);
            String subject = resultSet.getString(3);
            Date date = Date.valueOf(resultSet.getString(4));

            TimeTable tb = new TimeTable(id, timePeriod, subject, date);
            timeTableDTOS.add(tb);
        }
        return timeTableDTOS;
    }

    @Override
    public boolean save(TimeTable entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(TimeTable entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public TimeTable searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet getCount() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet currentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
