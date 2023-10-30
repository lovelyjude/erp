package com.example.erp.faq.entity;


import com.example.erp.faq.dto.NoticeDto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name="notice_table")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String noticeTitle;

    @Column
    private String noticeMemo;

    @Column
    private String writer;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;

    public static NoticeEntity toSaveEntity(NoticeDto noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeMemo(noticeDTO.getNoticeMemo());
        noticeEntity.setWriter(noticeDTO.getWriter());
        return noticeEntity;
    }


    //시간되면 더티체킹 수정용.
    public void update(String noticeTitle, String noticeMemo){
        this.noticeTitle = noticeTitle;
        this.noticeMemo = noticeMemo;
    }


}
