package kr.co.books.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	
	private int memberId;
	private String id;
	private String name;
	private String password;
	private String phone;
	private String email;
	private String address;
	private String addressDetail;
	private String createDate;
	private String updateDate;
	private String deleteDate;

}
