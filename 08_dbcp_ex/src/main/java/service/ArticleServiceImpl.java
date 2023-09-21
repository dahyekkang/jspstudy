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

  private ArticleDao dao = ArticleDao.getDao();
  private PageVo pageVo = new PageVo();

  @Override
  public ActionForward add(HttpServletRequest request) {
    
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String editor = request.getParameter("editor");
    
    ArticleDto dto = ArticleDto.builder()
                      .title(title)
                      .content(content)
                      .editor(editor)
                      .build();
    
    int addResult = dao.articleAdd(dto);
    
    String path = null;
    if(addResult == 1) {
      path = request.getContextPath() + "/getArticleList.do";
    } else if(addResult == 0) {
      path = request.getContextPath() + "/index.do";
    }
    
    return new ActionForward(path, true);
    
  }

  @Override
  public ActionForward list(HttpServletRequest request) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    int total = dao.articleCount();
    
    int display = 5;
    
    pageVo.setPaging(page, total, display);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageVo.getBegin());
    map.put("end", pageVo.getEnd());
    
    List<ArticleDto> articleList = dao.articleList(map);
    
    request.setAttribute("articleList", articleList);
    request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/getArticleList.do"));
    return new ActionForward("/article/list.jsp", false);
    
  }
  
  @Override
  public ActionForward detail(HttpServletRequest request) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));
    
    ArticleDto article = dao.articleDetail(article_no);
    
    request.setAttribute("article", article);
    return new ActionForward("/article/detail.jsp", false);
    
  }

  @Override
  public ActionForward edit(HttpServletRequest request) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));
    
    ArticleDto article = dao.articleDetail(article_no);
    
    request.setAttribute("article", article);
    return new ActionForward("/article/edit.jsp", false);
    
  }
  
  @Override
  public ActionForward modify(HttpServletRequest request) {
    
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    int article_no = Integer.parseInt(request.getParameter("article_no"));
    
    ArticleDto dto = ArticleDto.builder()
                    .title(title)
                    .content(content)
                    .article_no(article_no)
                    .build();
    
    int modifyResult = dao.articleModify(dto);
    
    String path = null;
    if(modifyResult == 1) {
      path = request.getContextPath() + "/getArticleDetail.do?article_no=" + article_no;
    } else {
      path = request.getContextPath() + "/index.do";
    }
    
    return new ActionForward(path, true);
    
  }
   
}
