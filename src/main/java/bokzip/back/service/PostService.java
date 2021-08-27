package bokzip.back.service;

import bokzip.back.domain.Post;
import bokzip.back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Optional<Post> findId(Long id){
        System.out.println(postRepository.findById(id));
        return postRepository.findById(id);
    }

    public List<Post> findAll(){
        return postRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Post> getListforCategory(String category){
        return postRepository.findByCategory(category);
    }
}
