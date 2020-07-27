package com.web.blog.model.article_tag;

import io.swagger.annotations.ApiModelProperty;

public class ArticleTagResponse {
    @ApiModelProperty(value = "articleId", position = 1)
    public int articleId;
    @ApiModelProperty(value = "tagId", position = 2)
    public int tagId;
}