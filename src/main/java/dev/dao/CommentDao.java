package dev.dao;

import dev.model.Comment;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(Comment.class)
public interface CommentDao {
    @SqlUpdate("INSERT INTO comments (blog_post_id, author, content) VALUES (:blogPostId, :author, :content)")
    @GetGeneratedKeys
    long createComment(@Bind("blogPostId") long blogPostId, @Bind("author") String author, @Bind("content") String content);

    @SqlQuery("SELECT * FROM comments WHERE blog_post_id = :blogPostId")
    List<Comment> readComments(@Bind("blogPostId") long blogPostId);

    @SqlUpdate("UPDATE comments SET author = :author, content = :content WHERE id = :id")
    void updateComment(@Bind("id") long id, @Bind("author") String author, @Bind("content") String content);

    @SqlUpdate("DELETE FROM comments WHERE id = :id")
    void deleteComment(@Bind("id") long id);
}
