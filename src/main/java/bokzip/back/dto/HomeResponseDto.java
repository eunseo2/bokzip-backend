package bokzip.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor // DTO에 바인딩할 필드는 생성자 작업이 필수
public class HomeResponseDto {
    // @see : 일반탭, 추천탭, 스크랩탭 모두에서 보여질 기본 데이터는 다음과 같아서 클래스명을 HomeResponseDto로 설정
    private Long id;
    private String title;
    private String thumbnail;
    private Integer starCount;
}
