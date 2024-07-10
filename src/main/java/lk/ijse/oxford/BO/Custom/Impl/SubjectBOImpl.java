package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.BOFactory;
import lk.ijse.oxford.BO.Custom.SubjectBO;
import lk.ijse.oxford.DAO.Custom.Impl.SubjectDAOImpl;
import lk.ijse.oxford.DAO.Custom.SubjectDAO;
import lk.ijse.oxford.DTO.SubjectDTO;
import lk.ijse.oxford.Entity.Subject;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectBOImpl implements SubjectBO {

    SubjectDAO subjectDAO = (SubjectDAO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUBJECT);

    public List<String> getIds() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = subjectDAO.getIds();

        List<String> subList = new ArrayList<>();

        while (resultSet.next()) {
            subList.add(resultSet.getString(1));
        }
        return subList;
    }

    public SubjectDTO searchByName(String subName) throws SQLException, ClassNotFoundException {

        Subject subject = subjectDAO.searchByName(subName);

        SubjectDTO subjectDTO = new SubjectDTO(
                subject.getSubId(),
                subject.getDescription(),
                subject.getFeeAmount(),
                subject.getAvailableSeats());

        return subjectDTO;
    }
}
