package kr.co.books.global.config;

public class MailConfig {
    public static final String MAIL_FROM = "tjrdud471@naver.com";
    public static final String MAIL_SUBJECT = "회원가입 인증 코드";
    public static final String MAIL_CONTENT = "요청하신 인증 코드는 [%s] 입니다.\n5분 내에 입력해주세요.";

    private MailConfig() {}

}
