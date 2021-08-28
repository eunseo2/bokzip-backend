package bokzip.back.service;

import bokzip.back.config.CustomException;
import bokzip.back.config.GlobalExceptionHandler;
import bokzip.back.domain.Post;
import bokzip.back.dto.HomeResponseDto;
import bokzip.back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired //DI
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    /**
     * [중앙부처] 데이터 조회
     */
    // @param : 중앙부처 데이터
    public Optional<Post> findId(Long id) throws CustomException{
        if (id >= 1000 || id <= 0) //id가 null인 경우 url == 전체 조회 url
            throw new CustomException();

        return postRepository.findById(id);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public List<HomeResponseDto> getListforCategory(String category){
        boolean isNumber = category.matches("^[0-9]*$");
        boolean isEnglish = category.matches("^[a-zA-Z]*$");

        if(isNumber || isEnglish)
            throw new CustomException();

        return postRepository.findByCategory(category);
    }
}
