package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.TimeTableBO;
import lk.ijse.oxford.DAO.Custom.Impl.TimeTableDAOImpl;
import lk.ijse.oxford.DAO.Custom.TimeTableDAO;
import lk.ijse.oxford.DTO.TimeTableDTO;
import lk.ijse.oxford.Entity.TimeTable;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeTableBOImpl implements TimeTableBO {
    TimeTableDAO timeTableDAO = new TimeTableDAOImpl();
    public  List<TimeTableDTO> getAll() throws SQLException, ClassNotFoundException {
        List<TimeTableDTO> timeTableDTO = new ArrayList<>();
        List<TimeTable> timeTables = timeTableDAO.getAll();
        for (TimeTable timeTable : timeTables){
            TimeTableDTO timeTableDTOs = new TimeTableDTO(
                    timeTable.getTimeId(),
                    timeTable.getTimePeriod(),
                    timeTable.getSubject(),
                    timeTable.getDate());
            timeTableDTO.add(timeTableDTOs);
        }
        return timeTableDTO;
    }
}
