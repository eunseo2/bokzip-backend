package bokzip.back.dto;

import bokzip.back.domain.Post;
import lombok.Getter;

@Getter
public class PostDto {

    private Long id;
    private String title;
    private String category;
    private String thumbnail;
    private boolean is_scrap;
    private String area;


    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.thumbnail = post.getThumbnail();
        this.is_scrap = post.getIsScrap();
        this.area = post.getArea();
    }


}
