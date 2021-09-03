package bokzip.back.controller;

import bokzip.back.config.ErrorResponse;
import bokzip.back.config.SuccessResponse;
import bokzip.back.dto.ScrapMapping;
import bokzip.back.service.ScrapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
public class ScrapController {
    private final ScrapService scrapService;

    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    @PostMapping("/center/{postId}")
    public ResponseEntity addPostScrap(@PathVariable Long postId) {
        scrapService.addPostScrap(postId);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/general/{generalId}")
    public ResponseEntity addGeneralScrap(@PathVariable Long generalId) {
        scrapService.addGeneralScrap(generalId);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 되었습니다."), HttpStatus.OK);
    }


    @DeleteMapping("/center/{postId}")
    public ResponseEntity deletePostScrap(@PathVariable Long postId) {
        scrapService.deletePostScrap(postId);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 해제되었습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/general/{generalId}")
    public ResponseEntity deleteGeneralScrap(@PathVariable Long generalId) {
        scrapService.deleteGeneralScrap(generalId);
        return new ResponseEntity<>(SuccessResponse.res("스크랩 해제되었습니다."), HttpStatus.OK);
    }

    @GetMapping("/scraps")
    public List<ScrapMapping> scraps() {
        List<ScrapMapping> scrap_list = scrapService.Scraps();

        if(scrap_list.isEmpty())
            throw new RuntimeException("404");

        return scrap_list;
    }
}
