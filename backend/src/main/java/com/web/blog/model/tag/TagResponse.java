package com.web.blog.model.tag;

import io.swagger.annotations.ApiModelProperty;

public class TagResponse {
    @ApiModelProperty(value = "tagId", position = 1)
    public int tagId;
    @ApiModelProperty(value = "name", position = 2)
    public int name;
}