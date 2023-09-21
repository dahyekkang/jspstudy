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
  .article span {
    margin-right: 20px;
  }
</style>
<script>

  $(function(){
    fnCheckbox();
  })

  function fnCheckbox(){
    $('#chk_all').click(function(){
      $('.chk_each').prop('checked', $(this).prop('checked'));
    })
    $('.chk_each').click(function(){
      let total = 0;
      $.each($('.chk_each'), function(i, elem){
        total += elem.checked;
      })
      $('#chk_all').prop('checked', total === $('.chk_each').length);
    })
  }

</script>
</head>
<body>

  <div>
    <a href="${contextPath}/writeArticle.do">기사작성하러가기</a>
  </div>

  <hr>
  
  <div>
    <input type="checkbox" id="chk_all">
    <label for="chk_all">전체선택</label>
  </div>

  <hr>
  
  <div>
  <c:if test="${empty articleList}">
    <div>작성된 기사가 없습니다.</div>
  </c:if>
  <c:if test="${not empty articleList}">
    <c:forEach items="${articleList}" var="article">
      <div class="article">
        <input type="checkbox" class="chk_each" value="${article.article_no}">
        <span>${article.article_no}</span>
        <span><a href="${contextPath}/getArticleDetail.do?article_no=${article.article_no}">${article.title}</a></span>
        <span>${article.hit}</span>
        <span>${article.created}</span>
      </div>
    </c:forEach>
  </c:if>
  </div>
  
  <div>${paging}</div>

</body>
</html>