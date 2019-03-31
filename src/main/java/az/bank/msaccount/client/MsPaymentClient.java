package az.bank.msaccount.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static az.bank.msaccount.model.client.auth.HttpHeader.X_AUTH_TOKEN;

@Component
@FeignClient(name = "ms-payment", url = "http://localhost:8585/accounts/payment")
public interface MsPaymentClient {
    @PostMapping("/validate")
    boolean validateToken(@RequestHeader(X_AUTH_TOKEN) String token);
}