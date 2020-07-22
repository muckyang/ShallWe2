package com.web.blog.dao;

import java.util.List;
import com.web.blog.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentDao extends JpaRepository<Comment, String> {
   
    List<Comment> findCommentByArticleno(int articleno);
    Comment getCommentByNo(int no);

}