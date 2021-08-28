package bokzip.back.controller;

import bokzip.back.domain.Post;
import bokzip.back.dto.ErrorResponse;
import bokzip.back.dto.HomeResponseDto;
import bokzip.back.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity selectAll(){
        List<Post> post_list = postService.findAll();

        if(post_list != null) //200 OK
            return new ResponseEntity<>(post_list, HttpStatus.OK);
        else //404 NOT FOUND (조회 결과가 null인 경우)
            return new ResponseEntity<>(ErrorResponse.res("요청 받은 리소스를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
    }

    //@param : pk로 중앙부처 데이터 조회
    @GetMapping("/center/{id}")
    public ResponseEntity getOneData(@PathVariable Long id) {
        Optional<Post> post = postService.findId(id);

        if(id >= 1000 || id <= 0){ //400 잘못된 요청
            return new ResponseEntity<>(
                    ErrorResponse.res("일치하는 정보가 없습니다. 사용자 id를 다시 확인해주십시오."), HttpStatus.BAD_REQUEST);
        }if(!post.isPresent()){ //404 Not Found
            return new  ResponseEntity<>(ErrorResponse.res("요청받은 리소스를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
        }else{ //200 OK
            return new  ResponseEntity<>(post, HttpStatus.OK);
        }
    }

    //@param : 중앙부처 분야별 데이터 조회
    @GetMapping("/center/category/{category}")
    public ResponseEntity getAllCategory(@PathVariable String category){
        List<HomeResponseDto> categoryResult = new ArrayList<>();

        //@todo : category가 문자인 경우 / 숫자인 경우 / null 인 경우에 따라 잘못된 요청 return 해야 함.
        //@see : 현재 코드는 null이 아닌 경우 우선 List에 담도록 구현. empty() 여부에 따라 결과를 return
        if(category != null) //category가 있을 경우, List에 보관
            postService.getListforCategory(category).forEach(categoryResult::add);
        else //404 잘못된 요청
            return new ResponseEntity<>(ErrorResponse.res("잘못된 요청입니다. 분야를 다시 입력해주세요."), HttpStatus.BAD_REQUEST);

        if(!categoryResult.isEmpty()) //200 OK
            return new ResponseEntity<>(categoryResult, HttpStatus.OK);
        else //404 NOT FOUND
            return new ResponseEntity<>(ErrorResponse.res("요청 받은 리소스를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
    }

}
