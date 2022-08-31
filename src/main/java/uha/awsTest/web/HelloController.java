package uha.awsTest.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uha.awsTest.dto.HelloReponseDto;
import uha.awsTest.service.PostService;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final PostService postService;

//    @GetMapping("/hello/dto")
//    public HelloReponseDto helloReponseDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
//        return new HelloReponseDto(name, amount);
//    }
    @GetMapping("/")
    public String hello(Model model){
        model.addAttribute("posts",postService.findAllDesc());
        return "index" ;
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save" ;
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        model.addAttribute("post",postService.findById(id));
        return "posts-update";
    }


}
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String hello(){
//        return "hello" ;
//    }
//
//}
