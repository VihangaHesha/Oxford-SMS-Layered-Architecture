package lk.ijse.oxford.DTO.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentTm {
    private String equipId;
    private String description;
    private int qty;
}
