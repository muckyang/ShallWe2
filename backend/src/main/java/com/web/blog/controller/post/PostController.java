package com.web.blog.controller.post;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.web.blog.dao.post.PostDao;
import com.web.blog.model.PostResponse;
import com.web.blog.model.post.CreateRequest;
import com.web.blog.model.post.Post;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = PostResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = PostResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = PostResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = PostResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class PostController {
    @Autowired
    PostDao postDao;

    @PostMapping("/post/create")
    @ApiOperation(value = "게시글등록")
    public Object create(@Valid @RequestBody CreateRequest request) throws MessagingException, IOException {
        String message = "";
        int pid = request.getPid();
        String title = request.getTitle();
        int memberAmount = request.getMemberAmount();
        int price = request.getPrice();
        String description = request.getDescription();
        String writer = request.getWriter();

        Post post = new Post();
        post.setPid(pid);
        post.setTitle(title);
        post.setMemberAmount(memberAmount);
        post.setPrice(price);
        post.setDescription(description);
        post.setWriter(writer);
        System.out.println(post.getPid());
        System.out.println();
        postDao.save(post);

        System.out.println("게시물 등록!!");
        final PostResponse result = new PostResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/post/read")
    @ApiOperation(value = "게시글목록")
    public Object read() throws MessagingException, IOException {

        List<Post> plist = postDao.findAll();

        System.out.println("게시물 목록!!");
        final PostResponse result = new PostResponse();
        result.postList = plist;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/post/detail/{pid}") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "게시물상세보기") // SWAGGER UI에 보이는 이름
    // public Object login(@RequestParam(required = true) final String id,
    // @RequestParam(required = true) final String password) {
    public Object read(@PathVariable int pid) {

        Optional<Post> postOpt = postDao.findPostByPid(pid);
        ResponseEntity response = null;
        String res = "";
        if (postOpt.isPresent()) {
            String ptitle = postOpt.get().getTitle();
            int ppid = postOpt.get().getPid();
            int pprice = postOpt.get().getPrice();
            String pdescription = postOpt.get().getDescription();
            String pwriter = postOpt.get().getWriter();

            final PostResponse result = new PostResponse();
            result.pid = ppid;
            result.title = ptitle;
            result.price = pprice;
            result.description = pdescription;
            result.writer = pwriter;
            response = new ResponseEntity<>(result, HttpStatus.OK);
            System.out.println("게시물 출력!!!");
        } else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            System.out.println("게시물 출력 실패!!!");
        }

        return response;
    }

    @PostMapping("/post/update/")
    @ApiOperation(value = "게시글수정")
    public Object update(@Valid @RequestBody CreateRequest request) {
        // 프론트에서 넘겨줄때 hidden으로 id 넘겨줄 것
        // 이메일, 닉네임 중복처리
        String message = "";
        int pid= request.getPid();
        String title = request.getTitle();
        int memberAmount = request.getMemberAmount();
        int price = request.getPrice();
        String description = request.getDescription();



        Post post = postDao.getPostByPid(pid);


        post.setTitle(title);
        post.setMemberAmount(memberAmount);
        post.setPrice(price);
        post.setDescription(description);
        System.out.println(post.getPid());
        System.out.println();

        postDao.save(post);

        System.out.println("게시물 수정");
        PostResponse result = new PostResponse();
        result.title = title;
        result.memberAmount = memberAmount;
        result.price = price;
        result.description = description;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/post/delete/{pid}")
    @ApiOperation(value = "삭제하기")
    public Object delete(@Valid @PathVariable int pid) {
        Post post = postDao.getPostByPid(pid);
        postDao.delete(post);
        System.out.println("삭제하기!! ");
        PostResponse result = new PostResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}