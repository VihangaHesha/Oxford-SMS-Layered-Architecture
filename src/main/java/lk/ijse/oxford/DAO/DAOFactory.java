package lk.ijse.oxford.DAO;

import lk.ijse.oxford.DAO.Custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ATTENDANCE,CLASSROOM,EMPLOYEE,EQUIPMENT,PAYMENT,PAYMENTDETAILS,QUERY,SALARY,STUDENT,SUBJECT,TIMETABLE,USER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case STUDENT :
                return new StudentDAOImpl();
            case EMPLOYEE:
                return new EmployeDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case CLASSROOM:
                return new ClassroomDAOImpl();
            case EQUIPMENT:
                return new EquipmentDAOImpl();
            case TIMETABLE:
                return new TimeTableDAOImpl();
            case PAYMENTDETAILS:
                return new PaymentDetailsDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SUBJECT:
                return new SubjectDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
