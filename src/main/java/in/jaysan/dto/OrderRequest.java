package in.jaysan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderItem> orderedItems;
    private String userAddress;
    private double amount;
    private String email;
    private String phoneNumber;

    private String orderStatus;
}
