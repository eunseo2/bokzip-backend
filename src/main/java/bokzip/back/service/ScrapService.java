package bokzip.back.service;

import bokzip.back.config.error.*;
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

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Service
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;
    private final GeneralRepository generalRepository;
    private final UserConverter userConverter;
    private final HttpSession httpSession;

    public ScrapService(ScrapRepository scrapRepository, PostRepository postRepository, GeneralRepository generalRepository, UserConverter userConverter, HttpSession httpSession) {
        this.scrapRepository = scrapRepository;
        this.postRepository = postRepository;
        this.generalRepository = generalRepository;
        this.userConverter = userConverter;
        this.httpSession = httpSession;
    }

    @Transactional
    public Scrap addScrap(Long Id, ScrapType type) {
        User user = getSessionUser();
        Scrap scrap = new Scrap();
        if (type == ScrapType.POST) {
            Post post = postRepository.findById(Id)
                    .orElseThrow(() -> new PostNotFoundException(ErrorCode.NOT_FOUND));
            post.addScrap(post.getStarCount());
            scrapPostCheck(user, post);
            scrap.updatePost(user, post);
            scrapRepository.save(scrap);
        } else {
            General general = generalRepository.findById(Id)
                    .orElseThrow(() -> new GeneralNotFoundException(ErrorCode.NOT_FOUND));
            general.addScrap(general.getStarCount());
            scrapGeneralCheck(user, general);
            scrap.updateGeneral(user, general);
            scrapRepository.save(scrap);
        }
        return scrap;
    }

    public void deleteScrap(Long Id, ScrapType type) {
        User user = getSessionUser();
        if (type == ScrapType.POST) {
            Post post = postRepository.findById(Id)
                    .orElseThrow(() -> new PostNotFoundException(ErrorCode.NOT_FOUND));
            post.deleteScrap(post.getStarCount());
            Scrap getScrap = scrapRepository.findByUserAndPost(user, post)
                    .orElseThrow(() -> new UnableDeleteException(ErrorCode.UNABLE_DELETE_SCRAP));
            scrapRepository.delete(getScrap);
        } else {
            General general = generalRepository.findById(Id)
                    .orElseThrow(() -> new GeneralNotFoundException(ErrorCode.NOT_FOUND));
            general.deleteScrap(general.getStarCount());
            Scrap getScrap = scrapRepository.findByUserAndGeneral(user, general)
                    .orElseThrow(() -> new UnableDeleteException(ErrorCode.UNABLE_DELETE_SCRAP));
            scrapRepository.delete(getScrap);
        }
    }

    private void scrapPostCheck(User user, Post post) {
        Scrap scrap = scrapRepository.findByUserAndPost(user, post)
                .orElseGet(() -> null);
        if (Objects.nonNull(scrap)) {
            throw new DuplicateScrapException(ErrorCode.VALID_SCRAP);
        }
    }

    private void scrapGeneralCheck(User user, General general) {
        Scrap scrap = scrapRepository.findByUserAndGeneral(user, general)
                .orElseGet(() -> null);
        if (Objects.nonNull(scrap)) {
            throw new DuplicateScrapException(ErrorCode.VALID_SCRAP);
        }
    }

    public List<ScrapMapping> displayScraps() {
        User user = getSessionUser();
        List<ScrapMapping> scraps = scrapRepository.findByUser(user);
        return scraps;
    }

    private User getSessionUser() {
        UserDto userDto = (UserDto) httpSession.getAttribute("user");
        if (userDto == null) {
            throw new NoAuthException(ErrorCode.UN_AUTHORIZED);
        }
        User user = userConverter.converterUser(userDto);
        return user;
    }
}
