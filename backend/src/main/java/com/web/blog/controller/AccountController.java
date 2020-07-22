package com.web.blog.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import com.web.blog.dao.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;

import org.springframework.web.bind.annotation.RestController;

import org.thymeleaf.spring5.SpringTemplateEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.thymeleaf.context.Context;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class AccountController {

    @Autowired
    UserDao userDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine TemplateEngine;

    @Autowired
    private JwtService jwtService; 

    @GetMapping("/account/login/{id}/{password}") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "로그인") // SWAGGER UI에 보이는 이름
    // public Object login(@RequestParam(required = true) final String id,
    // @RequestParam(required = true) final String password) {
    public Object login(@PathVariable  String id, @PathVariable  String password) {

         Optional<User> userOpt = userDao.findUserByIdAndPassword(id, password);
        ResponseEntity<Object> response = null;

        if (userOpt.isPresent()) {
            //  String uname = userOpt.get().getName();
            //  String uaddress = userOpt.get().getAddress();
            //  String uemail = userOpt.get().getEmail();
            //  String unickname = userOpt.get().getNickname();
            //  String uid = userOpt.get().getId();
            //  String upassword = userOpt.get().getPassword();
            //  LocalDate ubirthday = userOpt.get().getBirthday();

             
            //  final BasicResponse result = new BasicResponse();
            // result.email = uemail;
            // result.password = upassword;
            // result.name = uname;
            // result.id = uid;
            // result.address = uaddress;
            // result.nickname = unickname;
            // result.birthday = ubirthday;
            
            System.out.println("로그인성공");
            User user = new User();
            user.setId(id);
            System.out.println(id);
            user.setPassword(password);
            user.setEmail(userOpt.get().getEmail());
            String token = jwtService.createLoginToken(user);
		    // LOGGER.debug("created jwt ::::::: {}" , token);
		    // LOGGER.debug("jwt decoding... ");
		    User jwtuser = jwtService.getUser(token);
            // LOGGER.debug("decoded jwt ::::::: {}", jwtuser);
            System.out.println("token>>>>>>"+token);
            System.out.println("jwt>>>>>>"+jwtuser);

            response = new ResponseEntity<>(token, HttpStatus.OK);

        } else {

            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            System.out.println("로그인 실패");
        }

        return response;
    }


    @PostMapping("/account/signup")
    @ApiOperation(value = "회원가입")
    public Object signup(@Valid @RequestBody SignupRequest request) throws MessagingException, IOException{
        // 이메일, 닉네임 중복처리
        String message = "";
        User isEmail = userDao.getUserByEmail(request.getEmail());
      User isNickname = userDao.getUserByNickname(request.getNickname());
        if (isEmail != null) { // 메일 중복
            message = "이메일 중복 입니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        if (isNickname != null) { // 닉네임 중복
            message = "닉네임 중복 입니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
     String id = request.getId();
         String password = request.getPassword();
         String email = request.getEmail();
         String name = request.getName();
         String nickname = request.getNickname();
         String address = request.getAddress();
         LocalDate birthday = request.getBirthday();

         User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setNickname(nickname);
        user.setAddress(address);
        user.setBirthday(birthday);

        userDao.save(user);

        try {
            System.out.println("메일 전송 완료!!");
            MimeMessage mailmessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailmessage, true);
            // 메일 제목 설정
            helper.setSubject("스프링 부트 메일 전송");
            // 수신자 설정
            helper.setTo(request.getEmail());
            // 템플릿에 전달할 데이터 설정
      
            Context context = new Context();
            context.setVariable("test_key", "test_value");
        
            // 메일 내용 설정 : 템플릿 프로세스
            String html = TemplateEngine.process("mail-template", context);
            helper.setText(html, true);
            

            javaMailSender.send(mailmessage);
            System.out.println(request.getEmail());
        } catch ( Exception e) {
            System.out.println("메일전송 실패!");
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }

        System.out.println("가입하기 들어옴!! ");
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/update")
    @ApiOperation(value = "수정하기")
    public Object update(@Valid @RequestBody  SignupRequest request) {
        // 프론트에서 넘겨줄때 hidden으로 id 넘겨줄 것
        // 이메일, 닉네임 중복처리
        String message = "";

         String id = request.getId();
         String password = request.getPassword();
         String name = request.getName();
         String nickname = request.getNickname();
         String address = request.getAddress();
         LocalDate birthday = request.getBirthday();

         User user = userDao.getOne(id);

         String email = request.getEmail();// 바꿀거
         String dbemail = user.getEmail();// 원래이메일
         User isEmail = userDao.getUserByEmail(email);// 바꿀게 db에 있을때
         User isNickname = userDao.getUserByNickname(request.getNickname());

        user.setPassword(password);
        user.setName(name);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setAddress(address);
        user.setBirthday(birthday);

        if (isEmail != null && !isEmail.getEmail().equals(dbemail)) { // 메일 중복
            message = "이메일 중복 입니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        if (isNickname != null && !isNickname.getNickname().equals(user.getNickname())) { // 닉네임 중복
            message = "닉네임 중복 입니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        userDao.save(user);
        System.out.println("수정하기 들어옴!! ");
         BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/account/delete/{id}")
    @ApiOperation(value = "삭제하기")
    public Object delete(@Valid @PathVariable String id) {
         User user = userDao.getOne(id);

        userDao.delete(user);
        System.out.println("삭제하기!! ");
         BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}