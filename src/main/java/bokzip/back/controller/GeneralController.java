package bokzip.back.controller;

import bokzip.back.domain.General;
import bokzip.back.dto.GeneralMapping;
import bokzip.back.service.GeneralService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/post")
@RestController
public class GeneralController {
    private final GeneralService generalService;

    public GeneralController(GeneralService generalService) {
        this.generalService = generalService;
    }

    //@param : 일반 전체 데이터 조회
    @GetMapping("/generals")

    public List<GeneralMapping> generals() {
        return generalService.findAll();
    }

    //@param : 일반 전체 데이터 조회
    @GetMapping("/generals/star")
    public List<GeneralMapping> StarGenerals() {
        return generalService.StarfindAll();
    }


    //@param : 일반 전체 데이터 조회
    @GetMapping("/generals/view")
    public List<GeneralMapping> ViewGenerals() {
        return generalService.ViewfindAll();
    }


    //@param : pk로 일반 데이터 조회
    @GetMapping("/general/{id}")
    public General findById(@PathVariable @Validated Long id) {
        General general = generalService.findById(id).orElseThrow(() -> new RuntimeException("404"));
        return general;
    }

    //@param : 일반 조회수 증가
    @GetMapping("/general/view/{id}")
    public void addGeneralView(@PathVariable @Validated Long id) {
        generalService.addGeneralView(id);
    }

}
