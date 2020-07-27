package com.web.blog.model.participant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Data
@Valid
public class ParticipantRequest {
  
    
    @ApiModelProperty(required = true)
    @NotNull
    int userId;
    
    @ApiModelProperty(required = true)
    @NotNull
    int articleId;
    
    @ApiModelProperty(required = true)
    @NotNull
    String title;
    
    @ApiModelProperty(required = true)
    @NotNull
    int price;
    
    @ApiModelProperty(required = true)
    String description;
}