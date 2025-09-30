package kr.co.books.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailVerifyRequestDTO {
	
    @NotBlank
    private String email;

    @NotBlank
    private String inputCode;


}
