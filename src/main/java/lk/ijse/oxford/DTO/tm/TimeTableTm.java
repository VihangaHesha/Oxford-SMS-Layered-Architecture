package lk.ijse.oxford.DTO.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TimeTableTm {
    private String TimeId;
    private String TimePeriod;
    private String Subject;
    private java.sql.Date Date;
}
