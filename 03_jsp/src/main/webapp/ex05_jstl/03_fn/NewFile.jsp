<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
  <c:set var="str" value="Hello World" scope="page" />    <%-- scope는 생략 가능 --%>

  <h4>${fn:length(str)}</h4>
  <h4>${fn:substring(str, 0, 5)}</h4>
  <h4>${fn:substringBefore(str, ' ')}</h4>
  <h4>${fn:substringAfter(str, ' ')}</h4>
  <h4>${fn:indexOf(str, ' ')}</h4>
  <h4>${fn:replace(str, ' ', '_')}</h4>
  
  <c:if test="${fn:startsWith(str, 'H')}">
    <h4>H로 시작한다.</h4>
  </c:if>
  <c:if test="${fn:endsWith(str, 'H')}">
    <h4>H로 끝난다.</h4>
  </c:if>
  <c:if test="${fn:contains(str, 'H')}">
    <h4>H를 포함한다.</h4>
  </c:if>
  <c:if test="${fn:containsIgnoreCase(str, 'h')}">
    <h4>H or h를 포함한다.</h4>
  </c:if>
  
  
  <c:set var="words" value="${fn:split(str, ' ')}"/>
    <c:forEach var="word" items="${words}">
      <h4>${word}</h4>
    </c:forEach>
  <h4>${fn:join(words, ' ')}</h4>
    
  <%-- 스크립트 입력하면 스트립트로 인식하지 않고 문자열로 인식하도록 스크립트 인식을 방지하는 코드 --%>
  <c:set var="str2" value="<script>alert('haha')</script>" />
  ${fn:escapeXml(str2)}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

</body>
</html>