package com.web.blog.model.post;
import java.util.List;


import io.swagger.annotations.ApiModelProperty;


public class PostListResponse {
    @ApiModelProperty(value = "postList", position = 1)
    public List<PostResponse> postList;
}