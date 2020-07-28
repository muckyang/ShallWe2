package com.web.blog.model.user;

import java.time.LocalDate;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    String email;
}
