CREATE TABLE blog_posts
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(255),
    content    TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comments
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    blog_post_id BIGINT,
    author       VARCHAR(255),
    content      TEXT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (blog_post_id) REFERENCES blog_posts (id)  ON DELETE CASCADE
);
