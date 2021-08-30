package bokzip.back.service;

import bokzip.back.domain.*;
import bokzip.back.dto.ScrapMapping;
import bokzip.back.repository.GeneralRepository;
import bokzip.back.repository.PostRepository;
import bokzip.back.repository.ScrapRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;
    private final GeneralRepository generalRepository;

    public ScrapService(ScrapRepository scrapRepository, PostRepository postRepository, GeneralRepository generalRepository) {
        this.scrapRepository = scrapRepository;
        this.postRepository = postRepository;
        this.generalRepository = generalRepository;
    }

    public Scrap addPostScrap(Long postId) {

        return getAddScrap(postId, ScrapType.POST);
    }


    public Scrap addGeneralScrap(Long generalId) {

        return getAddScrap(generalId, ScrapType.GENERAL);
    }

    private Scrap getAddScrap(Long Id, ScrapType type) {
        Optional<Post> getPost = null;
        Optional<General> getGeneral = null;

        Scrap scrap;
        User user = new User();
        user.setId(1L);


        if (type == ScrapType.POST) {
            getPost = postRepository.findById(Id);
            getPost.ifPresent(post -> {
                int startCount = post.getStarCount();
                post.setStarCount(++startCount);
            });
            scrapPostCheck(user, getPost);
            scrap = new Scrap(user, getPost.get());
            scrapRepository.save(scrap);

        } else {
            getGeneral = generalRepository.findById(Id);
            getGeneral.ifPresent(general -> {
                int startCount = general.getStarCount();
                general.setStarCount(++startCount);
            });
            scrapGeneralCheck(user, getGeneral);
            scrap = new Scrap(user, getGeneral.get());
            scrapRepository.save(scrap);
        }

        return scrap;
    }

    public void deletePostScrap(Long postId) {
        getDeleteScrap(postId, ScrapType.POST);
    }

    public void deleteGeneralScrap(Long generalId) {
        getDeleteScrap(generalId, ScrapType.GENERAL);
    }

    private void getDeleteScrap(Long Id, ScrapType type) {
        Optional<Post> getPost = null;
        Optional<General> getGeneral = null;

        User user = new User();
        user.setId(1L);


        if (type == ScrapType.POST) {
            getPost = postRepository.findById(Id);
            getPost.ifPresent(post -> {
                int startCount = post.getStarCount();
                post.setStarCount(--startCount);
            });
            Optional<Scrap> getScrap = scrapRepository.findByUserAndPost(user, getPost.get());
            scrapRepository.delete(getScrap.get());

        } else {
            getGeneral = generalRepository.findById(Id);
            getGeneral.ifPresent(general -> {
                int startCount = general.getStarCount();
                general.setStarCount(--startCount);
            });
            Optional<Scrap> getScrap = scrapRepository.findByUserAndGeneral(user, getGeneral.get());
            scrapRepository.delete(getScrap.get());
        }

    }


    private Optional<Scrap> scrapPostCheck(User user, Optional<Post> post) {
        Optional<Scrap> scrap = scrapRepository.findByUserAndPost(user, post.get());
        if (scrap.isPresent()) {
            throw new RuntimeException("409");
        }
        return scrap;
    }

    private Optional<Scrap> scrapGeneralCheck(User user, Optional<General> general) {
        Optional<Scrap> scrap = scrapRepository.findByUserAndGeneral(user, general.get());
        if (scrap.isPresent()) {
            throw new RuntimeException("409");
        }
        return scrap;

    }


    public List<ScrapMapping> Scraps() {
        User user = new User();
        user.setId(1L);
        return scrapRepository.findByUser(user);
    }


}
