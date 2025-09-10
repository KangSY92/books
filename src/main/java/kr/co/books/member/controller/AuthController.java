package kr.co.books.member.controller;

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

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody ReqEmailDTO emailDTO) {
        authService.sendEmailCode(emailDTO);
        return ResponseEntity.ok("success");
    }
}