package com.web.blog.model.comment;


import java.time.LocalDateTime;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentingRequest {
    int no;
    String content;
    String writer;
    LocalDateTime create_time;
    int articleno;
}