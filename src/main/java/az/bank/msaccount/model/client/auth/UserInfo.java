package az.bank.msaccount.model.client.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    // private Long userId;
    private String customerId;
    private String email;
}
