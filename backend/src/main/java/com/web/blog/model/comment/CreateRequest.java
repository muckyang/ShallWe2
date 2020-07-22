package com.web.blog.model.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Data
@Valid
public class CreateRequest {
  
    int no;
    @ApiModelProperty(required = true)
    @NotNull
    String content;
    @ApiModelProperty(required = true)
    @NotNull
    String writer;
    @ApiModelProperty(required = true)
    @NotNull
    int articleno;
        
}
