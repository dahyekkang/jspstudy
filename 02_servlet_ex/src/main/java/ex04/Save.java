package ex04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class Save
 */
@WebServlet("/save")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Save() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  String path = "D:/storage2";
	  
	  File dir = new File(path);
	  if(!dir.exists()) {
	    dir.mkdirs();
	  }
	  
	  MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 50, "UTF-8", new DefaultFileRenamePolicy());
	  
	  String date = LocalDate.now().toString();
	  
	  String writer = mr.getParameter("writer");
	  
	  String title = mr.getParameter("title");
	  
	  
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  
	  out.println("<script>");
	  out.println("alert(" + date + "-" + writer + "-" + title + ".txt 파일이 생성되었습니다.");
	  out.println("history.back()");
	  out.println("</script>");
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
