package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.ArticleDto;
import repository.ArticleDao;
import util.PageVo;

public class ArticleServiceImpl implements ArticleService {
  
  // 모든 서비스가 공동으로 사용하는 BoardDao, PageVo 객체 가져오기
  private ArticleDao dao = ArticleDao.getDao();
  private PageVo pageVo = new PageVo();

  @Override
  public ActionForward addArticle(HttpServletRequest request) {

    // 등록할 제목과 내용
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String editor = request.getParameter("editor");
    
    // 제목 + 내용 -> BoardDto 객체로 생성
    ArticleDto dto = ArticleDto.builder()
                       .title(title)
                       .content(content)
                       .editor(editor)
                       .build();
    
    // 확인
    // System.out.println(dto);
    
    // BoardDao의 register 메소드 호출
    int registerResult = dao.addArticle(dto);
    
    // 등록 성공(registerResult == 1), 등록 실패(registerResult == 0)
    String path = null;
    if(registerResult == 1) {
      path = request.getContextPath() + "/getArticleList.do";
    } else if(registerResult == 0) {
      path = request.getContextPath() + "/index.do";
    }
    
    // 어디로 어떻게 이동하는지 반환 (insert 수행 후에는 반드시 redirect로 이동한다.)
    return new ActionForward(path, true);
  }
  
  @Override
  public ActionForward getArticleList(HttpServletRequest request) {

    /* page, total, display 정보가 있어야 목록을 가져올 수 있다. */
    
    // 전달된 페이지 번호 (페이지 번호의 전달이 없으면 1 페이지를 연다. -> 최초 오픈 시 page번호가 주소창에 없다.)
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));  // null이 불가능할 땐 of, null이 가능할 땐 ofNullable
    int page = Integer.parseInt(opt.orElse("1"));       // 파라미터가 있으면 있는 걸 쓰고 없으면 이걸("1") 쓰겠다.
    
    int total = dao.getArticleCount();    // database 갔다 올 dao가 필요함. select count(*) from board_t;해야 한다. 이 sql문을 수행하는 dao의 메소드(getBoardCount)를 만들어줘야한다.
    
    int display = 5;  // 파라미터를 받진 않았는데 임의로 결정하겠다. 고정 값 사용(원하면 파라미터로 받아 오는 것으로 변경도 가능함)
    
    // PageVo의 모든 정보(필드들) 계산하기
    pageVo.setPaging(page, total, display);
    
    // 확인해보기 
    // System.out.println(pageVo);
    
    // 게시글 목록을 가져올 때 사용할 변수들을 Map으로 만듦
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageVo.getBegin());
    map.put("end", pageVo.getEnd());
    
    // DB로부터 게시글 목록 가져오기
    List<ArticleDto> articleList = dao.getArticleList(map);
    
    // 게시글 목록을 /board/list.jsp로 전달하기 위해 request에 저장한 뒤 forward 한다.
    request.setAttribute("articleList", articleList);
    request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/getArticleList.do"));
    return new ActionForward("/getArticleList.jsp", false);
    
  }
  
  @Override
  public ActionForward getArticleByNo(HttpServletRequest request) {
    
    // 상세조회할 게시글 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));     // 예외 회피! : 혹시 번호가 전달 안 되면 0번을 사용 -> 조회 X
    
    // DB로부터 게시글 가져오기
    ArticleDto article = dao.getArticleByNo(article_no);
    
    // 게시글을 /board/detail.jsp에 전달하기 위해서 forward 처리
    request.setAttribute("article", article);
    return new ActionForward("/getArticleDetail.jsp", false);
  }
  
  @Override
  public ActionForward editArticle(HttpServletRequest request) {
    
    // 편집할 게시글 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));     // 예외 회피 : 혹시 번호가 전달 안 되면 0번을 사용
    
    // DB로부터 게시글 가져오기
    ArticleDto article = dao.getArticleByNo(article_no);
    
    // 게시글을 /board/edit.jsp에 전달하기 위해서 forward 처리
    request.setAttribute("article", article);
    return new ActionForward("/editArticle.jsp", false);
  }
  
  @Override
  public ActionForward modifyArticle(HttpServletRequest request) {

    // 수정할 게시글 정보
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    int article_no = Integer.parseInt(request.getParameter("article_no"));
    
    // 수정할 게시글 정보를 BoardDto 객체로 생성 (db로 보낼 정보가 2개 이상이면 객체 아니면 map 사용)
    ArticleDto dto = ArticleDto.builder()
                    .title(title)
                    .content(content)
                    .article_no(article_no)
                    .build();
    
    // 위 dto를 수정할 dao에 전달할 것이다.
    
    // 수정하기
    int modifyResult = dao.modifyArticle(dto);
    
    // 수정 성공(modifyResult == 1), 수정 실패(modifyResult == 0)
    String path = null;
    if(modifyResult == 1) {   // 성공하면 상세보기로 갈 것이다. board_no가 있어야 한다!
      path = request.getContextPath() + "/getArticleDetail.do?article_no=" + article_no;
    } else {
      path = request.getContextPath() + "/index.do";
    }
    
    // update 이후에는 redirect 사용한다!!!
    return new ActionForward(path, true);
  }
  
  @Override
  public ActionForward deleteArticle(HttpServletRequest request) {
    
    // 삭제할 게시글 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));     // 예외 회피 : 혹시 번호가 전달 안 되면 0번을 사용
    
    // 삭제하기
    int deleteResult = dao.deleteArticle(article_no);
    
    // 삭제 성공(deleteResult == 1), 삭제 실패(deleteResult == 0)
    String path = null;
    if(deleteResult == 1) {
      path = request.getContextPath() + "/getArticleList.do";
    } else if(deleteResult == 0) {
      path = request.getContextPath() + "/index.do";
    }
    
    // delete 이후에는 redirect 한다.
    return new ActionForward(path, true);
  }
  
  @Override
  public ActionForward plustHit(HttpServletRequest request) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));
    
    int hit = dao.plusHit(article_no);
    
    String path = null;
    
    
    return new ActionForward(path, true);
  }
  
}
