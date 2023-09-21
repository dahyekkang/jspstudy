package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface ArticleService {
  
  public ActionForward addArticle(HttpServletRequest request);        // action으로 할거여서 response 필요없다!
  public ActionForward getArticleList(HttpServletRequest request);    // 페이지 정보가 필요하다. 요청 넘겨줘야 하므로 request 필요하다.
  public ActionForward getArticleByNo(HttpServletRequest request);
  public ActionForward editArticle(HttpServletRequest request);
  public ActionForward modifyArticle(HttpServletRequest request);
  public ActionForward deleteArticle(HttpServletRequest request);
  public ActionForward plustHit(HttpServletRequest request);
  
}
