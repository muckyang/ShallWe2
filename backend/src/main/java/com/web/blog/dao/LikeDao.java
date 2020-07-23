package com.web.blog.dao;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LikeDao extends JpaRepository<Like, String> {
    List<Like> findLikeByArticleno(int articleno);  //게시물  좋아요 수 리턴 
    // Optional<Like> findLikeByUseridArticleno(String id,int articleno); // 계정 좋아요 여부 확인
}