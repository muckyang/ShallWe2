package com.web.blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.web.blog.dao.CommentDao;
import com.web.blog.model.CommentResponse;
import com.web.blog.model.comment.CommentCreateRequest;
import com.web.blog.model.comment.Comment;

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

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommentResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = CommentResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = CommentResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = CommentResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class CommentController {
    @Autowired
    CommentDao commentDao;

    @PostMapping("/comment/create")
    @ApiOperation(value = "댓글등록")
    public Object create(@Valid @RequestBody CommentCreateRequest request) throws MessagingException, IOException {
    
        String content = request.getContent();
        String writer = request.getWriter();
        int articleno = request.getArticleno();
        
        Comment comment = new Comment();
       
        comment.setArticleno(articleno);
        comment.setContent(content);
        comment.setWriter(writer);
        commentDao.save(comment);

        System.out.println("댓글 등록!!");
        final CommentResponse result = new CommentResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/comment/read/{articleno}")
    @ApiOperation(value = "댓글 목록")
    public Object read(@PathVariable int articleno) throws MessagingException, IOException {

        List<Comment> clist = commentDao.findCommentByArticleno(articleno);

        System.out.println("댓글 목록!!");
        final CommentResponse result = new CommentResponse();
        result.commentList = clist;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("/comment/update/")
    @ApiOperation(value = "댓글수정")
    public Object update(@Valid @RequestBody CommentCreateRequest request) {

        int no=request.getNo();
        String content = request.getContent();
        // String writer = request.getWriter();
        // int articleno = request.getArticleno();
        
        Comment comment = commentDao.getCommentByNo(no);
        comment.setContent(content);
        commentDao.save(comment);

        System.out.println("댓글 수정");
        CommentResponse result = new CommentResponse();
        result.content = content;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/comment/delete/{no}")
    @ApiOperation(value = "댓글삭제하기")
    public Object delete(@Valid @PathVariable int no) {
        Comment comment = commentDao.getCommentByNo(no);
        commentDao.delete(comment);
        System.out.println("댓글 삭제하기!! ");
        CommentResponse result = new CommentResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
   
}