package kr.co.books.member.service;

import jakarta.servlet.http.HttpSession;
import kr.co.books.member.dto.LoginRequestDTO;
import kr.co.books.member.dto.LoginResponseDTO;
import kr.co.books.member.dto.RegisterDTO;
import kr.co.books.member.dto.RegisterRequestDTO;

public interface MemberService {

	void register(RegisterRequestDTO registerRequest, String inputCode);

	LoginResponseDTO login(LoginRequestDTO loginRequest);

}
