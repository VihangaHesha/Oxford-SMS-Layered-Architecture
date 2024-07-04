package lk.ijse.oxford.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentUpdate {
    private String equipId;
    private int qty;
    private String description;
}
