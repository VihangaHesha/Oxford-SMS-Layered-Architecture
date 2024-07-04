package lk.ijse.oxford.DTO.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttedanceTm {
    private String attendId;
    private java.sql.Date Date;
    private String attendMark;
    private String StId;
    private String Name;
}
