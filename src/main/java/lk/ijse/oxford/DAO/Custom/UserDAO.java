package lk.ijse.oxford.DAO.Custom;

import lk.ijse.oxford.DAO.CrudDAO;
import lk.ijse.oxford.Entity.User;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    public  boolean updateName(String uId, String name) throws SQLException, ClassNotFoundException ;

    public  boolean updateContact(String id, String contact) throws SQLException, ClassNotFoundException ;

    public  boolean updatePW(String id, String pw) throws SQLException, ClassNotFoundException ;

    public  boolean updateEmail(String id, String email) throws SQLException, ClassNotFoundException ;
}
