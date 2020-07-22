package com.web.blog.dao;
import java.util.List;

import com.web.blog.model.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDao extends JpaRepository<Like, String> {
    List<Like> findByarticle(int articleno); 
}