package com.web.blog.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.web.blog.dao.CommentDao;
import com.web.blog.dao.LikeDao;
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

    @Autowired
    LikeDao likeDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/post/create/{token}/{temp}")
    @ApiOperation(value = "게시글및임시글등록")
    // public Object create(@Valid @RequestBody CreateRequest request ,
    // HttpServletRequest req) throws MessagingException, IOException {
    public Object create(@Valid @RequestBody CreateRequest request, @PathVariable String token,
            @PathVariable boolean temp) throws MessagingException, IOException {
        if (temp) {
            int tnum = 1;
            String title = request.getTitle();
            int memberAmount = request.getMemberAmount();
            int price = request.getPrice();
            String description = request.getDescription();

            User jwtuser = jwtService.getUser(token);
            Optional<User> userOpt = userDao.findUserByIdAndPassword(jwtuser.getId(), jwtuser.getPassword());

            if (userOpt.isPresent()) {

                Post post = new Post();

                post.setTitle(title);
                post.setMemberAmount(memberAmount);
                post.setPrice(price);
                post.setDescription(description);
                post.setWriter(userOpt.get().getId()); // token값으로 id 받아옴
                post.setTemp(tnum);
                System.out.println(post.getPid());
                System.out.println();
                postDao.save(post);

                System.out.println("게시물 등록!!");
                PostResponse result = new PostResponse();

                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            String message = "로그인을 확인하세요";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        } else {
            int tnum = 0;
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
                post.setTemp(tnum);
                System.out.println(post.getPid());
                System.out.println();
                postDao.save(post);

                System.out.println("임시물 등록!!");
                final PostResponse result = new PostResponse();

                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            String message = "로그인을 확인하세요";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    // @GetMapping("/post/read/{token}/{temp}")
    // @ApiOperation(value = "게시글및임시글목록")
    // public Object read(@PathVariable boolean temp) throws MessagingException,
    // IOException {
    @GetMapping("/post/read/{temp}/{token}")
    @ApiOperation(value = "게시글및임시글목록")
    public Object read(@PathVariable boolean temp, @PathVariable String token) throws MessagingException, IOException {

        if (temp) {
            System.out.println("게시물 목록 출력!!");

            int tnum = 1;
            List<Post> plist = postDao.findPostByTemp(tnum);
            System.out.println(plist.get(0).getTitle());
            PostListResponse result = new PostListResponse();
            result.postList = new LinkedList<>();
            for (int i = 0; i < plist.size(); i++) { // 각 게시물 마다 좋아요 수 가져오기
                Post p = plist.get(i);
                int articleno = p.getPid();
                result.postList.add(new PostResponse(p.getPid(), p.getTitle(), p.getMemberAmount(), p.getPrice(),
                        p.getDescription(), p.getWriter(), p.getTemp()));

                // Optional<Like> llist = likeDao.findLikeByArticleno(articleno);
                List<Like> llist = likeDao.findLikeByArticleno(articleno);
                System.out.println("list return success");
                int likenum = llist.size();
                System.out.println(likenum);
                result.postList.get(i).likenum = likenum;

                // / 토큰받아서 id 추출 id 와 게시물 번호로 쿼리 , 좋아요 여부 확인
                User jwtuser = jwtService.getUser(token);
                Optional<User> userOpt = userDao.findUserByIdAndPassword(jwtuser.getId(), jwtuser.getPassword());

                // if (userOpt.isPresent()) {// 로그인 상태일때
                // Optional<Like> isILiked =
                // likeDao.findLikeByUidAndArticleno(userOpt.get().getUid(), articleno);
                // if (isILiked.isPresent()) {// 좋아요 한 경우
                // result.postList.get(i).isLiked = true;
                // } else {// 좋아요 하지 않은경우
                // result.postList.get(i).isLiked = false;
                // }
                // } else {// 비 로그인 경우 / 좋아요 안한 상태!
                // result.postList.get(i).isLiked = false;
                // }

            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            System.out.println("임시글 목록 출력!!");
            int tnum = 0;


            
            User jwtuser = jwtService.getUser(token);
            Optional<User> userOpt = userDao.findUserByIdAndPassword(jwtuser.getId(), jwtuser.getPassword());

            String writer = userOpt.get().getId();
            List<Post> plist = postDao.findPostByTempAndWriter(tnum , writer);

            PostListResponse result = new PostListResponse();
            result.postList = new LinkedList<>();
            for (int i = 0; i < plist.size(); i++) { // 각 게시물 마다 좋아요 수 가져오기
                Post p = plist.get(i);
                // int articleno = p.getPid();
                result.postList.add(new PostResponse(p.getPid(), p.getTitle(), p.getMemberAmount(), p.getPrice(),
                        p.getDescription(), p.getWriter(), p.getTemp()));

            //     // Optional<Like> llist = likeDao.findLikeByArticleno(articleno);
            //     // List<Like> llist = postDao.findLikeByArticleno(articleno);
            //     // System.out.println("list return success");
            //     // int likenum = llist.size();
            //     // System.out.println(likenum);
            //     // result.postList.get(i).likenum = likenum;


            //     // if (userOpt.isPresent()) {// 로그인 상태일때
            //     //     Optional<Like> isILike = likeDao.findLikeByUseridArticleno(userOpt.get().getId(), articleno);
            //     //     if (isILike.isPresent()) {// 좋아요 한 경우
            //     //         result.postList.get(i).isLike = true;
            //     //     } else {// 좋아요 하지 않은경우
            //     //         result.postList.get(i).isLike = false;
            //     //     }
            //     // } else {// 비 로그인 경우 좋아요 안한 상태!
            //     //     result.postList.get(i).isLike = false;
            //     // }

            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/post/detail/{pid}/{token}") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "게시물상세보기") // SWAGGER UI에 보이는 이름
    public Object detail(@PathVariable int pid, @PathVariable String token) {
        // 토큰 받아오면 그 토큰으로 유효성 검사 후 uid 받아와서 좋아요 한지 여부 확인
        Optional<Post> postOpt = postDao.findPostByPid(pid);
        Post p = postOpt.get();
        if (postOpt.isPresent()) {

            User jwtuser = jwtService.getUser(token);
            Optional<User> userOpt = userDao.findUserByIdAndPassword(jwtuser.getId(), jwtuser.getPassword());
            PostResponse result = new PostResponse(p.getPid(), p.getTitle(), p.getMemberAmount(), p.getPrice(),
                    p.getDescription(), p.getWriter(), p.getTemp());
            if (userOpt.isPresent()) {// 로그인 상태일때

                Optional<Like> isILiked = likeDao.findLikeByUidAndArticleno(userOpt.get().getUid(), pid);
                if (isILiked.isPresent()) // 좋아요 한 경우
                    result.isLiked = true;
                else // 좋아요 하지 않은경우
                    result.isLiked = false;

                System.out.println("게시물 출력!!!");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                System.out.println("비로그인 / 로그인 여부 확인 !!!");
                result.isLiked = false;
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post/update/{temp}")
    @ApiOperation(value = "게시글및임시글수정")
    public Object update(@Valid @RequestBody CreateRequest request, @PathVariable boolean temp) {
        if (temp) {
            int tnum = 1;
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
            post.setTemp(tnum);
            System.out.println(post.getPid());
            System.out.println();

            postDao.save(post);

            System.out.println("게시물 수정");
            PostResponse result = new PostResponse();
            result.title = title;
            result.memberAmount = memberAmount;
            result.price = price;
            result.description = description;
            result.temp = tnum;
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {

            int tnum = 0;
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
            post.setTemp(tnum);
            System.out.println(post.getPid());
            System.out.println();

            postDao.save(post);

            System.out.println("임시물 수정");
            PostResponse result = new PostResponse();
            result.title = title;
            result.memberAmount = memberAmount;
            result.price = price;
            result.description = description;
            result.temp = tnum;

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/post/delete/{pid}")
    @ApiOperation(value = "삭제하기")
    public Object delete(@Valid @PathVariable int pid) {
        Post post = postDao.getPostByPid(pid);
        List<Comment> commentList = commentDao.findCommentByArticleno(pid);
        int size = commentList.size();
        for (int i = 0; i < size; i++) {
            Comment c = commentList.get(i);
            commentDao.delete(c);
        }
        postDao.delete(post);
        System.out.println("삭제하기!! ");
        PostResponse result = new PostResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}