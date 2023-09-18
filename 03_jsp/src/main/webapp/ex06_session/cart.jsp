<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
  // <body> 태그를 모두 읽은 뒤 function(){}을 실행한다.
  $(function(){
    $('#btn_shop').click(function(){
      location.href = '${contextPath}/ex06_session/main.jsp';
    })
    // 뭐든지 삭제하기 전에 한 번 물어봐라-!!!!
    $('#btn_clear_cart').click(function(){
      if(confirm('장바구니를 비울까요?')){
        location.href = '${contextPath}/clearCart';
      } else {
        alert('장바구니 비우기를 취소했습니다.');
      }
    })
  })
</script>
</head>
<body>

<%-- 장바구니가 비었다. session에 저장된 카트가 없으면, --%>
<c:if test="${empty sessionScope.cart}">
  <div>장바구니가 비었습니다.</div>
</c:if>

<%-- elem은 Map이다. Map의 요소를 꺼내올 때, Map이름.속성으로 꺼내면 됨 --%>
<c:if test="${not empty sessionScope.cart}">
  <c:forEach var="elem" items="${sessionScope.cart}">
    <div>${elem.item} ${elem.ea}</div>
  </c:forEach>
</c:if>

<div>
  <button type="button" id="btn_shop">계속쇼핑하기</button>
  <button type="button" id="btn_clear_cart">장바구니비우기</button>
</div>

</body>
</html>