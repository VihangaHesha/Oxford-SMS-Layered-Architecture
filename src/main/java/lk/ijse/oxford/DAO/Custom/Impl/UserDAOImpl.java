package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.UserDAO;
import lk.ijse.oxford.Entity.User;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public  boolean updateName(String uId, String name) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE User SET Name = ?  WHERE UserId = ?",name,uId);
    }
    @Override
    public  boolean updateContact(String id, String contact) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE User SET Contact = ?  WHERE UserId = ?",contact,id);
    }
    @Override
    public  boolean updatePW(String id, String pw) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE User SET Password = ?  WHERE UserId = ?",pw,id);
    }
    @Override
    public  boolean updateEmail(String id, String email) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE User SET Email = ?  WHERE UserId = ?",email,id);
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User searchById(String id) throws SQLException, ClassNotFoundException {
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
