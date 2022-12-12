package com.teamhw.opensurveyservice.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article; // 본문
    private String title; // 제목

    private LocalDateTime createdAt; // 생성일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일시
    private String modifedBy; // 수정자
}