package bokzip.back.service;

import bokzip.back.domain.Post;
import bokzip.back.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired PostService postService;
    @Autowired PostRepository postRepository;

    @Test
    public void 중앙부처ID별조회(){
        //given
        //Post post = new Post();

        //given, when
        Optional<Post> post = postService.findId(1L);

        //then
        post.ifPresent(selectPost ->{
            System.out.println(selectPost.getCategory());
            System.out.println(selectPost.getTitle());
        });
    }

    @Test
    public void 중앙부처카테고리조회(){
        //given
        String category = "생활지원";

        //given, when
        List<Post> listResult = postService.getListforCategory(category);

        //then
        if(!listResult.isEmpty()){
            System.out.println("개수 : " + listResult.size()); //36개 나와야 함
            System.out.println(listResult);
        }
        else
            System.out.println("post는 null");
    }
}
