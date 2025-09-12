package kr.co.books.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.books.member.dto.RegisterDTO;
import kr.co.books.member.dto.RegisterRequestDTO;
import kr.co.books.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	@GetMapping("/register/form")
	public String registerForm(Model model) {
		model.addAttribute("registerDTO", new RegisterDTO());
		return "member/register";
	}
	
	@PostMapping("/register")
	public String register(RegisterRequestDTO registerRequest,  @RequestParam("inputCode")String inputCode) {
		memberService.register(registerRequest, inputCode);
		return "redirect:/";
	}
}
