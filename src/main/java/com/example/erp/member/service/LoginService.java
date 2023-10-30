package com.example.erp.member.service;


import com.example.erp.member.dto.LoginDto;
import com.example.erp.member.dto.MemberDto;
import com.example.erp.member.entity.MemberEntity;
import com.example.erp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;



    //로그인기능
    public MemberDto login(LoginDto loginDto) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(loginDto.getUserId());
        if (byUserId.isPresent()){
            //조회 결과가 있다면
            MemberEntity memberEntity = byUserId.get();
            System.out.println("조회결과엔티티에서 : " + memberEntity.getUserId() +memberEntity.getUserPass());
            System.out.println("조회결과dto에서 : " + loginDto.getUserId()+ loginDto.getUserPass());

            if (memberEntity.getUserPass().equals(loginDto.getUserPass())){
                //비밀번호 일치
                //entity -> dto 변환
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            //조회 결과가 없다
            return null;
        }

    }
}
