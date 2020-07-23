package com.web.blog.controller;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.web.blog.dao.CommentDao;
// import com.web.blog.dao.LikeDao;
import com.web.blog.dao.PostDao;
import com.web.blog.dao.UserDao;
import com.web.blog.model.PostListResponse;
import com.web.blog.model.PostResponse;
import com.web.blog.model.like.Like;
import com.web.blog.model.post.CreateRequest;
import com.web.blog.model.post.Post;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;

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
import com.web.blog.model.comment.Comment;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = PostResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = PostResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = PostResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = PostResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class PostController {
    @Autowired
    PostDao postDao;

    @Autowired
    UserDao userDao;

    // @Autowired
    // LikeDao likeDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/post/create/{token}")
    @ApiOperation(value = "게시글등록")
    // public Object create(@Valid @RequestBody CreateRequest request ,
    // HttpServletRequest req) throws MessagingException, IOException {
    public Object create(@Valid @RequestBody CreateRequest request, @PathVariable String token)
            throws MessagingException, IOException {

        String title = request.getTitle();
        int memberAmount = request.getMemberAmount();
        int price = request.getPrice();
        String description = request.getDescription();

        System.out.println(token);
        User jwtuser = jwtService.getUser(token);

        Optional<User> userOpt = userDao.findUserByIdAndPassword(jwtuser.getId(), jwtuser.getPassword());
        if (userOpt.isPresent()) {

            Post post = new Post();

            post.setTitle(title);
            post.setMemberAmount(memberAmount);
            post.setPrice(price);
            post.setDescription(description);
            post.setWriter(userOpt.get().getId()); // token값으로 id 받아옴

            System.out.println(post.getPid());
            System.out.println();
            postDao.save(post);

            System.out.println("게시물 등록!!");
            final PostResponse result = new PostResponse();

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        String message = "로그인을 확인하세요";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    // @GetMapping("/post/read/{token}")
    @GetMapping("/post/read/")
    @ApiOperation(value = "게시글목록")
    public Object read() throws MessagingException, IOException {
    // public Object read(@PathVariable String token) throws MessagingException, IOException {
        System.out.println("게시물 목록 출력!!");
        List<Post> plist = postDao.findAll();
        PostListResponse result = new PostListResponse();
        result.postList = new LinkedList<>();
        for (int i = 0; i < plist.size(); i++) { // 각 게시물 마다 좋아요 수 가져오기
            Post p = plist.get(i);
            int articleno = p.getPid();
            result.postList.add(new PostResponse(p.getPid(), p.getTitle(), p.getMemberAmount(), p.getPrice(),
                    p.getDescription(), p.getWriter()));

            // Optional<Like> llist = likeDao.findLikeByArticleno(articleno);
            List<Like> llist = likeDao.findLikeByArticleno(articleno);
            System.out.println("list return success");
            int likenum = llist.size();
            System.out.println(likenum);
            result.postList.get(i).likenum = likenum;

            /// 토큰받아서 id 추출 id 와 게시물 번호로 쿼리 , 좋아요 여부 확인
            // User jwtuser = jwtService.getUser(token);
            // Optional<User> userOpt = userDao.findUserByIdAndPassword(jwtuser.getId(), jwtuser.getPassword());
            // if (userOpt.isPresent()) {// 로그인 상태일때 
            //     Optional<Like> isILike = likeDao.findLikeByUseridArticleno(userOpt.get().getId(), articleno);
            //     if (isILike.isPresent()) {// 좋아요 한 경우 
            //         result.postList.get(i).isLike = true;
            //     } else {//좋아요 하지 않은경우
            //         result.postList.get(i).isLike = false;
            //     }
            // } else {//비 로그인 경우 좋아요 안한 상태!
            //     result.postList.get(i).isLike = false;
            // }

        }


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/post/detail/{pid}/{token}") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "게시물상세보기") // SWAGGER UI에 보이는 이름
    // public Object login(@RequestParam(required = true) final String id,
    // @RequestParam(required = true) final String password) {
    public Object read(@PathVariable int pid, @PathVariable String token) {
        // 토큰 받아오면 그 토큰으로 유효성 검사 후 uid 받아와서 좋아요 한지 여부 확인
        Optional<Post> postOpt = postDao.findPostByPid(pid);
        ResponseEntity<Object> response = null;

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

        // 이메일, 닉네임 중복처리
        int pid = request.getPid();
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
        List<Comment> commentList=commentDao.findCommentByArticleno(pid);
        int size =commentList.size();
        for(int i = 0;i<size;i++){
            Comment c = commentList.get(i);
            commentDao.delete(c);
        }
        postDao.delete(post);
        System.out.println("삭제하기!! ");
        PostResponse result = new PostResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // @GetMapping("/post/like/{pid}")
    // @ApiOperation(value = "좋아요")
    // public Object delete(@Valid @PathVariable int pid) {
    // Post post = postDao.getPostByPid(pid);
    // postDao.delete(post);
    // System.out.println("삭제하기!! ");
    // PostResponse result = new PostResponse();

    // return new ResponseEntity<>(result, HttpStatus.OK);
    // }

}