<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  // 요청 인코딩
  request.setCharacterEncoding("UTF-8");

  // 요청 파라미터
  String dir = request.getParameter("dir");
  String filename = URLDecoder.decode(request.getParameter("filename"), "UTF-8");
  
  // dir의 실제 경로(RealPath)
  String realPath = application.getRealPath(dir);
  
  // File 객체 생성
  File file = new File(realPath, filename);
  
  // 결과 화면 만들기 (가독성이 좋은 코드) - 자바(jsp)로 직접 응답 화면도 만들 수 있다.
  if(!file.exists()){
    out.println("<div>" + filename + " 파일이 생성되지 않았습니다." + "</div>");
  } else {
    out.println("<div>" + filename + " 파일이 생성되었습니다." + "</div>");
  }
  
%>

</body>
</html>