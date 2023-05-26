package net.daum.vo;


import lombok.Data;

@Data
public class Food_MemberVO {
    private String user_id; //회원 아이디
    private String user_pwd; //회원 비밀번호
    private String user_name; //회원 이름
    private String user_birth; //회원 생년월일
    private String user_email; //회원 이메일
    private String user_phone; //회원 휴대폰번호
    private String search_type;
    private String search_field;
}
