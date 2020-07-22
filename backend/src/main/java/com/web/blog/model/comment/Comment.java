package com.web.blog.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.hibernate.annotations.ManyToAny;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "comment") // 이어줄 테이블명 지정

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    
    private String content;
    private String writer;

    private int articleno;
    
}