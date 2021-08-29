package bokzip.back.controller;

import bokzip.back.dto.ScrapMapping;
import bokzip.back.service.ScrapService;
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
    public String addPostScrap(@PathVariable Long postId) {
        scrapService.addPostScrap(postId);
        return "스크랩 되었습니다.";
    }

    @PostMapping("/general/{generalId}")
    public String addGeneralScrap(@PathVariable Long generalId) {
        scrapService.addGeneralScrap(generalId);
        return "스크랩 되었습니다.";
    }


    @DeleteMapping("/center/{postId}")
    public String deletePostScrap(@PathVariable Long postId) {
        scrapService.deletePostScrap(postId);
        return "스크랩 해제되었습니다.";
    }

    @DeleteMapping("/general/{generalId}")
    public String deleteGeneralScrap(@PathVariable Long generalId) {
        scrapService.deleteGeneralScrap(generalId);
        return "스크랩 해제되었습니다.";
    }

    @GetMapping("/scraps")
    public List<ScrapMapping> scraps() {
        return scrapService.Scraps();
    }
}
