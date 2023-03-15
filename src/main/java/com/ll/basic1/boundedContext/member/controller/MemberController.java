package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.RsData;
import com.ll.basic1.boundedContext.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        memberService = new MemberService();
    }

    @GetMapping("member/login")
    @ResponseBody
    public RsData loginResult(String username, String password){

        return memberService.tryLogin(username, password);
    }
}
