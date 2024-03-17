package com.example.core.email;

import com.example.core.email.dto.MailInfo;
import com.example.core.email.dto.QuestionData;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@RequiredArgsConstructor
@Service
public class EmailSendService {

    private static final String TITLE = "스터디허브 이메일 인증 번호";
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final EmailCacheService emailCacheService;

    @Value("${spring.mail.username}")
    private String emailAddress;

    public MimeMessage createEmailForm(String toEmail) throws MessagingException {
        String authCode = emailCacheService.getAndCacheAuthCode(toEmail);
        String setFrom = emailAddress;

        MimeMessage message = emailSender.createMimeMessage();
        addMessageForm(message, toEmail, setFrom, setContext(authCode));
        return message;
    }

    private void addMessageForm(MimeMessage message, String toEmail, String setFrom, String authCode) throws MessagingException {
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail); //받는 이메일 설정
        message.setSubject(TITLE); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(authCode, "utf-8", "html");
    }

    public void sendEmail(MailInfo info) throws MessagingException {
        String toEmail = info.getEmail();
        MimeMessage emailForm = createEmailForm(toEmail);
        emailSender.send(emailForm);
    }

    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("mail", context);
    }

    public String setQuestionContext(String content, String toEmail) {
        Context context = new Context();
        context.setVariable("content", content);
        context.setVariable("toEmail", toEmail);
        return templateEngine.process("question", context); //mail.html
    }

    public void sendQuestionEmail(QuestionData data) throws MessagingException {
        MimeMessage emailForm = createQuestionEmailForm(data.getToEmail(), data.getTitle(), data.getContent());
        emailSender.send(emailForm);
    }

    private MimeMessage createQuestionEmailForm(String toEmail, String questionTitle, String questionContent) throws MessagingException {
        String setFrom = emailAddress; //email-config 에 설정한 자신의 이메일 주소(보내는 사람)
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, emailAddress); //받는 이메일 설정
        message.setSubject(questionTitle); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(setQuestionContext(questionContent, toEmail), "utf-8", "html");
        return message;
    }
}
