package lk.ijse.oxford.util;

import lk.ijse.oxford.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql , Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        //pstm Loop
        for (int i=0;i<args.length;i++){
            pstm.setObject(i+1,args[i]);
        }

        //Execution Type
//        boolean executionType = sql.startsWith("SELECT");
        if (sql.startsWith("SELECT")){
            ResultSet resultSet = pstm.executeQuery();
            return (T) resultSet;
        }else{;
            return (T)(Boolean)(pstm.executeUpdate()>0);
        }

        //Return Type


    }
}
