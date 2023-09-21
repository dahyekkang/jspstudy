<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
                              <%-- ↑ 표현언어(EL) 대신 JSP 표현식<%= %>으로 함 사용해봤다. --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>

$(function(){   // function없으면 스크립트가 먼저 있어서 목록으로 이동, 제목은 필수입니다. 가 되지 않는다. function넣어줌으로써 진짜 맨 마지막에 하게 됨
  // 함수 호출 (호이스팅. 만들어둔 걸 먼저 해석하기 때문에 호출은 나중에 처리됨)
  fnArticleList();
  fnArticleRegister();  
})

  // 함수 정의
  function fnArticleList(){
    $('#btn_list').click(function(){
    location.href = '${contextPath}/getArticleList.do';      
    })
  }
  
  // 함수 정의
  function fnArticleRegister(){
    $('#frm_register').submit(function(event){
      if($('#title').val() === ''){
        alert('제목은 필수입니다.');
        $('#title').focus();
        event.preventDefault();
        return;
      }
    })
  }
  
</script>
</head>
<body>

<div>
  <form id="frm_register" method="post" action="${contextPath}/addArticle.do">
    <div>
      <label for="title">제목</label>
      <input type="text" id="title" name="title">
    </div>
    <div>
      <textarea rows="5" cols="50" name="content"></textarea>
    </div>
    <div>
      <label for="editor">작성자</label>
      <input type="text" id="editor" name="editor">
    </div>
    <div>
      <button type="submit">작성완료</button>
      <button type="reset">작성초기화</button>
      <button type="button" id="btn_list">목록으로이동</button>
    </div>
  </form>
</div>

</body>
</html>