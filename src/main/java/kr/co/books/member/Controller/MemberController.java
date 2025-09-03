package kr.co.books.member.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.books.member.dto.RegisterDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

	@GetMapping("/register/form")
	public String registerForm(Model model) {
		model.addAttribute("registerDTO", new RegisterDTO());
		return "member/register";
	}
	
}
