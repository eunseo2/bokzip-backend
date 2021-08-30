package bokzip.back.repository;

import bokzip.back.domain.Post;
import bokzip.back.dto.HomeMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class PostRepositroyTest {
    @Autowired
    PostRepository postRepository;

    @Test
    public void 중앙부처ID별조회(){
        //given, when
        Optional<Post> post = postRepository.findById(1L);

        //then
        post.ifPresent(selectPost ->{
            System.out.println(selectPost.getCategory());
            System.out.println(selectPost.getTitle());
        });
    }

    @Test
    public void 중앙부처전체데이터조회(){
        //given, when
        List<HomeMapping> post = postRepository.findAllBy(Sort.by(Sort.Direction.ASC, "id"));

        //then
        if(!post.isEmpty())
            System.out.println(post);
        else
            System.out.println("post는 null");
    }

    @Test
    public void 중앙부처_카테고리에_해당하는_데이터조회(){
        //given
        String category = "%지원";

        //when
        List<HomeMapping> listResult = postRepository.findByCategoryLike(category);

        //then
        if(!listResult.isEmpty()){
            System.out.println("개수 : " + listResult.size()); //36개 나와야 함
            System.out.println(listResult);
        }
        else
            System.out.println("post는 null");
    }
}
