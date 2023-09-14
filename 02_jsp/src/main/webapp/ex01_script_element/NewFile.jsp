<%--
  지시어(directive)
  1. page    : page 설정, Java class import
  2. include : 다른 페이지를 현재 페이지에 포함할 때 사용(네이버 뉴스에서 정치/경제/사회 등 눌렀을 때 안 바뀌는 부분(페이지 상단부 메뉴, 하단부). 모든 페이지에 계속 메뉴를 욱여 넣는 게 아니라, 메뉴를 만들고 페이지를 욱여 넣는 것이다.)
  3. taglib  : JSTL(Java Standard Tag Library) 사용할 때 사용
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <!-- HTML 주석 : "소스 보기"에서 보인다.    Java 관련 코드는 주석처리하지 못한다. 주석으로 묶은 것 같지만 실제로 실행이 된다.(주석이 아니게 됨) (ctrl + shift + /) -->
  <%-- JSP  주석 : "소스 보기"에서 안 보인다. Java 관련 코드를 주석처리할 수 있다. --%>

  

  <%-- 스크립트릿(scriptlet) : Java 코드 넣을 때 사용하는 것 --%>
  <% int a = 10; %>

  
  <%-- 표현식(expression) : Java 값(변수값, 반환값이 있는 메소드 호출)을 넣을 때 사용하는 것 - 내부에 자동으로 생기는 공백은 지워줘라. --%>
  <div><%=a%></div>
  

  <%-- 선언부(declaration) : Java 메소드 정의할 때(만들 때) 사용 --%>
  <%!
    // 0~9 사이의 난수를 반환하는 getRandom() 메소드 정의하기
    public int getRandom(){
      return (int)(Math.random() * 10);
    }
  %>
  <div>0~9 사이 난수 : <%=getRandom()%></div>
  
  
  <%--
    Java와 JavaScript의 관계
    1. Java 변수를 JavaScript에서 사용할 수 있다.
    2. JavaScript 변수를 Java에서 사용할 수 없다.
  --%>
   
  <!-- Java 변수 선언 -->
  <% String msg = "Hello World"; %>
  
  <!-- Java 변수를 JavaScript에서 사용하기 -->
  <script>
    alert('<%=msg%>');
  </script>
     

  <%-- 연습1. 이름과 나이를 변수에 저장한 뒤 보여주자 --%>
  <%
    String name = "홍길동";
    int age = 30;
  %>
  <ul>
    <li>이름 : <%=name%></li>
    <li>나이: <%=age%></li>
  </ul>
  
  
  <%-- 연습2. getRandom() 호출 결과가 5 이상이면 큰 수, 아니면 작은 수를 출력하기 --%>
  <% if(getRandom() >= 5){ %>
    <div>큰 수</div>
  <% } else { %>
    <div>작은 수</div>
  <% } %>
  
  
  <%-- 연습3. <select> 태그에 1월~12월까지 등록해서 출력하기 --%>
  <div>
    <select name="month">
      <% for(int month = 1; month <= 12; month++){ %>
        <option value="<%=month%>"><%=month%>월</option>
      <% } %>
    </select>
  </div>
  
  
  <%-- 연습4. 현재 날짜를 yyyy-MM-dd 형식의 문자열로 반환하는 getToday() 메소드를 만들고 호출하기 --%>
  <%!
    public String getToday(){
     LocalDate today = LocalDate.now();
     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     return dtf.format(today);
    }
  %>
  <div><%=getToday()%></div>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
</body>
</html>