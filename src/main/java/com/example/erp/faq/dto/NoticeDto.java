package com.example.erp.faq.dto;


import com.example.erp.faq.entity.NoticeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class NoticeDto {
    private Long id;
    private String noticeTitle;
    private String noticeMemo;
    private String writer;
    private LocalDateTime noticeCreatedTime;



    public static NoticeDto toNoticeDTO(NoticeEntity noticeEntity){
        NoticeDto noticeDTO = new NoticeDto();;
        noticeDTO.setId(noticeEntity.getId());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeCreatedTime(noticeEntity.getCreatedTime());
        noticeDTO.setNoticeMemo(noticeEntity.getNoticeMemo());
        noticeDTO.setWriter(noticeEntity.getWriter());
        return noticeDTO;
    }
}
