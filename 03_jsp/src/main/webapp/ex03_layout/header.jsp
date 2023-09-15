<%@page import="java.util.Optional"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
  request.setCharacterEncoding("UTF-8");
  Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
  String title = opt.orElse("환영합니다");
  String contextPath = request.getContextPath();
%>

<title><%=title%></title>

<script src="<%=contextPath%>/resource/js/lib/jquery-3.7.1.min.js"></script>

<!-- js 포함하는 방법 -->
<!-- 자바스크립트 : 원래는 div(nav)아래에 넣어야 하는데 위에 두고 싶으면
    load 이벤트 사용 - 내용을 먼저 읽고, 나를 하시오. -->
<script src="<%=contextPath%>/resource/js/header.js?dt=<%=System.currentTimeMillis()%>"></script>

<!-- css 포함하는 방법 -->
<!-- 스타일 태그를 대신할 수 있는 link 태그 -->
<link rel="stylesheet" href="<%=contextPath%>/resource/css/header.css?dt=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="<%=contextPath%>/resource/css/main.css?dt=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="<%=contextPath%>/resource/css/footer.css?dt=<%=System.currentTimeMillis()%>">
<!-- 수정하고 저장하고 새로고침하면 반영이 안됨(∵ cache)
      -> 해결책1 : 자동화를 하기   - link 태그의 href에 파라미터로 타임스탬프를 넣어준다.
      -> 해결책2 : 자동화를 안하기 - link 태그의 href에 파라미터로 버전을 직접 변경해준다. 실행할 때마다 경로를 직접 바꿔준다.(css뒤에 ?ver=1.0)
-->


</head>
<body>

  <nav class="gnb">
    <div class="logo"></div>
    <ul>
      <li><a href="main1.jsp">정치</a></li>
      <li><a href="main2.jsp">경제</a></li>
      <li><a href="main3.jsp">사회</a></li>
    </ul>
  </nav>