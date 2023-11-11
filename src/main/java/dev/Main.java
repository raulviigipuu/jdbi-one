package dev;

import dev.model.BlogPost;
import dev.model.Comment;
import dev.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {
        // Set up JDBI to connect to the in-memory H2 database
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        jdbi.installPlugin(new SqlObjectPlugin());

        // Initialize the database schema
        try {
            String schema = new String(Files.readAllBytes(Paths.get(Main.class.getClassLoader().getResource("db/schema.sql").toURI())));
            jdbi.useHandle(handle -> handle.execute(schema));
        } catch (Exception e) {
            throw new RuntimeException("Error initializing the database schema", e);
        }

        // Instantiate the BlogService
        BlogService blogService = new BlogService(jdbi);

        // Create some blog posts and comments
        long postId = blogService.createBlogPost("My First Post", "Hello, World!");
        blogService.createComment(postId, "Jane Doe", "Great post!");

        // Update a blog post
        blogService.updateBlogPost(postId, "My First Post (Edited)", "Hello, World! This is an edit.");

        // Read and print the updated blog post
        BlogPost post = blogService.getBlogPost(postId);
        log.info("Blog Post: {} - {}", post.getTitle(), post.getContent());

        // Read and print comments
        List<Comment> comments = blogService.getComments(postId);
        comments.forEach(comment -> log.info("Comment by {}: {}", comment.getAuthor(), comment.getContent()));

        // Delete a blog post
        blogService.deleteBlogPost(postId);
    }
}