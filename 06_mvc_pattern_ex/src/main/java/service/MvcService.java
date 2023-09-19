package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface MvcService {
  public ActionForward getAge(HttpServletRequest request);
}
