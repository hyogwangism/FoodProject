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
    private String user_gender; //회원 성별
    private int user_state; //회원 상태(일반회원 0, 탈퇴회원 1)
    
    //검색필드
    private String search_type; //검색 필터
    private String search_field; //검색어
}
