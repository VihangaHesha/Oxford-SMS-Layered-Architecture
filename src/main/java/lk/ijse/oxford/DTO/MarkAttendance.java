package lk.ijse.oxford.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarkAttendance {
    private AttendMarkingDTO attendMarking;
    private CheckPayment checkPayment;
}
