package kr.co.books.member.service.impl;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.co.books.member.dto.ReqEmailDTO;
import kr.co.books.member.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JavaMailSender mailSender;
    
    // 마지막 발송 코드 저장
    private String lastCodeSent;

    @Override
    public void sendEmailCode(ReqEmailDTO emailDTO) {
        String code = generateCode();
        sendEmail(emailDTO.getEmail(), code);
        lastCodeSent = code;
    }

    private String generateCode() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000); // 6자리
        return String.valueOf(number);
    }

    private void sendEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tjrdud471@naver.com");
        message.setTo(to);
        message.setSubject("[서비스명] 이메일 인증 코드");
        message.setText("요청하신 인증 코드는 [" + code + "] 입니다.\n3분 내에 입력해주세요.");
        mailSender.send(message);
    }

    @Override
    public boolean verifyCode(String inputCode) {
        return lastCodeSent != null && lastCodeSent.equals(inputCode);
    }
}
