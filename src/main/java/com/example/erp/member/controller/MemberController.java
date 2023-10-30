package com.example.erp.member.controller;

import com.example.erp.member.dto.LoginDto;
import com.example.erp.member.dto.MemberDto;
import com.example.erp.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final LoginService loginService;

    //로그인폼
    @GetMapping("/")
    public String login(){
        return "/login";    //첫로그인화면으로
    }

    //로그인시도
    @PostMapping("login")
    public String logins(@ModelAttribute LoginDto loginDto, HttpSession session, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {//에러날시
            return "redirect:/login"; //첫화면으로
        }
        System.out.println("로그인테스트 첨 온것" + loginDto);
        MemberDto loginResult = loginService.login(loginDto);

        if (loginResult != null) {
            session.setAttribute("loginId", loginResult.getUserId());
            session.setAttribute("loginName", loginResult.getUserName());
            System.out.println("로그인 성공");
            return "/index"; // 로그인 성공 시 리다이렉트할 URL을 설정하세요.
        } else {
            System.out.println("로그인 실패");
            return "redirect:/";
        }
    }

    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index";
    }
}
