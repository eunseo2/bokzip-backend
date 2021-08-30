package bokzip.back.service;

import bokzip.back.domain.General;
import bokzip.back.dto.HomeMapping;
import bokzip.back.repository.GeneralRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralService {
    private final GeneralRepository generalRepository;

    public GeneralService(GeneralRepository generalRepository){
        this.generalRepository = generalRepository;
    }

    public Optional<General>findById(Long id){
        //id가 null == 전체조회와 같음
        if(id >= 100 || id < 0)
            throw new RuntimeException("404");

        return generalRepository.findById(id);
    }

    public List<HomeMapping> findAll(){
        return generalRepository.findAllBy();
    }
}
