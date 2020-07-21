package com.web.blog.model;

import java.util.List;

import com.web.blog.model.post.Post;

import io.swagger.annotations.ApiModelProperty;

public class PostResponse {
     // post
     @ApiModelProperty(value = "pid", position = 1)
     public int pid;
     @ApiModelProperty(value = "title", position = 2)
     public String title;
     @ApiModelProperty(value = "memberAmount", position = 3)
     public int memberAmount;
     @ApiModelProperty(value = "price", position = 4)
     public int price;
     @ApiModelProperty(value = "description", position = 5)
     public String description;
     @ApiModelProperty(value = "writer", position = 6)
     public String writer;
     @ApiModelProperty(value = "postlist", position = 7)
     public List<Post> postList;
}