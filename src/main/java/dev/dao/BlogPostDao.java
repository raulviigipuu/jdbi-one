package dev.dao;

import dev.model.BlogPost;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(BlogPost.class)
public interface BlogPostDao {
    @SqlUpdate("INSERT INTO blog_posts (title, content) VALUES (:title, :content)")
    @GetGeneratedKeys
    long createBlogPost(@Bind("title") String title, @Bind("content") String content);

    @SqlQuery("SELECT * FROM blog_posts WHERE id = :id")
    BlogPost readBlogPost(@Bind("id") long id);

    @SqlUpdate("UPDATE blog_posts SET title = :title, content = :content WHERE id = :id")
    void updateBlogPost(@Bind("id") long id, @Bind("title") String title, @Bind("content") String content);

    @SqlUpdate("DELETE FROM blog_posts WHERE id = :id")
    void deleteBlogPost(@Bind("id") long id);
}