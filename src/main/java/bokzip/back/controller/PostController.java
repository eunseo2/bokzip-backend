package bokzip.back.controller;

import bokzip.back.domain.Post;
import bokzip.back.dto.PostMapping;
import bokzip.back.dto.SortType;
import bokzip.back.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //@param : [중앙부처 + 로컬] 전체 데이터 조회
    @GetMapping("/centers")
    public List<PostMapping> selectAll() {
        return postService.findAll();
    }


    //@param : [중앙부처 + 로컬] pk로 데이터 조회
    @GetMapping("/center/{id}")
    public Optional<Post> addOneData(@PathVariable @Validated Long id) {
        Optional<Post> post = postService.findId(id);

        if (!post.isPresent()) //null 값 반환 방지
            throw new RuntimeException("404");

        return post;
    }

    //@param : [중앙부처 + 로컬] 조회수 증가
    @GetMapping("/center/view/{id}")
    public void addPostView(@PathVariable @Validated Long id) {
        postService.addPostView(id);
    }

    //@param : [중앙부처 + 로컬] 맞춤형 정보
    @GetMapping("/center/custom")
    public List<PostMapping> getPosts(@RequestParam(value = "category") String category,
                                      @RequestParam(value = "local", required = false, defaultValue = "") String local,
                                      @RequestParam(value = "sort", required = false, defaultValue = "") SortType sort
    ) {
        return postService.getListCategorySort(category, sort);
    }
}
