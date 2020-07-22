package com.web.blog.controller;
import java.time.LocalDate;
import java.util.Optional;


import com.web.blog.dao.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.User;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;


@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class UserController {
    @Autowired
    UserDao userDao;
    @GetMapping("/info/{id}") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "프로필 수정")
    public Object info(@PathVariable  String id){

        Optional<User> userOpt = userDao.findById(id);
        ResponseEntity<Object> response = null;
        
        if (userOpt.isPresent()) {
             String uname = userOpt.get().getName();
             String uaddress = userOpt.get().getAddress();
             String uemail = userOpt.get().getEmail();
             String unickname = userOpt.get().getNickname();
             String uid = userOpt.get().getId();
             String upassword = userOpt.get().getPassword();
             LocalDate ubirthday = userOpt.get().getBirthday();

             BasicResponse result = new BasicResponse();
            result.email = uemail;
            result.password = upassword;
            result.name = uname;
            result.id = uid;
            result.address = uaddress;
            result.nickname = unickname;
            result.birthday = ubirthday;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        
        } else {

            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }
}