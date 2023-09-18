package service;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

public class GetTime implements MvcService {
  
  @Override
  public String exec(HttpServletRequest request) {
    request.setAttribute("now", LocalTime.now().toString());
    return "views/result.jsp";
  }

}
