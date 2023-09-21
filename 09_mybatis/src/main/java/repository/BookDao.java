package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BookDto;

public class BookDao {

  // mybatis에서 사용하는 SqlSession을 만들 수 있는 SqlSessionFactory 선언
  private SqlSessionFactory factory;
  
  
  // Singleton Pattern
  private static BookDao dao = new BookDao();
  private BookDao() {  // 외부에서는 BookDao() 호출 불가능
    // SqlSessionFactory 생성
    try {
      String resource = "mybatis/config/mybatis-config.xml";
      InputStream in = Resources.getResourceAsStream(resource);   // mybatis에서 xml읽는 방법
      factory = new SqlSessionFactoryBuilder().build(in);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static BookDao getDao() {
    return dao;
  }
  
  // 매퍼의 namespace
  private final String NS = "mybatis.mapper.book.";
  
  // 전체 개수 반환 메소드(목록 반환 시 필요)
  public int bookCount() {
    SqlSession ss =  factory.openSession();  // 모든 메소드가 이거부터 한다. 공장을 세웠고, 공장에서 꺼내온다.
    // 쿼리문에 해당하는 insert, delete, update을 호출하면 된다.
    // select일 때에는 한 줄이면 ss.selectOne(), 여러 줄이면 ss.selectList()
    int count = ss.selectOne(NS + "bookCount");
    // SqlSession이라고 하는 게, connection, preparestatement, resultset을 다 사용을 한다. 그래서 close해주어야 한다!
    ss.close();
    return count;
  }
  
  // 목록 반환 메소드
  public List<BookDto> bookList(Map<String, Object> map) {
    // select쿼리를 부를 메소드 이름으로!
    SqlSession ss = factory.openSession();
    List<BookDto> list = ss.selectList(NS + "bookList", map);
    // map을 book.xml에 전달(아이디 뒤에 적으면 된다 (, map)) 이 맵은 서비스로부터 받아올 것임.
    ss.close();
    return list;
  }
  
  
  // 상세 반환 메소드
  public BookDto bookDetail(int bookNo) {
    SqlSession ss = factory.openSession();
    BookDto dto = ss.selectOne(NS + "bookDetail", bookNo);
    ss.close();
    return dto;
  }
  
  
  // 등록 메소드
  public int bookAdd(BookDto dto) {
    // insert, update, delete는 auto commit처리 결정을 하는 메소드를 사용해야한다.
    SqlSession ss = factory.openSession(false); // false : 내가 커밋하겠다.
    int addResult = ss.insert(NS + "bookAdd", dto);
    if(addResult == 1) {
      ss.commit();
    }
    ss.close();
    return addResult;
  }
  
  // 수정 메소드
  public int bookModify(BookDto dto) {
    SqlSession ss = factory.openSession(false);
    int modifyResult = ss.update(NS + "bookModify", dto);
    if(modifyResult == 1) {
      ss.commit();
    }
    ss.close();
    return modifyResult;
  }
  
  // 삭제 메소드
  public int bookDelete(int bookNo) {
    SqlSession ss = factory.openSession(false);
    int deleteResult = ss.delete(NS + "bookDelete", bookNo);
    if(deleteResult == 1) {
      ss.commit();
    }
    ss.close();
    return deleteResult;
  }
  
}
