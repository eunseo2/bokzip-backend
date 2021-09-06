package bokzip.back.service;

import bokzip.back.domain.Post;
import bokzip.back.dto.PostMapping;

import bokzip.back.dto.SortType;

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
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // @param : [중앙부처 + 로컬] 데이터 PK로 조회
    public Optional<Post> findId(Long id) {
        if (id >= 1000 || id <= 0) //id가 null인 경우 url == 전체 조회 url
            throw new RuntimeException("404");

        Object nullCheck = id;

        if (nullCheck == null)

            throw new RuntimeException("400");

        return postRepository.findById(id);
    }

    // @param : [중앙부처 + 로컬] 모든 데이터 조회
    public List<PostMapping> findAll() {

        return postRepository.findAllBy(Sort.by(Sort.Direction.ASC, "id"));
    }

    // @param : [중앙부처] 데이터 category로 조회

    public List<PostMapping> getListLikeCategory(String category) {
        String req_category = "%";

        boolean isNumber = category.matches("^[0-9]*$");
        boolean isEnglish = category.matches("^[a-zA-Z]*$");

        if (isNumber || isEnglish)
            throw new RuntimeException("400");

        req_category += category;

        return postRepository.findByCategoryLike(req_category);
    }

    public void addPostView(Long id) {
        if (id >= 1000 || id <= 0)
            throw new RuntimeException("404");

        Optional<Post> post = postRepository.findById(id);
        Integer viewCount = post.get().getViewCount();
        post.get().setViewCount(++viewCount);
        postRepository.save(post.get());
    }

    public List<PostMapping> getListCategorySort(String category, String area, SortType sort) {


        boolean isNumber = category.matches("^[0-9]*$");
        boolean isEnglish = category.matches("^[a-zA-Z]*$");

        if (isNumber || isEnglish)
            throw new RuntimeException("400");

        if (category.equals("전체")) category = "지원";

        switch (sort) {
            case viewCount:
                return postRepository.findByCategoryContainsAndAreaContainsOrderByViewCountDesc(category, area);
            case starCount:
                return postRepository.findByCategoryContainsAndAreaContainsOrderByStarCountDesc(category, area);
            default:
                return postRepository.findByCategoryContainsAndAreaContainsOrderByIdAsc(category, area);
        }

    }
}
