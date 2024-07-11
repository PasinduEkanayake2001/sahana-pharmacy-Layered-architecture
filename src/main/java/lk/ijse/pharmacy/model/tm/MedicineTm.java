package lk.ijse.pharmacy.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class MedicineTm {
    private String medicineId;
    private String name;
    private String description;
    private String qtyOnHand;
    private double price;
    private String shelfId;
}
