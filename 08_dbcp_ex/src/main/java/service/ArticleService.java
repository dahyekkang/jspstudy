package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface ArticleService {
  public ActionForward add(HttpServletRequest request);
  public ActionForward list(HttpServletRequest request);
  public ActionForward detail(HttpServletRequest request);
  public ActionForward edit(HttpServletRequest request);
  public ActionForward modify(HttpServletRequest request);
}
