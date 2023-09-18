package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetDate;
import service.GetTime;
import service.MvcService;

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

	  request.setCharacterEncoding("UTF-8");
	  
	  String requestURI = request.getRequestURI();                       /*    /mvc/getDate.do   */
	  String contextPath = request.getContextPath();                     /*    /mvc              */
	  String urlMapping = requestURI.substring(contextPath.length());    /*    /getDate.do       */
	  
	  MvcService mvcService = null;
	  
	  switch(urlMapping) {
	  case "/getDate.do":
	    mvcService = new GetDate();
	    break;
	  case "/getTime.do":
	    mvcService = new GetTime();
	    break;
	  }
	  
	  String path = mvcService.exec(request);
	  request.getRequestDispatcher(path).forward(request, response);
	  
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
