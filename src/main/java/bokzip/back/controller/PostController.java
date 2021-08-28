package bokzip.back.controller;

import bokzip.back.config.CustomException;
import bokzip.back.config.GlobalExceptionHandler;
import bokzip.back.domain.Post;
import bokzip.back.dto.HomeResponseDto;
import bokzip.back.service.PostService;
import com.sun.tools.javac.main.Option;
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

    //@param : 중앙부처 전체 데이터 조회
    @GetMapping("/center")
    public List<Post> selectAll() throws Exception {
        return postService.findAll();}

    //@param : pk로 중앙부처 데이터 조회
    @GetMapping("/center/{id}")
    public Optional<Post> getOneData(@PathVariable @Validated Long id) throws Exception  { //id에 validated로 선언하여 binding error 시 에러 호출
        Optional<Post> post = postService.findId(id);

        if(!post.isPresent()) //null 값 반환 방지
            throw new CustomException();

        return post;
    }

    //@param : 중앙부처 분야별 데이터 조회
    @GetMapping("/center/category/{category}")
    public List<HomeResponseDto> getAllCategory(@PathVariable @Validated String category) throws Exception  {
        List<HomeResponseDto> categoryResult = new ArrayList<>();

        if(category != null)
            postService.getListforCategory(category).forEach(categoryResult::add);
        else //null 값 반환 방지
            throw new NullPointerException();

        if(categoryResult.isEmpty()) //null 값 반환 방지
            throw new NullPointerException();

        return categoryResult;
    }

}
