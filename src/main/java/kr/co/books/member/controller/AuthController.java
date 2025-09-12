package kr.co.books.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.books.member.dto.ReqEmailDTO;
import kr.co.books.member.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 메일 발송
    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody ReqEmailDTO emailDTO) {
        authService.sendEmailCode(emailDTO);
        return ResponseEntity.ok("success");
    }
    
 // 인증번호 확인
    @PostMapping("/email/verify")
    public ResponseEntity<String> verifyCode(@RequestBody Map<String, String> body) {
        String inputCode = body.get("inputCode"); // JSON에서 가져오기
        boolean result = authService.verifyCode(inputCode);
        return result ? ResponseEntity.ok("success") : ResponseEntity.ok("fail");
    }
}
