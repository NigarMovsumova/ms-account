package az.bank.msaccount.controller;

import az.bank.msaccount.model.MailRequest;
import az.bank.msaccount.security.UserAuthentication;
import az.bank.msaccount.service.EmailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final EmailServiceImpl emailServiceImpl;

    @PostMapping("/send")
    public void sendOperationsByEmail(@RequestHeader("X-Auth-Token") String token,
                                      UserAuthentication userAuthentication,
                                      @RequestBody MailRequest request
                                      //@RequestParam String toEmailAddress
    ) {
        emailServiceImpl.sendmail(request, userAuthentication.getPrincipal());
    }
}
