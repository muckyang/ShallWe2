package com.web.blog.dao;
import java.util.List;
import com.web.blog.model.article_tag.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleTagDao extends JpaRepository<ArticleTag, String>{
    List<ArticleTag> findArticleTagByArticleId(int articleId);
    List<ArticleTag> findArticleTagByTagId(int tagId);
}