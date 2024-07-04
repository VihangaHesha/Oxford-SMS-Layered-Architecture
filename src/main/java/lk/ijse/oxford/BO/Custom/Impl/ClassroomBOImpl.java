package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.ClassroomBO;
import lk.ijse.oxford.DAO.Custom.ClassroomDAO;
import lk.ijse.oxford.DAO.Custom.Impl.ClassroomDAOImpl;
import lk.ijse.oxford.DTO.ClassroomDTO;
import lk.ijse.oxford.Entity.Classroom;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomBOImpl implements ClassroomBO {

    ClassroomDAO classroomDAO = new ClassroomDAOImpl();

    @Override
    public List<ClassroomDTO> getAll() throws SQLException, ClassNotFoundException {

        List<ClassroomDTO> classroomDTOS = new ArrayList<>();
        List<Classroom> classrooms = classroomDAO.getAll();
        for (Classroom classroom : classrooms){
            ClassroomDTO classroomDTO = new ClassroomDTO(
                    classroom.getClassId(),
                    classroom.getDescription(),
                    classroom.getCapacity(),
                    classroom.getSubId()
            );
            classroomDTOS.add(classroomDTO);
        }
        return classroomDTOS;
    }
    @Override
    public  boolean save(ClassroomDTO dto) throws SQLException, ClassNotFoundException {

        Classroom classroom = new Classroom(dto.getClassId(),
                dto.getDescription(),
                dto.getCapacity(),
                dto.getSubId());
        return classroomDAO.save(classroom);
    }
    @Override
    public  int getClassCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = classroomDAO.getClassCount();

        int classCount=0;
        if (resultSet.next()){
            classCount = resultSet.getInt("total_class_count");
        }

        return classCount;
    }
    @Override
    public  int getClassCapacity() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = classroomDAO.getClassCapacity();
        int totCapacity= 0;
        if (resultSet.next()){
            totCapacity=resultSet.getInt("total_capacity");
        }
        return totCapacity;
    }
    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

         return classroomDAO.delete(id);
    }
    @Override
    public  boolean update(ClassroomDTO dto) throws SQLException, ClassNotFoundException {

        Classroom classroom = new Classroom(
                dto.getSubId(),
                dto.getDescription(),
                dto.getCapacity(),
                dto.getClassId());
        return classroomDAO.update(classroom);
    }
    @Override
    public String currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet  = classroomDAO.currentId();
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
