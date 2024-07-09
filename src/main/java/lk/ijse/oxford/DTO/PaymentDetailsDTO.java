package lk.ijse.oxford.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDetailsDTO {
    private String payId;
    private String subId;
    private double totalFee;
    private int seats;
}
