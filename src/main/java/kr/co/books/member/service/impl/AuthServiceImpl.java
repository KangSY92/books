package kr.co.books.member.service.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static kr.co.books.global.config.MailConfig.*;
import kr.co.books.member.dto.EmailRequestDTO;
import kr.co.books.member.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JavaMailSender mailSender;
    
    private final StringRedisTemplate redisTemplate;
    
    @Value("${spring.mail.username}")
    private String mailFrom;
    
    @Override
    public void sendEmailCode(EmailRequestDTO emailDTO) {
    	
        // 1. 인증코드 생성
        String code = generateCode();

        // 2. 이메일 발송
        sendEmail(emailDTO.getEmail(), code);

        // 3. Redis에 저장 (TTL 5분)
        String emailKey = getKey(emailDTO.getEmail());
        redisTemplate.opsForValue().set(emailKey, code, 5, TimeUnit.MINUTES);
        
        System.out.println("sendEmailCode =" + code);
    }

    private String generateCode() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000); // 6자리
        return String.valueOf(number);
    }

    private void sendEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(to);
        message.setSubject(MAIL_SUBJECT);
        message.setText(String.format(MAIL_CONTENT, code));
        mailSender.send(message);
    }

    @Override
    public boolean verifyCode(String email, String inputCode) {
    	
        String emailKey = getKey(email);
        String savedCode = redisTemplate.opsForValue().get(emailKey);
                
        return savedCode != null && savedCode.equals(inputCode);
    }    
    
    // 성공 시점에 삭제
    @Override
    public void clearCode(String email) {
        redisTemplate.delete(getKey(email));
    }
  
    private String getKey(String email) {
        return "emailCode:" + email.trim().toLowerCase();
    }
}
