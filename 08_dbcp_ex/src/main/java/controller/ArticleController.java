package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.ArticleService;
import service.ArticleServiceImpl;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // 요청 인코딩(ArticleFilter가 수행함) + 응답 타입과 인코딩
    // request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    
    // 요청 주소 확인
    String requestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlMapping = requestURI.substring(contextPath.length());
	
    // 어디로 어떻게 이동할 것인지 알고 있는 ActionForward 객체
    ActionForward af = null;
    
    // ArticleService 객체 생성
    ArticleService articleService = new ArticleServiceImpl();
    
    switch(urlMapping) {
    case "/writeArticle.do":
      af = new ActionForward("/writeArticle.jsp", false);
      break;
    case "/index.do":
      af = new ActionForward("/index.jsp", false);
      break;
    case "/addArticle.do":
      af = articleService.addArticle(request);
      break;
    case "/getArticleList.do":
      af = articleService.getArticleList(request);
      break;
    case "/getArticleDetail.do":
      af = articleService.getArticleByNo(request);
      break;
    case "/editArticle.do":
      af = articleService.editArticle(request);
      break;
    case "/modifyArticle.do":
      af = articleService.modifyArticle(request);
      break;
    case "/deleteArticle.do":
      af = articleService.deleteArticle(request);
      break;
    case "/plusHit.do":
      af = articleService.plustHit(request);
      break;
    }
    
    // 이동
    if(af != null) {
      if(af.isRedirect()) {
        response.sendRedirect(af.getPath());
      } else {
        request.getRequestDispatcher(af.getPath()).forward(request, response);
      }
    }
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
