package uha.awsTest.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_저장(){
        postsRepository.save(Posts.builder()
                .title("백설공주")
                .content("일곱 난쟁이")
                .author("김씨")
                .build());
        List<Posts> postsList=postsRepository.findAll();

        Posts posts = postsList.get(0);
        Assertions.assertEquals("백설공주",posts.getTitle());
        Assertions.assertEquals("일곱 난쟁이",posts.getContent());

    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getLastModifiedDate());
        System.out.println(posts.getAuthor());
        //assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getLastModifiedDate()).isAfter(now);
    }
}