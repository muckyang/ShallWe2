package com.web.blog.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.web.blog.dao.ArticleTagDao;
import com.web.blog.dao.CommentDao;
import com.web.blog.dao.LikeDao;
// import com.web.blog.dao.LikeDao;
import com.web.blog.dao.PostDao;
import com.web.blog.dao.TagDao;
import com.web.blog.dao.UserDao;
import com.web.blog.model.post.PostListResponse;
import com.web.blog.model.post.PostResponse;
import com.web.blog.model.post.PostSearchRequest;
import com.web.blog.model.tag.Tag;
import com.web.blog.model.like.Like;
import com.web.blog.model.post.PostRequest;
import com.web.blog.model.post.Post;
import com.web.blog.model.user.TokenRequest;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;

import com.web.blog.model.article_tag.ArticleTag;
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
    ArticleTagDao articleTagDao;
    @Autowired
    TagDao tagDao;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/post/create/{temp}")
    @ApiOperation(value = "게시글및임시글등록")
    public Object create(@Valid @RequestBody PostRequest request, @PathVariable int temp) throws MessagingException, IOException {
              String token =   request.getToken();
        if (temp == 0) {// 임시저장
            System.out.println(token);
            User jwtuser = jwtService.getUser(token);

            Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
            if (userOpt.isPresent()) {

                Post post = new Post();

                post.setUserId(userOpt.get().getUserId()); // token값으로 user_id 받아옴
                post.setWriter(userOpt.get().getName());// 작성자 이름? Email
                post.setTemp(temp);
                post.setCategoryId(request.getCategoryId());
                post.setTitle(request.getTitle());
                post.setAddress(request.getAddress());
                post.setMinPrice(request.getMinPrice());
                post.setDescription(request.getDescription());
                post.setUrlLink(request.getUrlLink());
                post.setImage(request.getImage());
                post.setEndTime(request.getEndTime());
            

                System.out.println(post.getArticleId());
                System.out.println();
                postDao.save(post);

                System.out.println("임시저장!!");
                final PostResponse result = new PostResponse();

                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            String message = "로그인을 확인하세요";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (temp == 1) {
            User jwtuser = jwtService.getUser(token);
            Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());

            if (userOpt.isPresent()) {

                Post post = new Post();
                // articleId 자동생성
                post.setUserId(userOpt.get().getUserId()); // token값으로 user_id 받아옴
                post.setWriter(userOpt.get().getName());// 작성자 이름 ? 이메일? 
                post.setTemp(temp);
                post.setCategoryId(request.getCategoryId());
                post.setTitle(request.getTitle());
                post.setAddress(request.getAddress());
                post.setMinPrice(request.getMinPrice());
                post.setDescription(request.getDescription());
                post.setUrlLink(request.getUrlLink());
                post.setImage(request.getImage());
                post.setEndTime(request.getEndTime());
                // post.setBillImage(request.getBillImage()); // 후기작성때 넣을예정

                System.out.println(post.getArticleId());
                postDao.save(post);

                System.out.println("게시물 등록!!");
                PostResponse result = new PostResponse();

                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            String message = "로그인을 확인하세요";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        } else if (temp == 2) { // 자유게시물
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {// 그냥 나가
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post/read/{temp}/{categoryId}") // temp 값 int 로 변경예정
    @ApiOperation(value = "게시글 및 임시글 목록")
    public Object read(@RequestBody TokenRequest request, @PathVariable int temp,@PathVariable int categoryId) throws MessagingException, IOException {
        if (temp == 0) {
            System.out.println("임시글 목록 출력!!");
            String token = request.getToken();
            User jwtuser = jwtService.getUser(token);
            Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());

            String writer = userOpt.get().getEmail();
            List<Post> plist = postDao.findPostByTempAndWriter(temp, writer);

            PostListResponse result = new PostListResponse();
            result.postList = new LinkedList<>();
            for (int i = 0; i < plist.size(); i++) { // 각 게시물 마다 좋아요 수 가져오기
                Post p = plist.get(i);
                // int articleno = p.getPid();
                result.postList.add(new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                        p.getAddress(), p.getMinPrice(), p.getDescription(), p.getWriter(), p.getUrlLink(),
                        p.getImage(), p.getBillImage(), p.getTemp(), p.getEndTime()));

            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (temp == 1) {
            List<Post> plist;
            System.out.println("게시물 목록 출력!!");
            if(categoryId == 0 )// 전체 게시물 출력
                plist = postDao.findPostByTemp(temp);
            else 
                plist = postDao.findPostByTempAndCategoryId(temp,categoryId);
                // System.out.println(plist.get(0).getTitle());
            PostListResponse result = new PostListResponse();
            result.postList = new LinkedList<>();
            for (int i = 0; i < plist.size(); i++) { // 각 게시물 마다 좋아요 수 가져오기
                Post p = plist.get(i);
                int articleId = p.getArticleId();
                result.postList.add(new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                        p.getAddress(), p.getMinPrice(), p.getDescription(), p.getWriter(), p.getUrlLink(),
                        p.getImage(), p.getBillImage(), p.getTemp(), p.getEndTime()));

                // Optional<Like> llist = likeDao.findLikeByArticleno(articleno);
                List<Like> llist = likeDao.findLikeByArticleId(articleId);
                List<Comment> clist = commentDao.findCommentByArticleId(articleId);
                int likenum = llist.size();
                int commentnum = clist.size();
                System.out.println("list return success");
                System.out.println(likenum);

                result.postList.get(i).likenum = likenum;
                result.postList.get(i).commentnum = commentnum;

                // / 토큰받아서 id 추출 id 와 게시물 번호로 쿼리 , 좋아요 여부 확인
                // User jwtuser = jwtService.getUser(token);
                // Optional<User> userOpt = userDao.findUserByIdAndPassword(jwtuser.getId(),
                // jwtuser.getPassword());

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
        } else if (temp == 2) { // 자유게시판
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post/search/{temp}/{categoryId}") 
    @ApiOperation(value = "구매게시판 검색 목록")
    public Object search(@RequestBody PostSearchRequest request ,int temp, int categoryId) throws MessagingException, IOException {
        String subject = request.getSubject();
        String word = request.getWord();

        if (subject.equals("title")) {
            word = "%" + word + "%";
            List<Post> tlist = postDao.findPostByTempAndTitleLike(temp , word);

            PostListResponse result = new PostListResponse();
            result.postList = new LinkedList<>();
            for (int i = 0; i < tlist.size(); i++) { // 각 게시물 마다 좋아요 수 가져오기
                Post p = tlist.get(i);
                // int articleno = p.getPid();
                result.postList.add(new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                        p.getAddress(), p.getMinPrice(), p.getDescription(), p.getWriter(), p.getUrlLink(),
                        p.getImage(), p.getBillImage(), p.getTemp(), p.getEndTime()));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (subject.equals("writer")) {
            word = "%" + word + "%";
            List<Post> tlist = postDao.findPostByTempAndWriterLike(temp,word);

            PostListResponse result = new PostListResponse();
            result.postList = new LinkedList<>();
            for (int i = 0; i < tlist.size(); i++) { // 각 게시물 마다 좋아요 수 가져오기
                Post p = tlist.get(i);
                // int articleno = p.getPid();
                result.postList.add(new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                        p.getAddress(), p.getMinPrice(), p.getDescription(), p.getWriter(), p.getUrlLink(),
                        p.getImage(), p.getBillImage(), p.getTemp(), p.getEndTime()));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (subject.equals("tag")) {
            Optional<Tag> tag = tagDao.findTagByName(word);// 해당 태그의 tagId가져옴
            int tId = tag.get().getTagId();

            List<ArticleTag> atlist = articleTagDao.findArticleTagByTagId(tId);// 해당 tagId의 articleId들 가져옴
            PostListResponse result = new PostListResponse();
            result.postList = new LinkedList<>();
            for (int i = 0; i < atlist.size(); i++) {
                int aId = atlist.get(i).getArticleId();
                Optional<Post> article = postDao.findPostByArticleIdAndTemp(aId,temp);
                Post p = article.get();
                result.postList.add(new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                        p.getAddress(), p.getMinPrice(), p.getDescription(), p.getWriter(), p.getUrlLink(),
                        p.getImage(), p.getBillImage(), p.getTemp(), p.getEndTime()));
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("temp 해당 없음", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/post/detail/{articleId}") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "게시물상세보기") // SWAGGER UI에 보이는 이름
    public Object detail(@PathVariable int articleId, @RequestBody TokenRequest request) {
        // 토큰 받아오면 그 토큰으로 유효성 검사 후 uid 받아와서 좋아요 한지 여부 확인
        Optional<Post> postOpt = postDao.findPostByArticleId(articleId);
        Post p = postOpt.get();
        if (postOpt.isPresent()) {
            String token = request.getToken();
            User jwtuser = jwtService.getUser(token);
            Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
            PostResponse result = new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                    p.getAddress(), p.getMinPrice(), p.getDescription(), p.getWriter(), p.getUrlLink(), p.getImage(),
                    p.getBillImage(), p.getTemp(), p.getEndTime());
            if (userOpt.isPresent()) {// 로그인 상태일때

                Optional<Like> isILiked = likeDao.findLikeByUserIdAndArticleId(userOpt.get().getUserId(), articleId);
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
    @ApiOperation(value = "게시글 및 임시글 수정")
    public Object update(@Valid @RequestBody PostRequest request, @PathVariable int temp) {
        if (temp == 0) {
            Post post = postDao.getPostByArticleId(request.getArticleId());
            post.setCategoryId(request.getCategoryId());
            post.setTitle(request.getTitle());
            post.setAddress(request.getAddress());
            post.setDescription(request.getDescription());
            post.setMinPrice(request.getMinPrice());
            post.setUrlLink(request.getUrlLink());
            post.setImage(request.getImage());
            post.setTemp(temp);
            post.setEndTime(request.getEndTime());
            // post.setBillImage(request.getBillImage());

            System.out.println(post.getArticleId());
            System.out.println();

            postDao.save(post);

            System.out.println("게시물 수정");
            PostResponse result = new PostResponse();
            result.title = post.getTitle();
            result.minPrice = post.getMinPrice();
            // result.title = post.getAddress();
            // result.title = post.getTitle();
            // result.title = post.getTitle();
            // result.title = post.getTitle();
            result.temp = temp;
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (temp == 1) {

            Post post = postDao.getPostByArticleId(request.getArticleId());

            post.setCategoryId(request.getCategoryId());
            post.setTitle(request.getTitle());
            post.setAddress(request.getAddress());
            post.setMinPrice(request.getMinPrice());
            post.setDescription(request.getDescription());
            post.setUrlLink(request.getUrlLink());
            post.setImage(request.getImage());
            post.setTemp(temp);
            post.setEndTime(request.getEndTime());

            System.out.println(post.getArticleId());
            System.out.println();

            postDao.save(post);

            System.out.println("게시물 수정");
            PostResponse result = new PostResponse();
            result.title = post.getTitle();
            result.minPrice = post.getMinPrice();
            // result.title = post.getAddress();
            // result.title = post.getTitle();
            // result.title = post.getTitle();
            // result.title = post.getTitle();

            result.temp = temp;
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else if (temp == 2) { // 자유게시판
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/post/delete/{articleId}")
    @ApiOperation(value = "삭제하기")
    public Object delete(@Valid @PathVariable int articleId) {
        Post post = postDao.getPostByArticleId(articleId);

        //댓글 삭제 
        List<Comment> commentList = commentDao.findCommentByArticleId(articleId);
        int csize = commentList.size();
        for (int i = 0; i < csize; i++) {
            Comment c = commentList.get(i);
            commentDao.delete(c);
        }
        ///좋아요 삭제 
        List<Like> likeList = likeDao.findLikeByArticleId(articleId);
        int lsize = likeList.size();
        for (int i = 0; i < lsize; i++) {
            Like l = likeList.get(i);
            likeDao.delete(l);
        }
        // 태그 삭제
        List<ArticleTag> atList = articleTagDao.findArticleTagByArticleId(articleId);
        int atsize = atList.size();
        for (int i = 0; i < atsize; i++) {
            ArticleTag l = atList.get(i);
            articleTagDao.delete(l);
        }

        postDao.delete(post);
        System.out.println("삭제하기!! ");
        PostResponse result = new PostResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}