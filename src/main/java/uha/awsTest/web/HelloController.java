package uha.awsTest.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uha.awsTest.config.auth.SessionUser;
import uha.awsTest.dto.HelloReponseDto;
import uha.awsTest.service.PostService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final PostService postService;
    private final HttpSession httpSession;

//    @GetMapping("/hello/dto")
//    public HelloReponseDto helloReponseDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
//        return new HelloReponseDto(name, amount);
//    }
//    @GetMapping("/")
//    public String hello(Model model){
//        model.addAttribute("posts",postService.findAllDesc());
//        return "index" ;
//    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAllDesc());

        // 로그인 성공 시 httpSession.getAttribute("user") 에서 값 가져올 수 있음
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user!=null) {	// session에 저장된 값이 있을 때만 model에 userName으로 등록
            model.addAttribute("userName", user.getName());
        }

        return "index";
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
