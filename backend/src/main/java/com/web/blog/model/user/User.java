package com.web.blog.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "user") // 이어줄 테이블명 지정 
public class User  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;
    private String address;
    private LocalDate birthday;


}