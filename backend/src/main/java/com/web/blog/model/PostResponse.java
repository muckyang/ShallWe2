package com.web.blog.model;


import org.springframework.boot.context.properties.ConstructorBinding;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// @Data
// @ConstructorBinding
public class PostResponse {
     public PostResponse(){

     }
     public PostResponse(int pid,String title, int memberAmount,int price,String description,String writer){
          super();
          this.pid = pid;
          this.title = title;
          this.memberAmount = memberAmount;
          this.description = description;
          this.price = price;
          this.writer = writer;
 
     }
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
     @ApiModelProperty(value = "likenum", position = 7)
     public int likenum;
     @ApiModelProperty(value = "isLike", position = 8)
     public boolean isLike;
    
   
}