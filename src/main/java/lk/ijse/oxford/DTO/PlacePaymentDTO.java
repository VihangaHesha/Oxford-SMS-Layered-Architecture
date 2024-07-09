package lk.ijse.oxford.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlacePaymentDTO {
    private PaymentDTO payment;
    private List<PaymentDetailsDTO> pdList;
}
