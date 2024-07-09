package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.DTO.StudentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl {
    public static List<StudentDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM Student";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<StudentDTO>studentList = new ArrayList<>();
        while (resultSet.next()) {
            String stId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String grade = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);
            String userId = resultSet.getString(6);

            StudentDTO student = new StudentDTO(stId,userId,name,contact,address,grade);
            studentList.add(student);
        }
        return studentList;
    }

    public static boolean save(StudentDTO student) throws SQLException {
        String sql ="INSERT INTO Student VALUES (?,?,?,?,?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,student.getStId());
        pstm.setObject(2,student.getName());
        pstm.setObject(3,student.getGrade());
        pstm.setObject(4,student.getContact());
        pstm.setObject(5,student.getAddress());
        pstm.setObject(6,student.getUserId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {

        String sql = "DELETE FROM Student WHERE StId =?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(StudentDTO student) throws SQLException {

        String sql = "UPDATE Student SET Name = ? , Grade = ? , Contact = ? , Address = ? ,UserId = ? WHERE StId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,student.getName());
        pstm.setObject(2,student.getGrade());
        pstm.setObject(3,student.getContact());
        pstm.setObject(4,student.getAddress());
        pstm.setObject(5,student.getUserId());
        pstm.setObject(6,student.getStId());

        return pstm.executeUpdate() > 0;
    }


    public static int getStudentCount() throws SQLException {
        String sql ="SELECT COUNT(*) AS student_count FROM Student";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int studentCount= 0;
        if (resultSet.next()){
            studentCount=resultSet.getInt("student_count");
        }
        return studentCount;
    }

    public static String currentId() throws SQLException {
        String sql = "SELECT StId FROM Student ORDER BY StId desc LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
