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
    
    //발송코드 저장용
    private String lastCodeSent;
	
    @Override
    public void sendEmailCode(ReqEmailDTO emailDTO) {
        String code = generateCode();
        sendEmail(emailDTO.getEmail(), code);
        
        lastCodeSent = code; // 발송한 코드 저장
    }

    // 랜덤 6자리 코드 생성
    private String generateCode() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000); // 100000 ~ 999999
        return String.valueOf(number);
    }

    // 이메일 전송
    private void sendEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tjrdud471@naver.com"); // application.properties에 작성한 username
        message.setTo(to);
        message.setSubject("[서비스명] 이메일 인증 코드");
        message.setText("요청하신 인증 코드는 [" + code + "] 입니다.\n3분 내에 입력해주세요.");

        mailSender.send(message);
    }
    
    //문자열 비교
    public boolean verifyCode(String inputCode) { // 단순 문자열 비교
        if (lastCodeSent == null) {
        	return false;   // 코드가 발송되지 않았으면 실패
        	} else if (lastCodeSent.equals(inputCode)) {
        		return true;
        	} else {
        		System.out.println("코드가 일치하지 않습니다.");
        		return false;
        	}
    }
    
}
