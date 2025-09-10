package kr.co.books.member.service;

import kr.co.books.member.dto.ReqEmailDTO;

public interface AuthService {
	
	 void sendEmailCode(ReqEmailDTO emailDTO);

}
