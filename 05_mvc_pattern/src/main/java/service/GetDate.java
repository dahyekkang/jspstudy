package service;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

public class GetDate implements MvcService {

  @Override
  public String exec(HttpServletRequest request) {
    request.setAttribute("today", LocalDate.now().toString());
    return "views/result.jsp";
  }
  
}
