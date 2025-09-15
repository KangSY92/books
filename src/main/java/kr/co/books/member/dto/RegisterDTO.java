package kr.co.books.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
	
	private int memberId;
	private String id;
	private String name;
	private String password;
	private String phone;
	private String address;
	private String addressDetail;
	private String createDate;
	private String updateDate;
	private String deleteDate;
	private String email;

}
