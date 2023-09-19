package service;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public class MvcServiceImpl implements MvcService {

  @Override
  public ActionForward getAge(HttpServletRequest request) {

    String year = request.getParameter("year");
    int iYear = Integer.parseInt(year);
    int nowYear = LocalDate.now().getYear();
    
    request.setAttribute("age", nowYear - iYear);
    
    return new ActionForward("views/age.jsp", false); // views 폴더 내 age.jsp로 forward로 간다.
  }

}
