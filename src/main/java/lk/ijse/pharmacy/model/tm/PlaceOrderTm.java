package lk.ijse.pharmacy.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderTm {
    private String itemCode;
    private String description;
    private String qty;
    private String unitPrice;
    private String total;
    private JFXButton delete;
}
