package ex07_binding;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Binding1
 */
@WebServlet("/binding1")
public class Binding1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Binding1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // 서버 저장소 ! ! !
	  
	  /*
	   * binding
	   * 
	   * 1. 값을 저장할 수 있는 영역에 속성(Attribute)의 형태로 값을 저장하는 것을 의미한다.
	   * 2. HTML 태그의 속성(Attribute)과는 상관없는 개념이다.
	   * 3. 서버가 값을 저장할 영역을 제공한다.
	   * 영역이라고 얘기하고 있지만 클래스 얘기다.
	   * 4. 저장 영역
	   *   1) ServletContext     : 컨텍스트(프로젝트, 애플리케이션) 종료 전까지 값을 저장할 수 있다. 애플리케이션이 실행중이라면 어디서든 값에 접근할 수 있다.
	   *   2) HttpSession        : 세션 종료 전까지 값을 저장할 수 있다. 웹 브라우저가 실행중이라면 어디서든 값에 접근할 수 있다. (웹 브라우저를 닫으면 정보 소멸)(브라우저 열면 Session 생성, 닫으면 소멸. 사이트 이동은 상관없다. 닫지만 않으면 됨 ex로그인 정보)
	   *   3) HttpServletRequest : 요청 종료 전까지 값을 저장할 수 있다. 응답 전이라면 값에 접근할 수 있다. (1회용 정보) 1번 보내는 용도로 사용.
	   * 5. 속성(Attribute) 처리 메소드
	   *   1) setAttribute("속성", 값) : Object 타입으로 값을 저장한다.    값 : String, int, long, double, boolean, ... -> Object
	   *   2) getAttribute("속성")     : Object 타입의 값을 반환한다. 캐스팅이 필요할 수 있다.
	   *   3) removeAttribute("속성")  : 값을 제거한다.
	   */

	  // 한 번 전달하면 되는 건 ServletContext 사용, 나머지는 Session이나 ServletRequest 사용.
	  
	  // ServletContext 영역에 값을 저장하기
	  ServletContext application = request.getServletContext();
	  application.setAttribute("msg", "ServletContext");
	  
	  // HttpSession 영역에 값을 저장하기
	  HttpSession session = request.getSession();
	  session.setAttribute("msg", "HttpSession");
	  
	  // HttpServletRequest 영역에 값을 저장하기 (이미 매개변수에 선언되어 있다.)
	  request.setAttribute("msg", "HttpServletRequest");
	  
	  // Binding2 서블릿으로 이동 (HttpServletRequest의 전달이 가능한 forward를 이용)
	  // (forward or redirect가 있는데 ServletContext랑 HttpSession은 상관없는데 HttpServletRequest에 저장돼있는 걸 유지시키면서 이동하는 건 forward이므로 forward 사용)
	  
	  // 요청의 전달 객체(request.getRequestDispatcher("/binding2")) 전달 (forward의 특징 : 서버 내부로의 이동이기 때문에 /servlet이라는 ContextPath는 적지 않는다.)
	  // forward 사용 (binding1 -> binding2)
	  request.getRequestDispatcher("/binding2").forward(request, response);
	  
	  
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
