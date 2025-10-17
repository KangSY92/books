package kr.co.books.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.books.member.dto.LoginRequestDTO;
import kr.co.books.member.dto.LoginResponseDTO;
import kr.co.books.member.dto.RegisterDTO;
import kr.co.books.member.dto.RegisterRequestDTO;
import kr.co.books.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	@Value("${jwt.access-token-validity}")
	private long accessTokenValidity;

	@Value("${jwt.refresh-token-validity}")
	private long refreshTokenValidity;

	@GetMapping("/register/form")
	public String registerForm(Model model) {
		model.addAttribute("registerDTO", new RegisterDTO());
		return "member/register";
	}
	
	@PostMapping("/register")
	public String register(RegisterRequestDTO registerRequest) {
		memberService.register(registerRequest, registerRequest.getInputCode());
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(LoginRequestDTO loginRequest, HttpSession session,
			HttpServletResponse response) {
		LoginResponseDTO result = memberService.login(loginRequest);
		
		if(result == null) {
			System.out.println("존재하지 않는 사용자 입니다.");
			return "redirect:/";
		}
		
		if(!passwordEncoder.matches(loginRequest.getPassword(), result.getPassword())) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return "redirect:/";
		}
		
	    // 쿠키에 JWT 저장
	    Cookie accessCookie = new Cookie("accessToken", result.getAccessToken());
	    accessCookie.setHttpOnly(true);
	    accessCookie.setPath("/");
	    accessCookie.setMaxAge((int) accessTokenValidity); // 30분

	    Cookie refreshCookie = new Cookie("refreshToken", result.getRefreshToken());
	    refreshCookie.setHttpOnly(true);
	    refreshCookie.setPath("/");
	    refreshCookie.setMaxAge((int) refreshTokenValidity); // 7일

	    response.addCookie(accessCookie);
	    response.addCookie(refreshCookie);
		
		session.setAttribute("id", result.getId());
		session.setAttribute("name", result.getName());
		session.setAttribute("phone", result.getPhone());
		session.setAttribute("email", result.getEmail());
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 무효화
	return "redirect:/";
		
	}
}
