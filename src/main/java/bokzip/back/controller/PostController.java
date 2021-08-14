package bokzip.back.controller;

import bokzip.back.domain.Post;
import bokzip.back.repository.PostRepository;
import bokzip.back.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    //@param : 중앙부처 전체 데이터 조회
    @GetMapping("/center")
    public List<Post> selectAll(){
        return postService.findAll();
    }

    //@param : pk로 중앙부처 데이터 조회
    @GetMapping("/center/{id}")
    public Optional<Post> getOneData(@PathVariable Long id) {
        return postService.findId(id);
}

    //@param : 중앙부처 분야별 데이터 조회
    @GetMapping("/center/category/{category}")
    public List<Post> getAllCategory(@PathVariable String category){
        List<Post> categoryResult = new ArrayList<>();

        if(category != null)
            postService.getListforCategory(category).forEach(categoryResult::add);

        if(!categoryResult.isEmpty()){
            return categoryResult;
        }

        return null;
    }
}
