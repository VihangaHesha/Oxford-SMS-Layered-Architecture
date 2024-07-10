package lk.ijse.oxford.BO.Custom;

import lk.ijse.oxford.BO.SuperBO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    
    public  boolean updateName(String uId, String name) throws SQLException, ClassNotFoundException ;
    
    public  boolean updateContact(String id, String contact) throws SQLException, ClassNotFoundException ;
    
    public  boolean updatePW(String id, String pw) throws SQLException, ClassNotFoundException ;
    
    public  boolean updateEmail(String id, String email) throws SQLException, ClassNotFoundException ;
}
