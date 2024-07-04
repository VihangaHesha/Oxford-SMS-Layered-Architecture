package lk.ijse.oxford.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subject {
    private String SubId;
    private String description;
    private double feeAmount;
    private int availableSeats;
}
