package uha.awsTest.dto;

import lombok.Getter;
import uha.awsTest.domain.posts.Posts;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    public PostsResponseDto(Posts posts) {
        this.id=posts.getId();
        this.title=posts.getTitle();
        this.content=posts.getContent();
        this.author=posts.getAuthor();
    }
}
