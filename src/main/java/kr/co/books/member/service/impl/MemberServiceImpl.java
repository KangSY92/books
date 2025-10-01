package kr.co.books.member.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import kr.co.books.member.domain.Member;
import kr.co.books.member.dto.RegisterDTO;
import kr.co.books.member.dto.RegisterRequestDTO;
import kr.co.books.member.mapper.MemberMapper;
import kr.co.books.member.service.AuthService;
import kr.co.books.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	
	private final AuthService authService; 
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public void register(RegisterRequestDTO registerRequest,
						 String inputCode) {
		
        String email = registerRequest.getEmail(); // 이메일 가져오기
        
		//비밀번호 암호화
		String rawPassword = registerRequest.getPassword();
		String passEncode = passwordEncoder.encode(rawPassword);
		
		Member member = registerRequest.toMember(passEncode);
		
		//이메일 인증 확인
		if(authService.verifyCode(email, inputCode)) {
			
			memberMapper.register(member);
			authService.clearCode(email);
		} else {
			System.out.println("이메일 인증 실패");
		}		
	}

}
