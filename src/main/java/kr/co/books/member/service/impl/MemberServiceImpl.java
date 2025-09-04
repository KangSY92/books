package kr.co.books.member.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.books.member.dto.RegisterDTO;
import kr.co.books.member.mapper.MemberMapper;
import kr.co.books.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public void register(RegisterDTO registerDTO) {
		
		String rawPassword = registerDTO.getPassword();
		String passEncode = passwordEncoder.encode(rawPassword);
		
		registerDTO.setPassword(passEncode);
		memberMapper.register(registerDTO);
		
	}

}
