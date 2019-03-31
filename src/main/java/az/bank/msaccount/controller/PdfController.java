package az.bank.msaccount.controller;


import az.bank.msaccount.security.UserAuthentication;
import az.bank.msaccount.service.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class PdfController {

    private final PdfService pdfService;

    @GetMapping("/pdf/download")
    public ResponseEntity<InputStreamResource> downloadPdfFile(@RequestHeader("X-Auth-Token") String token,
                                                               String accountId,
                                                               UserAuthentication userAuthentication) {
        return pdfService.downloadPdfFile(accountId, userAuthentication.getPrincipal());
    }
}
