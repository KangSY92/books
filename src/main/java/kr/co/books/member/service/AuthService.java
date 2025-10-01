package kr.co.books.member.service;

import jakarta.servlet.http.HttpSession;
import kr.co.books.member.dto.EmailRequestDTO;

public interface AuthService {
	
	 void sendEmailCode(EmailRequestDTO emailDTO);

	 boolean verifyCode(String email, String inputCode);
	 
	 void clearCode(String email);
}
