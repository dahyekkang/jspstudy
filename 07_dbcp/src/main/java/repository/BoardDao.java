package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.BoardDto;

// db에 왔다갔다 하는 애들(dao)은 singleton으로 제작해야 한다!!!!!!!!!! dao만 db갈 수 있음!

public class BoardDao {
  
  // 모든 메소드가 공동으로 사용할 객체 선언
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  // Connection Pool 관리 DataSource 객체 선언
  private DataSource dataSource;
  
  // singleton pattern으로 BoardDao 객체 생성
  // 일단 만들고, getter로 반환!
  private static BoardDao dao = new BoardDao();
  private BoardDao() {
    // META-INF/context.xml에 있는 <Resource name="jdbc/oraclexe" /> 태그 내용을 읽어서 DataSource 객체 생성하기
    try {
      // context내용을 읽어주는 클래스 : Context
      Context context = new InitialContext();
      Context env = (Context)context.lookup("java:comp/env");
      // lookup 함수의 파라미터는 context.xml에 설정된 name과 동일해야 한다.  
      dataSource = (DataSource)env.lookup("jdbc/oraclexe");
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static BoardDao getDao() {
    return dao;
  }
  // 다른 클래스에서 getDao를 부르려면 객체가 있어야 한다. private BoardDao(){} 때문에 객체 생성은 불가 -> 클래스로 불러야 한다!
  // private BoardDao dao, public BoardDao getDao() 모두 -> static으로 바꿔주어야 한다.

  // 자원 반납 메소드
  public void close() {
    try {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(con != null) con.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  // 게시글 등록 메소드
  public int register(BoardDto dto) {
    
    // 등록 결과 (insert 실행 결과는 삽입된 행의 개수이다.)
    int insertResult = 0;
    
    try {
      
      // Connection Pool에서 Connection을 하나 받아온다.
      // Connection Pool 관리는 DataSource 객체가 수행한다.
      
      con = dataSource.getConnection();
      
      // 쿼리문 작성
      String sql = "INSERT INTO BOARD_T(BOARD_NO, TITLE, CONTENT, MODIFIED_AT, CREATED_AT) VALUES (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, SYSDATE)";
      
      // ps 객체 생성 (쿼리문 실행을 담당하는 객체)
      ps = con.prepareStatement(sql);
      
      // 쿼리문의 변수(?로 처리된 부분)에 값을 전달
      ps.setString(1, dto.getTitle());    // 1번째 물음표(?)에 dto.getTitle() 전달하기
      ps.setString(2, dto.getContent()); // 2번째 물음표(?)에 dto.getContent() 전달하기
      
      // 쿼리문의 실행
      insertResult = ps.executeUpdate();
      
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    // 등록 결과 반환
    return insertResult;

  }
  
  // 게시글 개수 반환 메소드
  public int getBoardCount() {
    
    // 게시글 개수
    int count = 0;
    
    try {
      
      con = dataSource.getConnection();
      String sql = "SELECT COUNT(*) FROM BOARD_T";  // COUNT(*)
                                                    // --------
                                                    //   120
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next()) {   // 집계함수(COUNT)를 썼기 때문에 행이 1개만 나온다. 1행의 데이터를 조회하기 위해 rs.next()를 1번만 사용하는데 혹시 없을 수도 있으니까 if문 사용
        count = rs.getInt(1); // count = rs.getInt("COUNT(*)")도 가능함
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    // 게시글 개수 반환
    return count;
    
  }
  
  // 게시글 목록 반환 메소드 (게시글 하나는 BoardDto에 저장, 게시글 목록은 List<BoardDto>로 하면 된다.
  public List<BoardDto> getBoardList(Map<String, Object> map){    // 서비스에서 저장한 map을 받아온다. (begin과 end가 들어있다.)
    
    // 게시글 목록 저장 List
    List<BoardDto> list = new ArrayList<BoardDto>();
    
    try {
      
      con = dataSource.getConnection();
      String sql = "SELECT A.BOARD_NO, A.TITLE, A.CONTENT, A.MODIFIED_AT, A.CREATED_AT"
                 + "  FROM (SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) AS RN, BOARD_NO, TITLE, CONTENT, MODIFIED_AT, CREATED_AT"
                 + "          FROM BOARD_T) A"
                 + " WHERE A.RN BETWEEN ? AND ?";   // sql문 사용 시, 줄바꿀 때 사이에 공백이 있는지 잘 확인하기.
      ps = con.prepareStatement(sql);
      ps.setInt(1, (int)map.get("begin"));    // 첫 번째 물음표에 map 안에 begin을 꺼내서 int로 바꾼 값을 쿼리문으로 전달해라
      ps.setInt(2, (int)map.get("end"));
      rs = ps.executeQuery();
      while(rs.next()) {    // next를 여러번 호출해야 한다. 5개 정보를 rs 포인터가 가리킬 수 있는 것이다. select, from 사이의 5개 컬럼 이름을 쓰거나, 번호로.
        // rs -> BoardDto로 바꾸는 작업을 해야 한다. rs 커서값 getint, getString, getDate
        BoardDto dto = BoardDto.builder()
                        .board_no(rs.getInt(1))     // 1열
                        .title(rs.getString(2))     // 2열
                        .content(rs.getString(3))   // 3열
                        .modified_at(rs.getDate(4)) // 4열
                        .created_at(rs.getDate(5))  // 5열
                        .build();
        // BoardDto -> list 추가
        list.add(dto);
      }
      
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
   
    // 게시글 목록 반환
    return list;
  }

  // 게시글 반환 메소드
  public BoardDto getBoardByNo(int board_no) {
    
    // 게시글
    BoardDto dto = null;
    
    try {
      
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_AT, CREATED_AT"
                 + "  FROM BOARD_T"
                 + " WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      rs = ps.executeQuery();
      if(rs.next()) {   // BOARD_NO이 PK이므로 1행 또는 0행 나온다. rs.next() 한 번만 하면 된다.
        // 검색 결과가 없으면 dto = null, 있으면 dto만들기
        dto = BoardDto.builder()
            .board_no(rs.getInt(1))     // 1열
            .title(rs.getString(2))     // 2열
            .content(rs.getString(3))   // 3열
            .modified_at(rs.getDate(4)) // 4열
            .created_at(rs.getDate(5))  // 5열
            .build();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    // 게시글 반환
    return dto;
    
  }
  
  // 게시글 수정 메소드 (수정된 게 있으면 1, 수정된 게 없으면(업데이트 쿼리문이 수행되지 않았다) 0)
  public int modify(BoardDto dto) {
    
    // 수정 결과
    int modifyResult = 0;
    
    try {
      
      con = dataSource.getConnection();
      String sql = "UPDATE BOARD_T"
                 + "   SET TITLE = ?, CONTENT = ?, MODIFIED_AT = SYSDATE"
                 + " WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, dto.getTitle());
      ps.setString(2, dto.getContent());
      ps.setInt(3, dto.getBoard_no());
      
      modifyResult = ps.executeUpdate();    // update, insert할 때는 executeQuery가 아닌 executeUpdate !
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    // 수정 결과 반환
    return modifyResult;
    
  }
  
  
  // 게시글 삭제 메소드
  public int delete(int board_no) {
    
    // 삭제 결과
    int deleteResult = 0;
    
    try {
      
      con = dataSource.getConnection();
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO = ?";
      
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      deleteResult = ps.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    // 삭제 결과 반환
    return deleteResult;
    
  }
  
  
}
