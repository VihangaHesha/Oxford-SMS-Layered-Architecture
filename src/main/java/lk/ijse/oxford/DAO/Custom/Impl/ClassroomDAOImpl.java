package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.ClassroomDAO;
import lk.ijse.oxford.Entity.Classroom;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAOImpl implements ClassroomDAO {
    @Override
    public  List<Classroom> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute("SELECT * FROM classroom");
        List<Classroom> classroomList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String desc = resultSet.getString(2);
            int capacity = Integer.parseInt(resultSet.getString(3));
            String subId = resultSet.getString(4);

            Classroom classroom = new Classroom(id,desc,capacity,subId);
            classroomList.add(classroom);
        }
        return classroomList;
    }
    @Override
    public  boolean save(Classroom classroom) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Classroom VALUES (?,?,?,?)",
                classroom.getSubId()
                ,classroom.getDescription(),
                classroom.getCapacity(),
                classroom.getSubId());
    }
    @Override
    public  ResultSet getCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute("SELECT COUNT(ClassId) AS total_class_count FROM Classroom");

        /*int classCount=0;
        if (resultSet.next()){
            classCount = resultSet.getInt("total_class_count");
        }*/
        
        return resultSet;
    }
    @Override
    public  ResultSet getClassCapacity() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(Capacity) AS total_capacity FROM Classroom");
        
        /*int totCapacity= 0;
        if (resultSet.next()){
            totCapacity=resultSet.getInt("total_capacity");
        }*/
        return resultSet;
    }
    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Classroom WHERE ClassId =?",id);
    }
    @Override
    public  boolean update(Classroom classroom) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Classroom SET Description = ? , Capacity = ?, SubId=? WHERE ClassId = ?",
                classroom.getSubId(),
                classroom.getDescription(),
                classroom.getCapacity(),
                classroom.getClassId());
    }

    @Override
    public Classroom searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT ClassId FROM Classroom ORDER BY ClassId desc LIMIT 1");
        return resultSet;
    }
}
