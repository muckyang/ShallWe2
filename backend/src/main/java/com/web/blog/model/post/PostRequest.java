package com.web.blog.model.post;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Data
@Valid
public class PostRequest {

    int articleId;
    int categoryId;
    int userId;
    @ApiModelProperty(required = true)
    @NotNull
    String title;
    String writer;
    @ApiModelProperty(required = true)
    @NotNull
    String address;
    @ApiModelProperty(required = true)
    @NotNull
    int minPrice;
    @ApiModelProperty(required = true)
    @NotNull
    String description;
    String urlLink;
    String image;
    String token;
    int temp;
    LocalDateTime endTime;
        
}
