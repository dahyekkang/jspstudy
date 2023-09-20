package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface BoardService {

  public ActionForward register(HttpServletRequest request);    // action으로 할거여서 response 필요없다!
  
}
