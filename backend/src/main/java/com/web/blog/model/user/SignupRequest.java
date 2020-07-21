package com.web.blog.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Data
@Valid
public class SignupRequest {
  
    int uid;
    @ApiModelProperty(required = true)
    @NotNull
    String id;
    @ApiModelProperty(required = true)
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    String password;
    @ApiModelProperty(required = true)
    @NotNull
    String email;
    @ApiModelProperty(required = true)
    @NotNull
    String nickname;
    String name;
    String address;
    LocalDate birthday;


    
}
