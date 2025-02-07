package com.web.blog.model.user;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;

public class UserResponse {
    // user
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    @ApiModelProperty(value = "object", position = 3)
    public Object object;
    @ApiModelProperty(value = "password", position = 5)
    public String password;
    @ApiModelProperty(value = "email", position = 6)
    public String email;
    @ApiModelProperty(value = "name", position = 7)
    public String name;
    @ApiModelProperty(value = "nickname", position = 8)
    public String nickname;
    @ApiModelProperty(value = "address", position = 9)
    public String address;
    @ApiModelProperty(value = "birthday", position = 10)
    public LocalDate birthday;

   
    
}