package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.BO.SuperBO;
import lk.ijse.oxford.DTO.TimeTableDTO;
import lk.ijse.oxford.Entity.TimeTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TimeTableBO extends SuperBO {
    public List<TimeTableDTO> getAll() throws SQLException, ClassNotFoundException ;
}
