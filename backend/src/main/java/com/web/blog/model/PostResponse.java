package com.web.blog.model;



import io.swagger.annotations.ApiModelProperty;
// @Data
// @ConstructorBinding
public class PostResponse {
     public PostResponse(){
          
     }
     public PostResponse(int pid,String title, int memberAmount,int price,String description,String writer, boolean temp){
          super();
          this.pid = pid;
          this.title = title;
          this.memberAmount = memberAmount;
          this.description = description;
          this.price = price;
          this.writer = writer;
          this.temp = temp;
 
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
     @ApiModelProperty(value = "isLiked", position = 8)
     public boolean isLiked;
     @ApiModelProperty(value = "temp", position = 9)
     public boolean temp;
}