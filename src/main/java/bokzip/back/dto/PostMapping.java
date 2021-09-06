package bokzip.back.dto;

public interface PostMapping {
    // @see : 일반탭, 추천탭, 스크랩탭 모두에서 보여질 기본 데이터는 다음과 같아서 클래스명을 HomeResponseDto로 설정
    Long getId();
    String getTitle();
    String getCategory();
    String getThumbnail();
    Boolean getIsScrap();
    String getArea();
}
