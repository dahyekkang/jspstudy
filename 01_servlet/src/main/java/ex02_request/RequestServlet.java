package ex02_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/request")   // 나를 부르려면 이 맵핑을 보고 따라오세요.
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // HttpServletRequest request : 요청을 처리
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());  // 서버가 사용자에게 문자(태그)를 보낸다. Served at: /servlet를 사용자에게 보여주겠다.
		
		// 1. 요청을 UTF-8로 인코딩한다.
		request.setCharacterEncoding("UTF-8");
		
		// 2. 요청 파라미터를 받는다. (요청 파라미터는 모두 String 타입이다.)
		//  1) 요청 파라미터는 모두 String 타입이다.
		//  2) 요청 파라미터가 없으면 null 값으로 처리된다.
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		
		// 3. 요청 파라미터의 타입을 조정한다.
		//   타입을 조정할 때 Exception이 발생하지 않도록 요청 파라미터의 [null 처리 + 빈 문자열 처리] 가 필요하다.
		
		// 1) 고전 null 처리
		/*
		int age = 0;
		
		if(strAge != null) {
		age = Integer.parseInt(strAge);   // age(파라미터)가 없으면, 여기서 오류가 난다.		
		}
		 */
		
		// 2) null 처리를 위한 java.util.Optional 클래스 (어떤 값이 있는데, 그걸 wrapping하는 작업이 Optional이다. 감쌀 때, null일 때 대신 쓸 값을 같이 감싼다.)
		/*
		Optional<String> opt = Optional.ofNullable(strAge); // strAge가 null이면,
		int age = Integer.parseInt(opt.orElse("0"));        // opt로 감싼 strAge가 null이면(없으면) strAge 대신 "0"을 꺼낸다.
		*/
		
		// null 처리 + 빈 문자열 처리
		int age = 0;
		if(strAge != null && !strAge.isEmpty()) {
		  age = Integer.parseInt(strAge);
		}
		
		System.out.println(name + ", " + age);
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // TODO Auto-generated method stub
	  response.getWriter().append("Served at: ").append(request.getContextPath());  // 서버가 사용자에게 문자(태그)를 보낸다. Served at: /servlet를 사용자에게 보여주겠다.
	  
	  // 1. 요청을 UTF-8로 인코딩한다.
	  request.setCharacterEncoding("UTF-8");
	  
	  // 2. 동일한 요청 파라미터가 2개 이상 전달되는 경우 getParameterValues() 메소드를 이용해서 파라미터들을 배열로 받는다.
	  String[] tel = request.getParameterValues("tel");            // 선택한 값, 직접 입력한 값 2개 전부 배열로 들어간다.
	  String[] hobbies = request.getParameterValues("hobbies");    // 선택(체크)한 값이 모두 배열로 들어간다.
	  
	  System.out.println(Arrays.toString(tel));
	  System.out.println(Arrays.toString(hobbies));
	  
	}

}
