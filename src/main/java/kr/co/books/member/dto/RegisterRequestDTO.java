package kr.co.books.member.dto;

import kr.co.books.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

	private String id;
	private String name;
	private String password;
	private String phone;
	private String email;
	private String address;
	private String addressDetail;
	
	private String inputCode;
	
	
	public Member toMember(String passEncode) {
		return Member.builder()
				.id(id)
				.name(name)
				.password(passEncode)
				.phone(phone)
				.email(email)
				.address(address)
				.addressDetail(addressDetail)
				.build();
		
	}
	
}
