package az.bank.msaccount.service;

public interface EmailService {
    void send(String to, String title, String body);
}
