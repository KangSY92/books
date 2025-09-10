package kr.co.books.member.service;

import kr.co.books.member.dto.RegisterDTO;
import kr.co.books.member.dto.RegisterRequestDTO;

public interface MemberService {

	void register(RegisterRequestDTO registerRequest);

}
