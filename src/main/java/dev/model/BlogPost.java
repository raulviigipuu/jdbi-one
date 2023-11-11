package dev.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BlogPost {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    // List to hold associated comments
    private List<Comment> comments;
}
