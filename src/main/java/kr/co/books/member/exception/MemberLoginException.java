package kr.co.books.member.exception;

import java.io.Serial;

public class MemberLoginException extends RuntimeException {
	
	/**
	 * 직렬화/역직렬화 과정에서 클래스의 버전 일치 여부를 검사하기 위한 고유 ID
	 * 클래스 구조가 변경될 경우 이 값을 변경해야 합니다.
	 * 
	 *  - 형식 : 년(4) + 월(2) + 일(2) + 시(2) + 분(2) + 변경횟수(3)
	 *  - 예시 : 202507081110001 -> 2025년 7월 8일 11시 10분, 첫 번째 변경
	 */
	@Serial
	private static final long serialVersionUID = 202507081110001L;

	/**
	 * 메시지와 원인 예외를 함께 전달하는 생성자입니다.
	 * @param message 예외에 대한 설명 메시지
	 * @param cause 예외 원인이 되는 하위 예외 객체
	 */
	public MemberLoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
