package uha.awsTest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uha.awsTest.domain.posts.Posts;
import uha.awsTest.domain.posts.PostsRepository;
import uha.awsTest.dto.PostsListResponseDto;
import uha.awsTest.dto.PostsResponseDto;
import uha.awsTest.dto.PostsSaveRequestDto;
import uha.awsTest.dto.PostsUpdateRequestDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    
    private final PostsRepository postsRepository;

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당세시글이 없습니다"));


        posts.update(postsUpdateRequestDto.getTitle(),postsUpdateRequestDto.getContent());
        return posts.getId();
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();

    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당세시글이 없습니다"));
        return new PostsResponseDto(posts);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 없다"));
        postsRepository.delete(posts);
    }
}
