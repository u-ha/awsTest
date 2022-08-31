package uha.awsTest.web;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uha.awsTest.domain.posts.Posts;
import uha.awsTest.domain.posts.PostsRepository;
import uha.awsTest.dto.PostsSaveRequestDto;
import uha.awsTest.dto.PostsUpdateRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;




    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity=restTemplate.postForEntity(url,requestDto, Long.class);
        //then

        System.out.println("Status Code: " + responseEntity.getStatusCode());
        System.out.println("Id: " + responseEntity.getBody());
        System.out.println("Location: " + responseEntity.getHeaders().getLocation());
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정된다() throws Exception {
        //given

        Posts save = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author").build());

        PostsUpdateRequestDto postsUpdateRequestDto=PostsUpdateRequestDto.builder()
                .title("newtitle")
                .content("newcontent")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/"+save.getId();
        HttpEntity<PostsUpdateRequestDto> httpEntity=new HttpEntity<>(postsUpdateRequestDto);

        ResponseEntity<Long> responseEntity=restTemplate.exchange(url, HttpMethod.PUT,httpEntity, Long.class);



        System.out.println("Status Code: " + responseEntity.getStatusCode());
        System.out.println("Id: " + responseEntity.getBody());
        System.out.println("Location: " + responseEntity.getHeaders().getLocation());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void simpleTest3() throws  Exception {


        ResponseEntity<String> forEntity = restTemplate.getForEntity("/", String.class);


        Assertions.assertEquals(forEntity.getStatusCode(), HttpStatus.OK);
    }
}