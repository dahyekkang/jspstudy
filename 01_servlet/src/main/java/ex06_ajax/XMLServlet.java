package ex06_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;

/**
 * Servlet implementation class XMLServlet
 */
@WebServlet("/getXML")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLServlet() {
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
	  String name = request.getParameter("name");
	  String strAge = request.getParameter("age");
	  int age = 0;
	  if(strAge != null && !strAge.isEmpty()) {
	    age = Integer.parseInt(strAge);
	  }
	  
	  // 요청 확인
	  System.out.println(name + ", " + age);
	

	  // 3. XML 만들기

	  // {"person": {"name": "alice", "age": 30}}를 생성한 뒤 XML 태그 형식으로 변환
  	
	  /*
  	 * <?xml version="1.0" encoding="UTF-8"?>
  	 * <person>
  	 *   <name>alice</name>
  	 *   <age>30</age>
  	 * </person>
  	 */
	  	  
	  JSONObject person = new JSONObject();
	  
	  // {"name": "alice", "age": 30}
	  person.put("name", name);  // key : name, value : alice
	  person.put("age", age);    // key : age,  value : 30

	  // {"person": { ... }}
	  JSONObject resJSON = new JSONObject();
	  resJSON.put("person", person);
	  
	  // 위 주석의 태그 형식으로 알아서 변환해준다.
	  String responseXML = XML.toString(resJSON);
	  // 라이브러리 사용 잘 됐는지 점검
	  // System.out.println(responseXML);
	  
	  // 4. 응답 데이터 타입과 인코딩
	  response.setContentType("application/xml; charset=UTF-8");
	  
	  // 5. 응답 스트림 생성
	  PrintWriter out = response.getWriter();
	  
	  // 6. 응답
	  out.println(responseXML);    // $.ajax({success: function(resData){}...)
	                               // function의 매개변수 resData로 responseXML이 전달된다.
	  
	  out.flush();
	  out.close();
	  
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
