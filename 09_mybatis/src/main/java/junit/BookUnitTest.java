package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import domain.BookDto;
import repository.BookDao;


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
 *  
 * 3. JUnit4 단위 테스트 주의사항
 *  1) 영속 계층(Dao)을 테스트 한다.
 *  2) WAS(Tomcat)의 개입이 없으므로 WAS가 필요한 코드는 테스트 불가하다.(일반적인 방법으론 불가)
 *  3) 메소드 이름을 한글로 작성해도 상관없다.
 */

public class BookUnitTest {
  
  // Dao
  private BookDao dao = BookDao.getDao();   // singleton이 되어있기 때문에 new는 못 한다.

  // 테스트이지만 실제로 db에 삽입이 된다.
  //@Test
  public void 책_등록_테스트() {

    // 등록할 BookDto 생성
    BookDto dto = BookDto.builder()
                    .title("테스트제목")
                    .author("테스트저자")
                    .price(99999)
                    .build();
    
    // BookDto 등록
    int addResult = dao.bookAdd(dto);
    
    // 등록 결과 확인(Assert클래스가 가진 assert 메소드(Assert.은 작성하지 않음) : 확인용 메소드 (null체크, 값 체크, 배열 확인 가능))
    assertEquals(1, addResult);   // 값을 확인할 때 사용하는 메소드 : assertEquals() / 기대값 : 1, 실제로 나온 값 : addResult
    
  }

  //@Test
  public void 책_조회_테스트() {
    
    // 조회할 책 번호
    int bookNo = 1;
    
    // 조회
    BookDto dto = dao.bookDetail(bookNo);
    
    // 조회 결과 확인
    assertNotNull(dto);
    
  }
  
  //@Test
  public void 책_목록_테스트() {
    
    // begin, end를 가진 Map 생성
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", 1);
    map.put("end", 20);
    
    // 목록 가져오기
    List<BookDto> list = dao.bookList(map);
    
    // 결과 확인
    assertEquals(1, list.size());
    
  }
  
  //@Test
  public void 책_수정_테스트() {
    
    // 수정할 BookDto 생성
    BookDto dto = BookDto.builder()
                    .bookNo(1)
                    .title("[수정]제목")
                    .author("[수정]저자")
                    .price(-99999)
                    .build();
    
    // 수정
    int modifyResult = dao.bookModify(dto);
    
    // 결과 확인
    assertEquals(1, modifyResult);
    
  }

  @Test
  public void 책_삭제_테스트() {
    
    // 삭제할 책 번호
    int bookNo = 1;
    
    // 삭제
    int deleteResult = dao.bookDelete(bookNo);
    
    // 결과 확인
    assertEquals(1, deleteResult);
    
  }

}
