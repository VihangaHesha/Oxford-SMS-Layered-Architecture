package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.SalaryBO;
import lk.ijse.oxford.DAO.Custom.Impl.QueryDAOImpl;
import lk.ijse.oxford.DAO.Custom.Impl.SalaryDAOImpl;
import lk.ijse.oxford.DAO.Custom.QueryDAO;
import lk.ijse.oxford.DAO.Custom.SalaryDAO;
import lk.ijse.oxford.DAO.DAOFactory;
import lk.ijse.oxford.DTO.SalaryDTO;
import lk.ijse.oxford.Entity.Salary;
import lk.ijse.oxford.util.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALARY);

    @Override
    public List<SalaryDTO> getAll() throws SQLException, ClassNotFoundException {

        List<Salary> salaryList = queryDAO.getAll();
        List<SalaryDTO> salaryDTOS = new ArrayList<>();

        for (Salary salary : salaryList){
            SalaryDTO salaryDTO = new SalaryDTO(
                    salary.getSalaryId(),
                    salary.getAmount(),
                    salary.getDate(),
                    salary.getName(),
                    salary.getEmpId()
            );
            salaryDTOS.add(salaryDTO);
        }
        return salaryDTOS;
    }
    @Override
    public  SalaryDTO searchByCode(String empId) throws SQLException, ClassNotFoundException {

        Salary salary = queryDAO.searchByCode(empId);
        SalaryDTO salaryDTO = new SalaryDTO(
                salary.getSalaryId(),
                salary.getAmount(),
                salary.getDate(),
                salary.getName(),
                salary.getEmpId());

        return salaryDTO;
    }

    @Override
    public  int getTotalSalary() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = salaryDAO.getTotalSalary();
        int totSalary= 0;
        if (resultSet.next()){
            totSalary=resultSet.getInt("total_salary");
        }
        return totSalary;
    }
}
