package ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String name = request.getParameter("name");
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    String gender = request.getParameter("gender");
    String email = request.getParameter("email");
    String mobile1 = request.getParameter("mobile1");
    String mobile2 = request.getParameter("mobile2");
    String mobile3 = request.getParameter("mobile3");
    
    // 3. 응답 데이터의 타입과 인코딩 설정
    response.setContentType("text/html; charset=UTF-8");
    
    // 4. 응답 스트림 생성
    PrintWriter out = response.getWriter();
    
    // 5. 응답하기
    out.println("<!DOCTYPE html>");
    out.println("<html lang=\"ko\">");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>페이지제목</title>");
    
    out.println("<style>");
    out.println("</style>");
    
    out.println("</head>");
    
    out.println("<body>");
    out.println("<div class=\"wrap\">");
    out.println("<ul>");
    out.println("<li>아이디: " + id + "</li>");
    out.println("<li>비밀번호: " + pw + "</li>");
    out.println("<li>이름: " + name + "</li>");
    out.println("<li>생년월일: " + year + "년 " + String.format("%02d", month) + "월 " + String.format("%02d", day) + "일" + "</li>");
    out.println("<li>성별: " + gender + "</li>");
    out.println("<li>이메일: " + email + "</li>");
    out.println("<li>휴대전화: " + mobile2 + "</li>");
    out.println("</ul>");
    out.println("</div>");
    
    out.println("</body>");
    out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
