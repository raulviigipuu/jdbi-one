package dev.service;

import dev.dao.BlogPostDao;
import dev.dao.CommentDao;
import dev.exception.ServiceException;
import dev.model.BlogPost;
import dev.model.Comment;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class BlogService {
    private final Jdbi jdbi;

    public BlogService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    // Blog Post Operations
    public long createBlogPost(String title, String content) {
        try {
            return jdbi.withExtension(BlogPostDao.class, dao -> dao.createBlogPost(title, content));
        } catch (Exception e) {
            throw new ServiceException("Error creating blog post", e);
        }
    }

    public BlogPost getBlogPost(long id) {
        return jdbi.withExtension(BlogPostDao.class, dao -> dao.readBlogPost(id));
    }

    public void updateBlogPost(long id, String title, String content) {
        jdbi.useExtension(BlogPostDao.class, dao -> dao.updateBlogPost(id, title, content));
    }

    public void deleteBlogPost(long id) {
        jdbi.useExtension(BlogPostDao.class, dao -> dao.deleteBlogPost(id));
    }

    // Comment Operations
    public long createComment(long blogPostId, String author, String content) {
        return jdbi.withExtension(CommentDao.class, dao -> dao.createComment(blogPostId, author, content));
    }

    public List<Comment> getComments(long blogPostId) {
        return jdbi.withExtension(CommentDao.class, dao -> dao.readComments(blogPostId));
    }

    public void updateComment(long id, String author, String content) {
        jdbi.useExtension(CommentDao.class, dao -> dao.updateComment(id, author, content));
    }

    public void deleteComment(long id) {
        jdbi.useExtension(CommentDao.class, dao -> dao.deleteComment(id));
    }
}
