package bokzip.back.service;

import bokzip.back.domain.Post;
import bokzip.back.dto.HomeMapping;
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
        Optional<Post> post = postService.findId(10000L);

        //then
        post.ifPresent(selectPost ->{
            System.out.println(selectPost.getCategory());
            System.out.println(selectPost.getTitle());
        });
    }

    @Test
    public void 중앙부처카테고리조회(){
        //given
        String category1 = "생활지원";
        String category2 = "교육지원";
        String category3 = "지원";
//        String category4 = "12"; //error
//        String category5 = "ads"; //error

        //when
        List<HomeMapping> listResult1 = postService.getListLikeCategory(category1);
        List<HomeMapping> listResult2 = postService.getListLikeCategory(category2);
        List<HomeMapping> listResult3 = postService.getListLikeCategory(category3);
//        List<HomeMapping> listResult4 = postService.getListLikeCategory(category4); //error
//        List<HomeMapping> listResult5 = postService.getListLikeCategory(category5); //error

        //then
        if(!listResult1.isEmpty()){
            System.out.println("개수 : " + listResult1.size()); //36개 나와야 함
            System.out.println(listResult1.toString());
        }
        else
            System.out.println("post는 null");

        if(!listResult2.isEmpty()){
            System.out.println("개수 : " + listResult2.size()); //36개 나와야 함
            System.out.println(listResult2.toString());
        }
        else
            System.out.println("post는 null");

        if(!listResult3.isEmpty()){
            System.out.println("개수 : " + listResult3.size()); //36개 나와야 함
            System.out.println(listResult3.toString());
        }
        else
            System.out.println("post는 null");

    }
}
