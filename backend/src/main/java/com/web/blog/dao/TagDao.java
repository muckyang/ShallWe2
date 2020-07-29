package com.web.blog.dao;

import java.util.Optional;
import com.web.blog.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagDao extends JpaRepository<Tag, String>{
    Optional<Tag> findTagByName(String name);
}