package bokzip.back.controller;

import bokzip.back.config.response.SuccessResponse;
import bokzip.back.dto.ScrapType;
import bokzip.back.domain.User;
import bokzip.back.dto.ScrapMapping;
import bokzip.back.dto.UserDto;
import bokzip.back.service.ScrapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/scraps")
@RestController
public class ScrapController {
    private final ScrapService scrapService;
    private final HttpSession httpSession;

    public ScrapController(ScrapService scrapService, HttpSession httpSession) {
        this.scrapService = scrapService;
        this.httpSession = httpSession;
    }

    @PostMapping("/centers/{postId}")
    public ResponseEntity addPostScrap(@PathVariable Long postId) {
        UserDto user = (UserDto) httpSession.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("401");
        }
        scrapService.addScrap(postId, ScrapType.POST, user);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/generals/{generalId}")
    public ResponseEntity addGeneralScrap(@PathVariable Long generalId) {
        UserDto user = (UserDto) httpSession.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("401");
        }
        scrapService.addScrap(generalId, ScrapType.GENERAL, user);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 되었습니다."), HttpStatus.OK);
    }


    @DeleteMapping("/centers/{postId}")
    public ResponseEntity deletePostScrap(@PathVariable Long postId) {
        UserDto user = (UserDto) httpSession.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("401");
        }
        scrapService.deleteScrap(postId, ScrapType.POST, user);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 해제되었습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/generals/{generalId}")
    public ResponseEntity deleteGeneralScrap(@PathVariable Long generalId) {
        UserDto user = (UserDto) httpSession.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("401");
        }
        scrapService.deleteScrap(generalId, ScrapType.GENERAL, user);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 해제되었습니다."), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<ScrapMapping> scraps() {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("401");
        }
        List<ScrapMapping> scrap_list = scrapService.Scraps(user);

        if (scrap_list.isEmpty())
            throw new RuntimeException("404");

        return scrap_list;
    }
}
