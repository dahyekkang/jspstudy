package ex04_el;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor  // argument가 없는 생성자
@AllArgsConstructor // 모든 argument가 있는 생성자
@Getter
@Setter
public class Person {
  private String name;
  private int age;
}
