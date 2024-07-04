package lk.ijse.oxford.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeTableDTO {
    private String TimeId;
    private String TimePeriod;
    private String Subject;
    private java.sql.Date Date;
}
