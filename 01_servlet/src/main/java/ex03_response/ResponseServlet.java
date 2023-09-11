package ex03_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 1. 요청 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  // 2. 요청 파라미터
	  String gender = request.getParameter("gender");
	  String domain = request.getParameter("domain");
	  String content = request.getParameter("content");
	  String region = request.getParameter("region");
	  
	  /*
	   * 응답 (Response)
	   * 1. 서버가 클라이언트에게 데이터를 보내는 것을 의미한다.
	   * 2. HttpServletResponse 클래스가 응답을 처리한다.
	   * 3. 응답할 데이터의 타입(MIME-TYPE)을 결정하고 응답한다.
	   *   주요 응답 TYPE(정해진 keyword)
	   *   1) text/html          : 태그를 만들어서 반환하는 경우(HTML로 전송 시)
	   *   2) application/xml    : XML을 반환하는 경우(XML데이터로 전송 시)
	   *   3) application/json   : JSON을 반환하는 경우(JSON데이터로 전송 시)
	   */
	  
	  // 3. 응답 데이터의 타입과 인코딩 설정
	  // response.setContentType("text/html");
	  // response.setCharacterEncoding("UTF-8");
	  // 위 두 줄을 아래의 한 줄로 하는 게 일반적임.
	  response.setContentType("text/html; charset=UTF-8");
	  
	  // 4. 응답 스트림 생성 (문자 기반 출력 스트림인 Writer 생성)
	  //   IOException 예외 처리가 필요하지만 doGet() 메소드가 IOException 처리를 이미 하고 있다.

	  // (서버 기준으로 봤을 때 출력 스트림이다. String이므로 byte기반 출력 스트림이 아닌, 문자 기반 출력 스트림(Writer) 사용)
	  // getWriter()메소드는 PrintWriter를 반환하기 때문에 PrintWriter를 선언하고 받아주면 된다.
	  PrintWriter out = response.getWriter();
	  
	  // 5. 응답하기 (온전한 웹 페이지)
	  // 옛날 개발자들은 자바에서 html 태그를 싹 다 만들었었음.
	  
	  out.println("<!DOCTYPE html>");
	  out.println("<html lang=\"ko\">");   // " escape : \"
	  out.println("<head>");
	  out.println("<meta charset=\"UTF-8\">");
	  out.println("<title>페이지제목</title>");
	  out.println("<style>");
	  out.println(".wrap {");
	  out.println("  background-color: #a0a0a0;");
	  out.println("}");
	  out.println("</style>");
	  out.println("</head>");
	  out.println("<body>");
	  out.println("<div class=\"wrap\">");
	  out.println("<h1>요청파라미터</h1>");
	  out.println("<ul>");
	  out.println("<li>성별: " + gender + "</li>");
	  out.println("<li>도메인: " + domain + "</li>");
	  out.println("<li><pre>" + content + "</pre></li>");    // <pre> 태그 : 줄바꿈이나 공백을 그대로 나타내 줌 글꼴은 바뀜 (textarea태그 대용으로 사용)
	  out.println("<li>지역: " + region + "</li>");
	  out.println("</ul>");
	  out.println("</div>");
	  out.println("</body>");
	  out.println("</html>");
	  out.flush();     // 출력스트림에 남아있는 게 있으면 전부 불어내는?것
	  out.close();     // 출력스트림은 close가 필수이다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
