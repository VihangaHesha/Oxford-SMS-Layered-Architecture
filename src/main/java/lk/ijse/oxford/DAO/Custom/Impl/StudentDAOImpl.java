package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.StudentDAO;
import lk.ijse.oxford.Entity.Student;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.StudentDTO;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public  List<Student> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Student");

        List<Student>studentList = new ArrayList<>();
        while (resultSet.next()) {
            String stId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String grade = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);
            String userId = resultSet.getString(6);

            Student student = new Student(stId,userId,name,contact,address,grade);
            studentList.add(student);
        }
        return studentList;
    }
    @Override
    public  boolean save(Student student) throws SQLException, ClassNotFoundException {
        
        return SQLUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?)",
                student.getStId(),
                student.getName(),
                student.getGrade(),
                student.getContact(),
                student.getAddress(),
                student.getUserId());
        
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        
        return SQLUtil.execute("DELETE FROM Student WHERE StId =?",id);
    }

    @Override
    public  boolean update(Student student) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Student SET Name = ? , Grade = ? , Contact = ? , Address = ? ,UserId = ? WHERE StId = ?",
               student.getName(),
                student.getGrade(),
                student.getContact(),
                student.getAddress(),
                student.getUserId(),
                student.getStId());

    }

    @Override
    public ResultSet getCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS student_count FROM Student");
        return resultSet;

       /* int studentCount= 0;
        if (resultSet.next()){
            studentCount=resultSet.getInt("student_count");
        }
        return studentCount;*/
    }

    @Override
    public  ResultSet currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT StId FROM Student ORDER BY StId desc LIMIT 1");

        return resultSet;

        /*if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;*/
    }
    @Override
    public Student searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
