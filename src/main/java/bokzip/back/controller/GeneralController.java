package bokzip.back.controller;

import bokzip.back.domain.General;
import bokzip.back.dto.ErrorResponse;
import bokzip.back.service.GeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/post")
@RestController
public class GeneralController {
    private final GeneralService generalService;

    public GeneralController(GeneralService generalService){
        this.generalService = generalService;
    }

    //@param : 일반 전체 데이터 조회
    @GetMapping("/general")
    public List<General> generals(){
        return generalService.findAll();
    }

    //@param : pk로 일반 데이터 조회
    @GetMapping("/general/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<General> general = generalService.findById(id);

        if(id >= 100 || id <= 0){
            return new ResponseEntity<>(
                    ErrorResponse.res("일치하는 정보가 없습니다. general_id를 다시 확인해주십시오."), HttpStatus.BAD_REQUEST);
        }else if(!general.isPresent()){ //404 Not Found
            return new  ResponseEntity<>(ErrorResponse.res("요청받은 리소스를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
        }else{ //200 OK
            return new  ResponseEntity<>(general, HttpStatus.OK);
        }

    }

}
