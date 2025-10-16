package kr.co.books.member.dto;

import kr.co.books.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponseDTO {
	
	private int memberId;
	private String id;
	private String name;
	private String password;
	private String phone;
	private String email;
	
	
	 private String accessToken;
	 private String refreshToken;
	
	public static LoginResponseDTO from(Member result, String accessToken, String refreshToken) {
		return LoginResponseDTO.builder()
				.memberId(result.getMemberId())
				.id(result.getId())
				.name(result.getName())
				.password(result.getPassword())
				.phone(result.getPhone())
				.email(result.getEmail())
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.build();
	}

}
