package kr.co.books.global.exception;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.books.member.exception.MemberLoginException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
 @ExceptionHandler(MemberLoginException.class)
 public void handleMemberLoginException(MemberLoginException e, HttpServletResponse response) throws IOException {
	    response.setContentType("text/html; charset=UTF-8");
	    String message = e.getMessage() != null ? e.getMessage() : "로그인 중 오류가 발생했습니다.";
	    response.getWriter().println("<script>alert('" + message + "'); history.back();</script>");
	    response.getWriter().flush();
	}

}
