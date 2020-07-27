package com.web.blog.model.participant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "participant") // 이어줄 테이블명 지정

public class Participant {
    @Id
    private int no;
    private int userId;
    private int articleId;
    private String title;
    private int price;
    private String description;

}