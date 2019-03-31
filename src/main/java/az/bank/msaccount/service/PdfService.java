package az.bank.msaccount.service;

import az.bank.msaccount.model.dto.OperationDto;
import az.bank.msaccount.utils.PdfCreatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
public class PdfService {
    private final OperationService operationService;
    private final PdfCreatorUtil pdfCreatorUtil;

    public ResponseEntity<InputStreamResource> downloadPdfFile(String accountId, String customerId) {
        System.out.println(customerId);
        List<OperationDto> operations = operationService.getOperations(accountId, customerId);
        if (operations.isEmpty()){
            throw new RuntimeException("No operations are found");
        }
        pdfCreatorUtil.createPdfFile(operations);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        String DIRECTORY = "C:\\Users\\Nigar\\Downloads\\account.pdf";
        MediaType mediaType = MediaType.parseMediaType("application/pdf");
        File file = new File(DIRECTORY);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }
}
