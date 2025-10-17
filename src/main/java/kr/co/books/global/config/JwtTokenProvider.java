package kr.co.books.global.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/*
 * JwtTokenProvider 클래스
 * - JWT 토큰을 생성하고 검증하는 역할을 담당함
 * - 로그인 성공 시 AccessToken, RefreshToken을 생성할 때 사용
 * - 토큰 안에는 사용자 식별 정보(ID)와 만료시간이 들어 있음
 */

@Component
public class JwtTokenProvider {

	// JWT 암호화를 위한 비밀키 객체
	// application.properties의 jwt.secret 값으로 초기화됨
	private final SecretKey key;

	// AccessToken과 RefreshToken의 유효 시간 (밀리초 단위)
	private final long accessTokenValidity;
	private final long refreshTokenValidity;

	/*
	 * 생성자
	 * 
	 * @Value 어노테이션으로 application.properties에 설정된 값을 읽어옴 secretKey는 문자열로 받아서
	 * HMAC-SHA256 방식으로 암호화할 수 있는 SecretKey 객체로 변환
	 */
	public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
			@Value("${jwt.access-token-validity}") long accessTokenValidity,
			@Value("${jwt.refresh-token-validity}") long refreshTokenValidity) {

		// 문자열 형태의 secretKey를 SecretKey 객체로 변환 (JWT 서명 시 사용됨)
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes());

		// 유효 시간 값 세팅 (application.properties에서 지정한 값)
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
	}

	/*
	 * AccessToken 생성 메서드 AccessToken은 짧은 유효기간을 가지며, 사용자의 로그인 상태를 인증할 때 사용됨
	 * 
	 * @param id : 사용자 ID
	 * 
	 * @return AccessToken 문자열
	 */
	public String generateAccessToken(String id) {
		return Jwts.builder().setSubject(id) // 토큰의 "주제" 부분에 사용자 ID 저장
				.setIssuedAt(new Date()) // 발급 시간
				.setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity)) // 만료 시간 설정
				.signWith(key, SignatureAlgorithm.HS256) // 서명 (변조 방지용)
				.compact(); // JWT 문자열로 변환해서 반환
	}

	/*
	 * RefreshToken 생성 메서드 RefreshToken은 AccessToken이 만료됐을 때 새로 발급받기 위해 사용됨 유효기간이
	 * AccessToken보다 훨씬 김 (보통 7일 정도)
	 * 
	 * @param id : 사용자 ID
	 * 
	 * @return RefreshToken 문자열
	 */
	public String generateRefreshToken(String id) {
		return Jwts.builder().setSubject(id).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}

	/*
	 * 토큰 안에 들어있는 사용자 ID를 꺼내는 메서드 (JWT의 subject 값이 사용자 ID로 저장되어 있음)
	 */
	public String getUserId(String token) {
		return parseClaims(token).getSubject();
	}

	/*
	 * 토큰이 정상적으로 만들어졌고 아직 만료되지 않았는지 확인하는 메서드 - 변조되었거나 유효기간이 끝나면 false 반환
	 */
	public boolean validateToken(String token) {
		try {
			parseClaims(token); // 내부적으로 파싱하며 서명 검증까지 수행됨
			return true; // 예외가 없으면 유효한 토큰
		} catch (Exception e) {
			// 만료되었거나, 서명이 틀린 경우 예외 발생
			return false;
		}
	}

	/*
	 * JWT 내부의 payload(Claims) 부분을 꺼내는 메서드 - Claims 안에는 subject(사용자 ID), 발급시간, 만료시간
	 * 등의 정보가 들어 있음 - 이 과정에서 서명 검증도 함께 이루어짐
	 */
	private Claims parseClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key) // 비밀키를 설정해서 서명 검증
				.build().parseClaimsJws(token) // 토큰 파싱 (검증 포함)
				.getBody(); // 토큰 본문(Claims) 반환
	}

}
