package com.web.blog.model.user;

import java.time.LocalDate;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    int uid;
    String id;
    String email;
    String password;
    String name;
    String nickname;
    LocalDate birthday;

}
