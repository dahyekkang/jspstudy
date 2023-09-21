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
  fnBoardList();
  //fnBoardModify();    // 이걸 주석 처리하고 제목을 안 적고 보내면 db에서 오류가 날 수밖에 없다! -> 수정되지 않고, index.do로 이동됨
})

  // 함수 정의
  function fnBoardList(){
    $('#btn_list').click(function(){
    location.href = '${contextPath}/board/list.do';      
    })
  }
  
  // 함수 정의
  function fnBoardModify(){
    $('#frm_edit').submit(function(event){
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
  <form id="frm_edit" method="post" action="${contextPath}/board/modify.do">
    <div>
      <label for="title">제목</label>
      <input type="text" id="title" name="title" value="${board.title})">
    </div>
    <div>
      <textarea rows="5" cols="50" name="content">${board.content}</textarea>
    </div>
    <div>
      <input type="hidden" name="board_no" value="${board.board_no}">
      <button type="submit">수정완료</button>
      <button type="reset">작성초기화</button>
      <button type="button" id="btn_list">목록으로이동</button>
    </div>
  </form>
</div>

</body>
</html>