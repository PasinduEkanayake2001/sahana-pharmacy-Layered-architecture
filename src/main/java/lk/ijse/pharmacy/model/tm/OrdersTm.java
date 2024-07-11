package lk.ijse.pharmacy.model.tm;


import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersTm {
    public String orderId;
    private String paymentId;
    private String customerId;
    private String date;

}
