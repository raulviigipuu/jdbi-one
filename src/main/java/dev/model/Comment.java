package dev.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    private long id;
    private long blogPostId;
    private String author;
    private String content;
    private LocalDateTime createdAt;
}
