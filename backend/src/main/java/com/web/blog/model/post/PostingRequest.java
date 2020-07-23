package com.web.blog.model.post;


import java.time.LocalDateTime;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostingRequest {
    int pid;
    String title;
    int memberAmount;
    int price;
    String description;
    String writer;
    LocalDateTime create_time;
    int temp;

}
