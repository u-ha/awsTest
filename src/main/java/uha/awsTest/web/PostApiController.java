package uha.awsTest.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uha.awsTest.dto.PostsResponseDto;
import uha.awsTest.dto.PostsSaveRequestDto;
import uha.awsTest.dto.PostsUpdateRequestDto;
import uha.awsTest.service.PostService;


@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){
        return postService.update(id, postsUpdateRequestDto);
    }

//    @GetMapping("/api/v1/posts/{id}")
//    public PostsResponseDto findById(@PathVariable Long id){
//        return postService.findById(id);
//    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postService.delete(id);
        return id;
    }

//    @GetMapping("/posts/update/{id}")
//    public String postUpdate(@PathVariable Long id, Model model){
//        model.addAttribute("post",postService.findById(id));
//        return "posts-update";
//    }
}
