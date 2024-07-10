package lk.ijse.oxford.BO;

import lk.ijse.oxford.BO.Custom.Impl.*;
import lk.ijse.oxford.BO.Custom.PaymentDetailsBO;
import lk.ijse.oxford.DAO.Custom.Impl.AttendanceDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        EMPLOYEE,STUDENT,EQUIPMENT,CLASSROOM,USER,PAYMENT,ATTENDANCE,SALARY,TIMETABLE,SUBJECT,
        PAYMENTDETAIL,SETATTENDANCE,PLACEPAYMENT
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types) {
        switch (types) {

            case ATTENDANCE:
                return new AttendanceBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case EQUIPMENT:
                return new EquipmentBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case CLASSROOM:
                return new ClassroomBOImpl();
            case TIMETABLE:
                return new TimeTableBOImpl();
            case USER:
                return new UserBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case SUBJECT:
                return new SubjectBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SETATTENDANCE:
                return new SetAttendanceBOImpl();
            case PAYMENTDETAIL:
                return new PaymentDetailsBOImpl();
            case PLACEPAYMENT:
                return new PlacePaymentBOImpl();
            default:
                return null;

        }
    }
}
