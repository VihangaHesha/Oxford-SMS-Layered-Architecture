package lk.ijse.oxford.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceDTO {
    private String StId;
    private java.sql.Date Date;
    private String attendMark;
    private String Name;
    private String attendId;
}
