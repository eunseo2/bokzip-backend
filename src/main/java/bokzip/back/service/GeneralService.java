package bokzip.back.service;

import bokzip.back.domain.General;
import bokzip.back.repository.GeneralRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralService {
    private final GeneralRepository generalRepository;

    public GeneralService(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
    }

    public Optional<General> findById(Long id) {
        return generalRepository.findById(id);
    }

    public List<General> findAll() {
        return generalRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<General> StarfindAll() {
        return generalRepository.findAll(Sort.by(Sort.Direction.DESC, "starCount"));
    }

    public List<General> ViewfindAll() {
        return generalRepository.findAll(Sort.by(Sort.Direction.DESC, "viewCount"));
    }
}
