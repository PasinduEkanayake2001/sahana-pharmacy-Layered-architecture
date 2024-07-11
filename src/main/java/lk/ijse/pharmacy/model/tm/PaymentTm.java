package lk.ijse.pharmacy.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class PaymentTm {
    private String paymentId;
    private double amount;
    private String paymentMethod;
    private String date;
}
