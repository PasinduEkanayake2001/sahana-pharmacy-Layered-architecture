package lk.ijse.pharmacy.model.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class CustomerTm {
    private String customerId;
    private String name;
    private String address;
    private String userId;
    private String tel;

}
