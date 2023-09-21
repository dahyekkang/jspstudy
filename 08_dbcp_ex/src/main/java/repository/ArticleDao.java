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

import domain.ArticleDto;

public class ArticleDao {

  // 모든 메소드가 공동으로 사용할 객체 선언
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  // Connection Pool 관리 DataSource 객체 선언
  private DataSource dataSource;
  
  // Singleton Pattern으로 ArticleDao 객체 생성
  private static ArticleDao dao = new ArticleDao();
  private ArticleDao() {
    // META-INF/context.xml에 있는 <Resource name="jdbc/oraclexe" /> 태그 내용을 읽어서 DataSource 객체 생성하기
    try {
      Context context = new InitialContext();
      Context env = (Context)context.lookup("java:comp/env");
      dataSource = (DataSource)env.lookup("jdbc/oraclexe");
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static ArticleDao getDao() {
    return dao;
  }
  
  // 자원 반납 메소드
  public void close() {
    try {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(con != null) con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  // 기사 등록 메소드
  public int articleAdd(ArticleDto dto) {
    
    int insertResult = 0;
    
    try {
            
      con = dataSource.getConnection();
      String sql = "INSERT INTO ARTICLE_T(ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED) VALUES (ARTICLE_SEQ.NEXTVAL, ?, ?, ?, 0, SYSDATE, SYSDATE)";
      ps = con.prepareStatement(sql);
      ps.setString(1, dto.getTitle());
      ps.setString(2, dto.getContent());
      ps.setString(3, dto.getEditor());
      insertResult = ps.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    return insertResult;
    
  }
  
  // 기사 개수 반환 메소드
  public int articleCount() {
    
    int count = 0;
    
    try {
      
      con = dataSource.getConnection();
      String sql = "SELECT COUNT(*) AS CNT FROM ARTICLE_T";  //    CNT
                                                             //  -------
                                                             //    120
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next()) {
        count = rs.getInt(1);  // count = rs.getInt("CNT")도 가능함
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    return count;
    
  }
  
  // 기사 목록 반환 메소드
  public List<ArticleDto> articleList(Map<String, Object> map) {
    
    List<ArticleDto> list = new ArrayList<ArticleDto>();
    
    try {
      
      con = dataSource.getConnection();
      String sql = "SELECT A.ARTICLE_NO, A.TITLE, A.CONTENT, A.EDITOR, A.HIT, A.LASTMODIFIED, A.CREATED"
                 + "  FROM (SELECT ROW_NUMBER() OVER (ORDER BY ARTICLE_NO DESC) AS RN, ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED"
                 + "          FROM ARTICLE_T) A"
                 + " WHERE A.RN BETWEEN ? AND ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, (int)map.get("begin"));
      ps.setInt(2, (int)map.get("end"));
      rs = ps.executeQuery();
      while(rs.next()) {
        ArticleDto dto = ArticleDto.builder()
                          .article_no(rs.getInt(1))
                          .title(rs.getString(2))
                          .content(rs.getString(3))
                          .editor(rs.getString(4))
                          .hit(rs.getInt(5))
                          .lastmodified(rs.getDate(6))
                          .created(rs.getDate(7))
                          .build();
        list.add(dto);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    return list;
    
  }
  
  // 기사 반환 메소드
  public ArticleDto articleDetail(int article_no) {
    
    ArticleDto dto = null;
    
    try {
      
      con = dataSource.getConnection();
      String sql = "SELECT ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED"
                 + "  FROM ARTICLE_T"
                 + " WHERE ARTICLE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, article_no);
      rs = ps.executeQuery();
      if(rs.next()) {
        dto = ArticleDto.builder()
                .article_no(rs.getInt(1))
                .title(rs.getString(2))
                .content(rs.getString(3))
                .editor(rs.getString(4))
                .hit(rs.getInt(5))
                .lastmodified(rs.getDate(6))
                .created(rs.getDate(7))
                .build();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    return dto;
    
  }
  
  // 기사 수정 메소드
  public int articleModify(ArticleDto dto) {
    
    int modifyResult = 0;
    
    try {
      
      con = dataSource.getConnection();
      String sql = "UPDATE ARTICLE_T"
                 + "   SET TITLE = ?, CONTENT = ?, LASTMODIFIED = SYSDATE"
                 + " WHERE ARTICLE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, dto.getTitle());
      ps.setString(2, dto.getContent());
      ps.setInt(3, dto.getArticle_no());
      modifyResult = ps.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    return modifyResult;
    
  }
  
}
