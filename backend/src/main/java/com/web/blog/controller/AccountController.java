package com.web.blog.controller;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import com.web.blog.dao.AuthDao;
import com.web.blog.dao.UserDao;
import com.web.blog.model.user.UserResponse;
import com.web.blog.model.auth.Auth;
import com.web.blog.model.user.AuthRequest;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.TokenRequest;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.context.Context;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = UserResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = UserResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = UserResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = UserResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class AccountController {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthDao authDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine TemplateEngine;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/account/login/{email}/{password}") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "로그인") // SWAGGER UI에 보이는 이름
    // public Object login(@RequestParam(required = true) final String id,
    // @RequestParam(required = true) final String password) {
    public Object login(@PathVariable String email, @PathVariable String password) {

        Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
        ResponseEntity<Object> response = null;

        if (userOpt.isPresent()) {

            System.out.println("로그인성공");
            System.out.println(email);
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            // 토큰 생성
            String token = jwtService.createLoginToken(user);
            // 복호화
            User jwtuser = jwtService.getUser(token);
            System.out.println("생성한 토큰 >>>>>>" + token);
            System.out.println("토큰 복호화 >>>>>>" + jwtuser);
            response = new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            System.out.println("로그인 실패");
        }

        return response;
    }

    @PostMapping("/account/sendmail")
    @ApiOperation(value = "인증메일 발송")
    public Object sendmail(@Valid @RequestBody AuthRequest request) throws MessagingException, IOException {

        int authNumber = (int) (Math.random() * 900000) +100000 ; // 난수 생성
        Optional<Auth> OptionalAuth = authDao.getAuthByEmail(request.getEmail());
        if (OptionalAuth.isPresent()) {
            Auth auth = OptionalAuth.get();
            auth.setAuthNumber(authNumber);
            authDao.save(auth);
        } else {
            Auth auth = new Auth();
            auth.setAuthNumber(authNumber);
            auth.setEmail(request.getEmail());
            authDao.save(auth);
        }

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
            context.setVariable("auth_number", authNumber);

            // 메일 내용 설정 : 템플릿 프로세스
            String html = TemplateEngine.process("mail-template", context);
            helper.setText(html, true);
            javaMailSender.send(mailmessage);

        } catch (Exception e) {
            System.out.println("메일전송 실패!");
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }

        System.out.println("메일전송 성공!");
        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "회원가입")
    public Object signup(@Valid @RequestBody SignupRequest request) throws MessagingException, IOException {
        // 이메일, 닉네임 중복처리

        User user = new User();
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setNickname(request.getNickname());
        user.setAddress(request.getAddress());
        user.setUserPoint(1000);// 최초 가입시 1000점으로
        user.setBirthday(request.getBirthday());

        userDao.save(user);

        System.out.println("가입하기 성공!");
        final UserResponse result = new UserResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/account/read") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "프로필 조회")
    public Object info(@RequestBody TokenRequest request) {
    // @RequestHeader(value = "Authorization") String token
    
        String token = request.getToken(); 
        System.out.println(token);

        // Optional<User> userOpt = userDao.findById(id);
        ResponseEntity<Object> response = null;
        System.out.println("프로필 조회 ! ");



        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());

        if (userOpt.isPresent()) {
            UserResponse result = new UserResponse();
            result.email = userOpt.get().getEmail();
            result.password = userOpt.get().getPassword();
            result.name = userOpt.get().getName();
            result.address = userOpt.get().getAddress();
            result.nickname = userOpt.get().getNickname();
            result.birthday = userOpt.get().getBirthday();
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }



    @PostMapping("/account/update")
    @ApiOperation(value = "수정하기")
    public Object update(@Valid @RequestBody SignupRequest request) {
        String token = request.getToken();
       
        // 복호화
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        String message = "";

        System.out.println("수정하기 - token 검색");
        if (userOpt.isPresent()) {
            // 이메일, 닉네임 중복처리
            System.out.println("token으로 찾기 완료 ");
            User user = userDao.getUserByEmail(request.getEmail());
            System.out.println("id로 검색");

            // Optional<User> isNickname = userDao.getUserByNickname(request.getNickname());

            user.setPassword(request.getPassword());
            user.setName(request.getName());
            user.setNickname(request.getNickname());// 삭제보류
            user.setAddress(request.getAddress());
            user.setBirthday(request.getBirthday());

            // if (isNickname.isPresent() &&
            // !isNickname.get().getNickname().equals(user.getNickname())) { // 닉네임 중복
            // message = "닉네임 중복 입니다.";
            // System.out.println("중복입니다.");
            // return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            // }

            userDao.save(user); // 수정내용 저장
            System.out.println("수정하기 완료!! ");
            UserResponse result = new UserResponse();
            result.status = true;
            result.data = "success";

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            message = "로그인 된 계정이 없습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/account/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete( @RequestBody TokenRequest request) {
        String token = request.getToken();
        User jwtuser = jwtService.getUser(token);

        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        String message = "";
        if (userOpt.isPresent()) {
            User user = userDao.getUserByEmail(jwtuser.getEmail());

            // FK 연동된것 삭제 /// 완료테이블 있어야 할 것 같음
            // 1. 거래참가자 테이블에 없어야 됨 (존재한다면 alert 표시)
            // 2. like_tabel, comment 테이블에서 해당 id 삭제
            // 3. article 테이블에서 해당아이디 게시물 삭제

            userDao.delete(user);

            System.out.println("삭제하기!!");
            UserResponse result = new UserResponse();
            result.status = true;
            result.data = "success";

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            message = "로그인 된 아이디가 없습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

}