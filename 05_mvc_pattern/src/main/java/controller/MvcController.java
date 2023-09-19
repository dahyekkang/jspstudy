package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.MvcService;
import service.MvcServiceImpl;

/**
 * Servlet implementation class MvcController
 */
@WebServlet("*.do")
public class MvcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MvcController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // 요청 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  // 응답의 타입과 인코딩
	  response.setContentType("text/html; charset=UTF-8");   // 여기서 해 놓으면 서비스마다 할 필요가 없다.
	  
	  // 요청 확인(URLMapping 확인) - 요청이 달라지면 주소가 달라지는 방식이므로.
	  String requestURI = request.getRequestURI();                       /*    /mvc/getDate.do   */
	  String contextPath = request.getContextPath();                     /*    /mvc              */
	  String urlMapping = requestURI.substring(contextPath.length());    /*    /getDate.do       */
	  
	  // 서비스 객체 생성(MVC Pattern에서 Model에 해당함)
	  MvcService mvcService = new MvcServiceImpl();
	  
	  // 서비스 실행 결과(어디로 어떻게 이동할 것인가에 관한 정보가 저장)
	  ActionForward af = null;
	  
	  // 요청에 따른 서비스 실행
	  switch(urlMapping) {
	  case "/getDate.do":
	    af = mvcService.getDate(request);
	    break;
	  case "/getTime.do":
	    af = mvcService.getTime(request);
	    break;
	  case "/getDatetime.do":
	    mvcService.getDatetime(request, response);   // getDatetime메소드 확인해보면 href가 직접적으로 들어가 있음(경고창에 보여줌)
	    break;
	  }
	  
	  // 서비스 실행 결과에 의한 이동 (redirect와 forward의 선택은 서비스에서 결정함)
	  if(af != null) {                 // switch문 이전에 af는 null이었기 때문에 확인해주는 것이 좋음
	    if(af.isRedirect()) {
	      response.sendRedirect(af.getPath());   // redirect
	    } else {
	      request.getRequestDispatcher(af.getPath()).forward(request, response);   // forward
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
