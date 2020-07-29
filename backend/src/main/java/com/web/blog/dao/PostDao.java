package com.web.blog.dao;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostDao extends JpaRepository<Post, String> {
    // Post getUserByEmail(String email);
    // Post getUserByNickname(String nickname);
    Post getPostByArticleId(int articleId);
    List<Post> findPostByTemp(int temp);
    List<Post> findPostByTempAndCategoryId(int temp,int categoryId);
    List<Post> findPostByTempAndWriter(int temp, String writer);
    Optional<Post> findPostByArticleId(int articleId);
    
    List<Post> findPostByTitleLike(String word);
    List<Post> findPostByWriterLike(String word);


}
