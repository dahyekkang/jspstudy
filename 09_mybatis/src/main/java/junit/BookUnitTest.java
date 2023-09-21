package junit;

import static org.junit.Assert.*;


/*
 * 1. JUnit4 단위 테스트 수행 방법
 *  1) JUnit Test Case 파일 추가
 *  2) @Test annotation이 추가된 테스트 메소드 작성
 *  3) [Run As] - [JUnit Test]로 실행
 *  
 * 2. JUnit4 단위 테스트 주요 annotation
 *  1) @Test      : 실제 테스트 수행
 *  2) @Before    : @Test 이전에 수행
 *  3) @BeforeAll : JUnit Test Case(BookUnitTest.java) 수행 이전, static 필수
 *  4) @After     : @Test 이후에 수행
 *  5) @AfterAll  : @JUnit Test Case(BookUnitTest.java) 수행 이후, static 필수
 */

import org.junit.Test;

public class BookUnitTest {

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
