package com.web.blog.model.post;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Data
@Valid
public class CreateRequest {
  
    int pid;
    @ApiModelProperty(required = true)
    @NotNull
    String title;
    @ApiModelProperty(required = true)
    @NotNull
    int memberAmount;
    @ApiModelProperty(required = true)
    @NotNull
    int price;
    @ApiModelProperty(required = true)
    @NotNull
    String description;
    String writer;
    boolean temp;


        
}
