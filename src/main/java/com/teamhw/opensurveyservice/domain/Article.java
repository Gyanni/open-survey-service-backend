package com.teamhw.opensurveyservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
// index는 보통 사이즈가 작다. 게시글의 내용은 사이즈가 큰데 이는 ElasticSearch의 도움을 받는다.
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@Entity
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Setter @Column(nullable = false)                 private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter private String hashtag; // 해시태그

    @CreatedDate @Column(nullable = false)                  private LocalDateTime createdAt; // 생성일시
    @CreatedBy @Column(nullable = false, length = 100)      private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false)             private LocalDateTime modifiedAt; // 수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifedBy; // 수정자

    protected Article() {
    }

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // design pattern
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    // 동등성 검사는 오직 id로 가능하다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

// @Setter를 특정 필드에 따로 적용하는 이유는 사용자가 특정 필드에 접근하는 것을 막기 위해서이다.