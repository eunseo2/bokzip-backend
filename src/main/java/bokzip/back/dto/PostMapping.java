package bokzip.back.dto;

public interface PostMapping {
    Long getId();
    String getTitle();
    String getCategory();
    String getThumbnail();
    Boolean getIsScrap();
}
