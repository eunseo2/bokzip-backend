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

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> findId(Long id) {
        if (id >= 1000 || id <= 0) {
            throw new RuntimeException("404");
        }
        return postRepository.findById(id);
    }

    public List<PostMapping> findAll() {
        return postRepository.findAllBy(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<PostMapping> getListLikeCategory(String category) {
        String req_category = "%";
        boolean isNumber = category.matches("^[0-9]*$");
        boolean isEnglish = category.matches("^[a-zA-Z]*$");
        if (isNumber || isEnglish) {
            throw new RuntimeException("400");
        }

        req_category += category;
        List<PostMapping> result = postRepository.findByCategoryLike(req_category);
        if (result.isEmpty()) { //null 값 반환 방지
            throw new RuntimeException("404");
        }
        return result;
    }

    public void addPostView(Long id) {
        if (id >= 1000 || id <= 0) {
            throw new RuntimeException("404");
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("404"));
        Integer viewCount = post.getViewCount();
        post.setViewCount(++viewCount);
        postRepository.save(post);
    }

    public List<PostMapping> getListCategorySort(String category, String area, SortType sort) {
        boolean isNumber = category.matches("^[0-9]*$");
        boolean isEnglish = category.matches("^[a-zA-Z]*$");
        List<PostMapping> result;
        if (isNumber || isEnglish) {
            throw new RuntimeException("400");
        }
        if (category.equals("전체")) {
            category = "지원";
        }
        switch (sort) {
            case viewCount:
                result = postRepository.findByCategoryContainsAndAreaContainsOrderByViewCountDesc(category, area);
            case starCount:
                result = postRepository.findByCategoryContainsAndAreaContainsOrderByStarCountDesc(category, area);
            default:
                result = postRepository.findByCategoryContainsAndAreaContainsOrderByIdAsc(category, area);
        }
        if (result.isEmpty()) { //null 값 반환 방지
            throw new RuntimeException("404");
        }
        return result;
    }
}
