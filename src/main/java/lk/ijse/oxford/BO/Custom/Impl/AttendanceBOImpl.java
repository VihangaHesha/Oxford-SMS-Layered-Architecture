package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.AttendanceBO;
import lk.ijse.oxford.DAO.Custom.AttendanceDAO;
import lk.ijse.oxford.DAO.Custom.Impl.AttendanceDAOImpl;
import lk.ijse.oxford.DAO.Custom.Impl.QueryDAOImpl;
import lk.ijse.oxford.DAO.Custom.QueryDAO;
import lk.ijse.oxford.DTO.AttendMarkingDTO;
import lk.ijse.oxford.DTO.AttendanceDTO;
import lk.ijse.oxford.Entity.AttendMarking;
import lk.ijse.oxford.Entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBOImpl implements AttendanceBO {
    QueryDAO queryDAO = new QueryDAOImpl();
    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    @Override
    public AttendanceDTO searchById(String id) throws SQLException, ClassNotFoundException {

        Attendance attendance = queryDAO.searchById(id);
        AttendanceDTO attendanceDTO = new AttendanceDTO(
                attendance.getStId(),
                attendance.getDate(),
                attendance.getAttendMark(),
                attendance.getAttendId(),
                attendance.getName()
                );
        return attendanceDTO;
    }
    @Override
    public List<AttendanceDTO> getAttendanceAll() throws SQLException, ClassNotFoundException {

        List<Attendance> attendanceList = queryDAO.getAttendanceAll();
        List<AttendanceDTO> attendanceDTOS = new ArrayList<>();

        for (Attendance attendance : attendanceList){
            AttendanceDTO attendanceDTO = new AttendanceDTO(
                    attendance.getName(),
                    attendance.getDate(),
                    attendance.getAttendMark(),
                    attendance.getStId(),
                    attendance.getAttendId()
            );
            attendanceDTOS.add(attendanceDTO);
        }
        return attendanceDTOS;
    }

    @Override
    public int getAttendanceCount(LocalDate date) throws SQLException, ClassNotFoundException {
        ResultSet resultSet =attendanceDAO.getAttendanceCount(date);
        int attendCount= 0;
        if (resultSet.next()){
            attendCount=resultSet.getInt("attend_count");
        }
        return attendCount;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = attendanceDAO.currentId();
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
    @Override
    public boolean save(AttendMarkingDTO dto) throws SQLException, ClassNotFoundException {
        AttendMarking attendMarking = new AttendMarking(
                dto.getAttendId(),
                dto.getDate(),
                dto.getAttendMark(),
                dto.getStId()
        );
        return attendanceDAO.save(attendMarking);
    }

}
