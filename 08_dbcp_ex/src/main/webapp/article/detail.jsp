<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style>
  .link a {
    margin-right: 20px;
  }
</style>
</head>
<body>

  <div class="link">
    <a href="${contextPath}/writeArticle.do">기사작성하러가기</a>
    <a href="${contextPath}/getArticleList.do">기사목록으로이동</a>
    <a href="${contextPath}/editArticle.do?article_no=${article.article_no}">기사편집하러가기</a>
  </div>

  <hr>
  
  <div>
    <div>기사번호: ${article.article_no}</div>
    <div>기사제목: ${article.title}</div>
    <div>작성자: ${article.editor}</div>
    <div>조회수: ${article.hit}</div>
    <div>기사내용</div>
    <div><pre>${article.content}</pre></div>
    <div>최종수정일: ${article.lastmodified}</div>
    <div>최초작성일: ${article.created}</div>
  </div>

</body>
</html>