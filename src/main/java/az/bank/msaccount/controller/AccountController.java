package az.bank.msaccount.controller;

import az.bank.msaccount.model.AccountRequest;
import az.bank.msaccount.model.dto.AccountDto;
import az.bank.msaccount.security.UserAuthentication;
import az.bank.msaccount.service.AccountService;
import az.bank.msaccount.service.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;


    @GetMapping("/customer")
    public List<AccountDto> getAccounts(@RequestHeader("X-Auth-Token") String token,
                                        UserAuthentication userAuthentication) {
        return service.getAccounts(userAuthentication.getPrincipal());
    }

    @PostMapping("/filter-accounts")
    public List<AccountDto> getAccounts(
            @RequestHeader("X-Auth-Token") String token,
            @RequestBody AccountRequest accountRequest,
            UserAuthentication userAuthentication) {
        return service.getAccounts(accountRequest, userAuthentication.getPrincipal());
    }

    @GetMapping("/customer-by-currency")
    public List<AccountDto> filterAccounts(@RequestHeader("X-Auth-Token") String token,
                                        UserAuthentication userAuthentication,
                                        @RequestParam String currency) {
        return service.filterAccounts(userAuthentication.getPrincipal(), currency);
    }


   /* private final PdfService pdfService;

    @GetMapping("/pdf/download")
    public ResponseEntity<InputStreamResource> downloadPdfFile(@RequestHeader("X-Auth-Token") String token,
             UserAuthentication userAuthentication
//                    ,
//             @RequestParam String accountId
            ) {
        return pdfService.downloadPdfFile(null, userAuthentication.getPrincipal());
    }
    */
}


