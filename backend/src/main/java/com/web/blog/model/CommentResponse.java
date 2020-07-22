package com.web.blog.model;

import java.util.List;

import com.web.blog.model.comment.Comment;

import io.swagger.annotations.ApiModelProperty;

public class CommentResponse {
    @ApiModelProperty(value = "content", position = 1)
    public String content;
    @ApiModelProperty(value = "writer", position = 2)
    public String writer;
    @ApiModelProperty(value = "articleno", position = 3)
    public int articleno;
    @ApiModelProperty(value = "commentlist", position = 4)
     public List<Comment> commentList;
   
}