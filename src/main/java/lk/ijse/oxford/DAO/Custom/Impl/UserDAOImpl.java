package lk.ijse.oxford.DAO.Custom.Impl;

import lk.ijse.oxford.DAO.Custom.UserDAO;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.SQLException;

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

}
