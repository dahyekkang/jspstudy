package ex08_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie1
 */
@WebServlet("/cookie1")
public class Cookie1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookie1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 서버가 가서 쿠키를 읽어보고 처리. 내가 만들어서 가지고 있든, 만들어놓은걸 가져오든.
    // 서버(서블릿)에서 쿠키를 만들고, 저장은 클라이언트에서
    // jQuery에서도 가능
	  
	  /*
	   * 쿠키
	   * 1. 클라이언트 측에 저장되는 정보를 의미한다.
	   * 2. 보안에는 취약하므로 개인 정보 같은 민감 정보는 저장하지 않는다.
	   */
	  
	  // 쿠키 만들기 (new Cookie("쿠키이름", "쿠키값"))
	  Cookie cookie1 = new Cookie("name", "홍길동");
	  Cookie cookie2 = new Cookie("age", "30");        // 모든 쿠키 값은 String 타입으로 저장한다. 
	  Cookie cookie3 = new Cookie("address", URLEncoder.encode("서울특별시 종로구", "UTF-8"));     // 공백은 유효하지 않은 문자이므로 공백이 들어간 값은 인코딩이 필요하다. 
	  
	  // 항상 모든 사이트에서 사용할 수 있는 건 아니다. 조건은 경로(주소). 특정 주소에 따라 쿠키값을 열 수 있게. 쿠키는 주소에 저장되는 것이다.

	  // 쿠키가 저장될 경로 설정하기 (생략하면 컨텍스트패스 경로에 저장된다.)
	  cookie1.setPath("/servlet");             // 컨텍스트패스 : request.getContextPath() -> 나중에는 변수값으로 바꾼다!   에 저장
	  cookie2.setPath("/servlet/cookie1");     // 서블릿경로   : request.getRequestURI()                                   에 저장
	                                           // cookie3은 경로 설정을 생략했으므로 컨텍스트패스에 저장된다.
	  
	  // 세션쿠키 : 브라우저를 닫을 때 지워진다.
	  // 쿠키가 유지되는 시간 설정하기 (생략하면 세션쿠키가 된다. 브라우저를 닫으면 지워진다. 초 단위.)
	  cookie1.setMaxAge(60 * 60);            // 1시간
	  cookie2.setMaxAge(60 * 60 * 24 * 7);   // 7일
	                                         // cookie3은 시간 설정을 생략했으므로 세션쿠키가 된다.
	  
	  // 쿠키 삭제 : 별도 메소드 없음. 쿠키 유지 시간을 0으로 설정하면 된다.
	  
	  // 쿠키를 브라우저에 저장하기 (응답으로 처리한다.)
	  response.addCookie(cookie1);
	  response.addCookie(cookie2);
	  response.addCookie(cookie3);
	  
	  // 확인 : F12 - Application - Cookies - 주소
	  
	  // Cookie2 서블릿으로 redirect 이동 (주소창에서 확인됨)
	  response.sendRedirect("/servlet/cookie2");
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
