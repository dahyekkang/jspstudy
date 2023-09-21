package domain;
// 일반적으로 dto를 담는 패키지는 domain / dto / vo 중 선택해서 사용한다.
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder    // lombok.jar 안에 있는 annotation으로, 클래스 안에 클래스 넣어서 처리할 수 있게 해준다.
public class ArticleDto {
  private int article_no;
  private String title;
  private String content;
  private String editor;
  private int hit;
  private Date lastmodified;
  private Date created; 
}
