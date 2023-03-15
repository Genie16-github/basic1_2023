package com.ll.basic1.boundedContext.member.service;

import com.ll.basic1.base.RsData;

public class MemberService {
    public RsData tryLogin(String username, String password) {
        if (username.equals("user1")){
            if (password.equals("1234")){
                return RsData.of("S-1", "%s 님 환영합니다.".formatted(username));
            }
            else{
                return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
            }
        }
        return RsData.of("S-1", "%s 님 환영합니다.".formatted(username));
    }
}
