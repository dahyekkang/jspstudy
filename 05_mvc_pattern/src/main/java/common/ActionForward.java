package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor    // argument가 없는 생성자
@AllArgsConstructor   // 모든 argument가 있는 생성자
@Data                 // Getter, Setter, toString
public class ActionForward {
  private String path;           // 어디로 갈 것인가?
  private boolean isRedirect;    // redirect는 true, forward는 false
  
}
