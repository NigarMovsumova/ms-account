package az.bank.msaccount.service;

import az.bank.msaccount.model.MailRequest;
import az.bank.msaccount.model.dto.OperationDto;
import az.bank.msaccount.utils.PdfCreatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
@AllArgsConstructor
public class EmailServiceImpl {
    private final OperationService operationService;
    private final PdfCreatorUtil pdfCreatorUtil;

    public void sendmail(MailRequest request, String customerId) {
        List<OperationDto> operations = operationService.getOperations(request.getAccountId(), customerId);
        System.out.println(Arrays.toString(operations.toArray()));
        if (operations==null|| operations.isEmpty()){
            throw new RuntimeException("No operations for this account");
        }
        pdfCreatorUtil.createPdfFile(operations);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("movsum.nigar@gmail.com", "Er159951.");
            }
        });
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress("movsum.nigar@gmail.com", false));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getEmail()));

            msg.setSubject("CoinBank Account Statement");
            //msg.setContent("Please find enclosed Your account statement(s)", "text/html");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("Dear Customer,", "text/plain");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setContent("\nPlease find enclosed Your account statement(s).\n\nKind Regards,\nCoin Bank Team", "text/plain");
            multipart.addBodyPart(messageBodyPart1);
            MimeBodyPart confidentialityFooter = new MimeBodyPart();
            confidentialityFooter.setContent(
                    "<br>" +
                            "<pre><h4>Contact Center</h4>" +
                            "Address: Baku, Azerbaijan" +
                            "<br>" +
                            "tel.: +994505532280 | fax: +994505532280" +
                            "email: info@coinbank.az | coinbank.az" +
                            "</pre>" +
                            "<pre>Please do not hesitate to comment to customer.care@coinbank.az on service quality. Thank you!*/\n" + "</pre>" +
                            "<h4>PRIVILEGE AND CONFIDENTIALITY NOTICE</h4>" +
                            "<pre>This communication contains information issued by Coin Bank OJSC. " +
                            "This e-mail message and all attachments transmitted with it are intended" +
                            "solely for the use of the addressee and may contain legally privileged" +
                            "and confidential information.  If the reader of this message is not the" +
                            "intended recipient, or an employee or agent responsible for delivering this" +
                            "message to the intended recipient, the reader is hereby notified that any" +
                            "dissemination, distribution, copying, or other use of this message or its " +
                            "attachments is strictly prohibited.  If you have received this message in error, " +
                            "please notify the sender immediately by replying to this message and please delete" +
                            " it from your computer. Within the bounds of law Coin Bank OJSC may monitor " +
                            "electronic transmissions through its internal and external networks to ensure " +
                            "compliance with internal policies and for legitimate business purposes.</pre>", "text/html");
            multipart.addBodyPart(confidentialityFooter);


            MimeBodyPart attachPart = new MimeBodyPart();
            try {
                attachPart.attachFile("C:\\Users\\Nigar\\Downloads\\account.pdf");
            } catch (IOException e) {
                e.printStackTrace();
            }
            multipart.addBodyPart(attachPart);

            String imageUrl = "C:\\Users\\Nigar\\Downloads\\na disk\\na disk\\untitled14\\src\\com\\company\\bitcoin\\icons\\get-money.png";


            msg.setContent(multipart);
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

