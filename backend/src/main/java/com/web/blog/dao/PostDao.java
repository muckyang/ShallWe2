package com.web.blog.dao;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostDao extends JpaRepository<Post, String> {
    // Post getUserByEmail(String email);
    Post getPostByPid(int pid);
    // Post getUserByNickname(String nickname);
    List<Post> findPostByTemp(boolean temp);
    Optional<Post> findPostByPid(int pid);


}
