package lk.ijse.oxford.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeTable {
    private String TimeId;
    private String TimePeriod;
    private String Subject;
    private java.sql.Date Date;
}
