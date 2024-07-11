package lk.ijse.oxford.BO.Custom.Impl;

import lk.ijse.oxford.BO.Custom.EmployeeBO;
import lk.ijse.oxford.DAO.Custom.EmployeeDAO;
import lk.ijse.oxford.DAO.Custom.Impl.EmployeDAOImpl;
import lk.ijse.oxford.DAO.DAOFactory;
import lk.ijse.oxford.DTO.EmployeeDTO;
import lk.ijse.oxford.Entity.Employee;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        List<Employee> employeeList = employeeDAO.getAll();
        for (Employee employee : employeeList){
            EmployeeDTO employeeDTO = new EmployeeDTO(
            employee.getEmpId(),
            employee.getName(),
            employee.getContact(),
            employee.getAddress(),
            employee.getType(),
            employee.getUserId());

            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public  boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {

        Employee employee = new Employee(
                dto.getEmpId(),
                dto.getName(),
                dto.getContact(),
                dto.getAddress(),
                dto.getType(),
                dto.getUserId()
        );
        return employeeDAO.save(employee);
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return employeeDAO.delete(id);
    }

    @Override
    public  boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {

        Employee employee = new Employee(
                dto.getName(),
                dto.getContact(),
                dto.getAddress(),
                dto.getType(),
                dto.getUserId(),
                dto.getEmpId()
        );
        return employeeDAO.update(employee);
    }

    @Override
    public  EmployeeDTO searchByCode(String id) throws SQLException, ClassNotFoundException {

        Employee employees = employeeDAO.searchById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employees.getEmpId(),
                employees.getName(),
                employees.getContact(),
                employees.getAddress(),
                employees.getType(),
                employees.getUserId());

        return employeeDTO;
    }

    @Override
    public  int getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = employeeDAO.getCount();
            int employeeCount=0;
            if (resultSet.next()){
                employeeCount = resultSet.getInt("employee_count");
            }
            return employeeCount;
    }

    @Override
    public  String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = employeeDAO.currentId();
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

}
