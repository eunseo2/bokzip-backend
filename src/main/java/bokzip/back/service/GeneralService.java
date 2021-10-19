package bokzip.back.service;

import bokzip.back.config.error.ErrorCode;
import bokzip.back.config.error.GeneralNotFoundException;
import bokzip.back.domain.General;
import bokzip.back.dto.GeneralMapping;

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
        if (id >= 100 || id < 0) {
            throw new GeneralNotFoundException(ErrorCode.NOT_FOUND);
        }
        return generalRepository.findById(id);
    }

    public List<GeneralMapping> findAll() {
        return generalRepository.findAllBy(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<GeneralMapping> StarfindAll() {
        return generalRepository.findAllBy(Sort.by(Sort.Direction.DESC, "starCount"));
    }

    public List<GeneralMapping> ViewfindAll() {
        return generalRepository.findAllBy(Sort.by(Sort.Direction.DESC, "viewCount"));

    }

    public void addGeneralView(Long id) {
        if (id >= 100 || id <= 0) {
            throw new GeneralNotFoundException(ErrorCode.NOT_FOUND);
        }
        General general = generalRepository.findById(id)
                .orElseThrow(() -> new GeneralNotFoundException(ErrorCode.NOT_FOUND));
        Integer viewCount = general.getViewCount();
        general.setViewCount(++viewCount);
        generalRepository.save(general);
    }
}
