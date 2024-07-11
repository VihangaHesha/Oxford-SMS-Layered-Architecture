package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.StudentBO;
import lk.ijse.oxford.DAO.Custom.Impl.StudentDAOImpl;
import lk.ijse.oxford.DAO.Custom.StudentDAO;
import lk.ijse.oxford.DAO.DAOFactory;
import lk.ijse.oxford.DTO.StudentDTO;
import lk.ijse.oxford.Entity.Student;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public List<StudentDTO> getAll() throws SQLException, ClassNotFoundException {

        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> studentList = studentDAO.getAll();
        for (Student student : studentList) {
            StudentDTO studentDTO = new StudentDTO(student.getStId(),
                    student.getName(),
                    student.getGrade(),
                    student.getContact(),
                    student.getAddress(),
                    student.getUserId());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
    @Override
    public  boolean save(StudentDTO dto) throws SQLException, ClassNotFoundException {
        Student student = new Student(
                dto.getStId(),
                dto.getName(),
                dto.getGrade(),
                dto.getContact(),
                dto.getAddress(),
                dto.getUserId()
        );
        return studentDAO.save(student);
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return studentDAO.delete(id);
    }

    @Override
    public  boolean update(StudentDTO dto) throws SQLException, ClassNotFoundException {
        Student student = new Student(
                dto.getName(),
                dto.getGrade(),
                dto.getContact(),
                dto.getAddress(),
                dto.getUserId(),
                dto.getStId());
        return studentDAO.update(student);
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = studentDAO.getCount();

        int studentCount= 0;
        if (resultSet.next()){
            studentCount=resultSet.getInt("student_count");
        }
        return studentCount;
    }
    @Override
    public String currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = studentDAO.currentId();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
