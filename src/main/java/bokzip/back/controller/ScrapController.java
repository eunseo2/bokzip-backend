package bokzip.back.controller;

import bokzip.back.config.response.SuccessResponse;
import bokzip.back.dto.ScrapType;
import bokzip.back.dto.ScrapMapping;
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

    @GetMapping("/centers/{postId}")
    public ResponseEntity addPostScrap(@PathVariable Long postId) {
        scrapService.addScrap(postId, ScrapType.POST);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 되었습니다."), HttpStatus.OK);
    }

    @GetMapping("/generals/{generalId}")
    public ResponseEntity addGeneralScrap(@PathVariable Long generalId) {
        scrapService.addScrap(generalId, ScrapType.GENERAL);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 되었습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/centers/{postId}")
    public ResponseEntity deletePostScrap(@PathVariable Long postId) {
        scrapService.deleteScrap(postId, ScrapType.POST);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 해제되었습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/generals/{generalId}")
    public ResponseEntity deleteGeneralScrap(@PathVariable Long generalId) {
        scrapService.deleteScrap(generalId, ScrapType.GENERAL);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 해제되었습니다."), HttpStatus.OK);
    }

    @GetMapping("")
    public List<ScrapMapping> scraps() {
        List<ScrapMapping> scraps = scrapService.displayScraps();
        return scraps;
    }
}
