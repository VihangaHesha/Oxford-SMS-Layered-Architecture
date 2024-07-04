package lk.ijse.oxford.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryDTO {
    private String salaryId;
    private String amount;
    private Date date;
    private String EmpId;
    private String name;
}
