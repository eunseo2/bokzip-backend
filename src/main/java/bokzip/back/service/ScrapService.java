package bokzip.back.service;

import bokzip.back.converter.UserConverter;
import bokzip.back.domain.*;
import bokzip.back.dto.ScrapMapping;
import bokzip.back.dto.ScrapType;
import bokzip.back.dto.UserDto;
import bokzip.back.repository.GeneralRepository;
import bokzip.back.repository.PostRepository;
import bokzip.back.repository.ScrapRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;
    private final GeneralRepository generalRepository;
    private final UserConverter userConverter;

    public ScrapService(ScrapRepository scrapRepository, PostRepository postRepository, GeneralRepository generalRepository, UserConverter userConverter) {
        this.scrapRepository = scrapRepository;
        this.postRepository = postRepository;
        this.generalRepository = generalRepository;
        this.userConverter = userConverter;
    }

    @Transactional
    public Scrap addScrap(Long Id, ScrapType type, UserDto dto) {
        User user = userConverter.converterUser(dto);

        Scrap scrap;

        if (type == ScrapType.POST) {
            Post post = postRepository.findById(Id)
                    .orElseThrow(() -> new RuntimeException("404"));
            post.addScrap(post.getStarCount());

//          scrapPostCheck(user, getPost);

            scrap = new Scrap();
            scrap.updatePost(user, post);
            scrapRepository.save(scrap);

        } else {

            General general = generalRepository.findById(Id)
                    .orElseThrow(() -> new RuntimeException("404"));

            general.addScrap(general.getStarCount());

//            scrapGeneralCheck(user, getGeneral);

            scrap = new Scrap();
            scrap.updateGeneral(user, general);
            scrapRepository.save(scrap);
        }

        return scrap;
    }

    public void deleteScrap(Long Id, ScrapType type, UserDto dto) {

        User user = userConverter.converterUser(dto);

        Scrap scrap;

        if (type == ScrapType.POST) {
            Post post = postRepository.findById(Id)
                    .orElseThrow(() -> new RuntimeException("404"));
            post.deleteScrap(post.getStarCount());
            Optional<Scrap> getScrap = scrapRepository.findByUserAndPost(user, post);
            scrapRepository.delete(getScrap.get());


        } else {

            General general = generalRepository.findById(Id)
                    .orElseThrow(() -> new RuntimeException("404"));

            general.deleteScrap(general.getStarCount());

            Optional<Scrap> getScrap = scrapRepository.findByUserAndGeneral(user, general);
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

    public List<ScrapMapping> Scraps(User user) {
        return scrapRepository.findByUser(user);
    }

}
