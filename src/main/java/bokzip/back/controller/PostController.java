package bokzip.back.controller;

import bokzip.back.domain.Post;
import bokzip.back.dto.PostMapping;
import bokzip.back.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/post")
@RestController
public class PostController{

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //@param : [중앙부처 + 로컬] 전체 데이터 조회
    @GetMapping("/centers")
    public List<PostMapping> selectAll(){
        return postService.findAll();}

    //@param : [중앙부처 + 로컬] pk로 데이터 조회
    @GetMapping("/center/{id}")
    public Optional<Post> getOneData(@PathVariable @Validated Long id){ //id에 validated로 선언하여 binding error 시 에러 호출
        Optional<Post> post = postService.findId(id);

        if(!post.isPresent()) //null 값 반환 방지
            throw new RuntimeException("404");

        return post;
    }

    //@param : [중앙부처] category로 조회
    @GetMapping("/center/category/{category}")
    public List<PostMapping> getAllCategory(@PathVariable @Validated String category){
        List<PostMapping> categoryResult = new ArrayList<>();

        postService.getListLikeCategory(category).forEach(categoryResult::add);

        if(categoryResult.isEmpty()) //null 값 반환 방지
            throw new RuntimeException("404");

        return categoryResult;
    }

}
