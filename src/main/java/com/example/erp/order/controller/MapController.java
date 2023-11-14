package com.example.erp.order.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MapController {

    @GetMapping("/map")
    public String map(){
        return "/order/map";    //첫로그인화면으로
    }
}
