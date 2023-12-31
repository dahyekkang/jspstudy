package ex08_Cookie;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie2
 */
@WebServlet("/cookie2")
public class Cookie2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookie2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // 요청하는 곳의 모든 쿠키 가져오기 (골라서 가져올 수 없다.)
	  Cookie[] cookies = request.getCookies();
	  
	  // 쿠키 확인하기
	  if(cookies != null) {
	    for(int i = 0; i < cookies.length; i++) {
	      // 쿠키이름
	      String name = cookies[i].getName();
	      // 쿠키값
	      String value = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
	      // 쿠키경로
	      String path = cookies[i].getPath();
	      // 쿠키 유지 시간
	      int expire = cookies[i].getMaxAge();
	      
	      System.out.println(name + ", " + value + ", " + path + ", " + expire);
	      
	      // age는 cookie1에 저장했기 때문에 위에서 age는 보이지 않는다.
	      
	    }
	  }
	  
	  // 쿠키 삭제하기 (같은 이름의 쿠키를 만든 뒤, 쿠키 유지시간을 0으로 설정하고 저장한다.(덮어쓰기한다.))
	  Cookie cookie = new Cookie("name", "값은아무의미가없다");
	  cookie.setMaxAge(0);
	  response.addCookie(cookie);
	  
	  // jsessionID : 브라우저 켤 때마다 session이 만들어지는데 자동으로 저장해두는 것임
	  // 그 값을 알고있는 사람은 그 작업을 수행했던 사람이다. -> 자동 로그인 처리
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
