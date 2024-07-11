package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.UserBO;
import lk.ijse.oxford.DAO.Custom.Impl.UserDAOImpl;
import lk.ijse.oxford.DAO.Custom.UserDAO;
import lk.ijse.oxford.DAO.DAOFactory;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public  boolean updateName(String uId, String name) throws SQLException, ClassNotFoundException {
        return userDAO.updateName(uId,name);
    }
    @Override
    public  boolean updateContact(String id, String contact) throws SQLException, ClassNotFoundException {
        return userDAO.updateContact(id,contact);
    }
    @Override
    public  boolean updatePW(String id, String pw) throws SQLException, ClassNotFoundException {
       return userDAO.updatePW(id,pw);
    }
    @Override
    public  boolean updateEmail(String id, String email) throws SQLException, ClassNotFoundException {
        return userDAO.updateEmail(id,email);
    }
}
