package com.example.erp.faq.service;


import com.example.erp.faq.dto.NoticeDto;
import com.example.erp.faq.entity.NoticeEntity;
import com.example.erp.faq.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeSerivce {

    private final NoticeRepository noticeRepository;


    //리스트띄어주기
    public List<NoticeDto> getAllNotices() {
        List<NoticeEntity> noticeEntities = noticeRepository.findAll();
        return noticeEntities.stream()
                .map(NoticeDto::toNoticeDTO)
                .collect(Collectors.toList());
    }

    //저장용
    @Transactional
    public void save(NoticeDto noticeDTO) throws IOException {
        NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
        noticeRepository.save(noticeEntity); //entity값으로 반환
    }




}
