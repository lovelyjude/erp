package com.example.erp.faq.controller;


import com.example.erp.faq.dto.NoticeDto;
import com.example.erp.faq.service.NoticeSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
        model.addAttribute("notice", notice);
        return "faq/notice/notice_list";  //공지사항 리스트 띄어주기용
    }

    //글추가페이지
    @GetMapping("/notice_add")
    public String notice_add() {
//        System.out.println("세션내용:" + session.getAttribute("loginId"));
        return "faq/notice/notice_add";
    }

    //글추가 처리 -> 권한이 admin일때만 되도록.
    @PostMapping("/notice_ok")
    @ResponseBody
    public String notice_ok(@ModelAttribute NoticeDto noticeDTO, HttpSession session) throws IOException {
        System.out.println("세션내용:" +  session.getAttribute("loginId"));
        noticeDTO.setWriter(session.getAttribute("loginId").toString());
        noticeSerivce.save(noticeDTO); //writer은 session에서 받아욜 예정
        return "공지사항 추가 완료.";
    }


    //공지사항 세부정보
    @GetMapping("/notice_memo/{id}")
    public String n_memo(Model model, @PathVariable long id) {
        model.addAttribute("notice", noticeSerivce.findById(id));
        return "faq/notice/notice_memo";
    }

    //공지사항 수정폼
    @GetMapping("/notice_edit/{id}")
    public String re_notice(Model model, @PathVariable long id) {
        model.addAttribute("notice", noticeSerivce.findById(id));
        return "faq/notice/notice_edit";
    }

    //글수정 처리
    @PostMapping("/notice_edit_ok")
    @ResponseBody
    public String edit_ok(@RequestParam(name = "id") Long id, @ModelAttribute NoticeDto noticeDto){

        noticeSerivce.update(id,noticeDto);
        return "공지사항 수정 완료";
    }


}
