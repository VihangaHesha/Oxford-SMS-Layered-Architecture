package lk.ijse.oxford.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDetails {
    private String payId;
    private String subId;
    private double totalFee;
    private int seats;
}
