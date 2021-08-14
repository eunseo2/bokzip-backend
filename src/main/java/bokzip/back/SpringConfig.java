package bokzip.back;

import bokzip.back.repository.PostRepository;
import bokzip.back.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class SpringConfig {

    private final PostRepository postRepository;

    @Autowired //DI
    public SpringConfig(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Bean //DI
    public PostService postService(){
        return new PostService(postRepository);
    }
}
