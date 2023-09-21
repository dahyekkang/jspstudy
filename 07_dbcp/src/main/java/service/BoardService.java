package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface BoardService {

  public ActionForward register(HttpServletRequest request);        // action으로 할거여서 response 필요없다!
  public ActionForward getBoardList(HttpServletRequest request);    // 페이지 정보가 필요하다. 요청 넘겨줘야 하므로 request 필요하다.
  public ActionForward getBoardByNo(HttpServletRequest request);
  public ActionForward edit(HttpServletRequest request);
  public ActionForward modify(HttpServletRequest request);
  public ActionForward delete(HttpServletRequest request);
}
