package com.example.erp.faq.controller;


import com.example.erp.faq.dto.NoticeDto;
import com.example.erp.faq.service.NoticeSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeSerivce noticeSerivce;


    //공지사항 리스트 보여주기용(처음들어왔을때)
    @GetMapping("/notice_list")
    public String listNotices(Model model) {
        List<NoticeDto> notice = noticeSerivce.getAllNotices();
        System.out.println("내용:" + notice);
        model.addAttribute("notice", notice);
        return "notice_list";  //공지사항 리스트 띄어주기용
    }

    //글추가페이지
    @GetMapping("/notice_add")
    public String notice_add(){
        return "notice_add";  //글추가할 페이지입니다.
    }

    //글추가 처리
    @PostMapping("/notice_ok")
    public String notice_ok(@ModelAttribute NoticeDto noticeDTO) throws IOException {
        noticeSerivce.save(noticeDTO); //writer은 session에서 받아욜 예정입니다.
        return "redirect:/notice_list"; //글추가 끝나고 갈페이지입니다.
    }
}
