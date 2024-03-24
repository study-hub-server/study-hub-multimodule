package com.example.webclient.api.email;

import com.example.core.email.EmailSendService;
import com.example.webclient.api.email.dto.MailSendRequest;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {

    private final EmailSendService emailSendService;

//    @Operation(summary = "이메일 인증코드 전송", description = "바디에 {email} json 형식으로 보내주시면 됩니다. ")
//    @PostMapping("/v1/email")
//    public ResponseEntity<HttpStatus> sendEmail(@Valid @RequestBody MailSendRequest request) throws MessagingException {
//        emailSendService.sendEmail(request.toMailInfo());
//        return ResponseEntity.ok().build();
//    }

}
