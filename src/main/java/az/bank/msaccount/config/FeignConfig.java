package az.bank.msaccount.config;

import az.bank.msaccount.client.MsAuthenticationClient;
import az.bank.msaccount.client.MsPaymentClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        MsAuthenticationClient.class, MsPaymentClient.class})
public class FeignConfig {
}