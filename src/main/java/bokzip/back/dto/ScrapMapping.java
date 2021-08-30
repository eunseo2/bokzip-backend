package bokzip.back.dto;

import bokzip.back.domain.General;
import bokzip.back.domain.Post;

public interface ScrapMapping {
    Long getId();
    PostMapping getPost();
    General getGeneral();
    Long getUserId();
}
