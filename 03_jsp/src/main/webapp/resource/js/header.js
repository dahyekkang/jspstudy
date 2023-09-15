/**
 * 본문을 모두 읽은 뒤 JavaScript를 수행할 수 있도록 load 이벤트 처리한다.
 * 방법1. window.onload = function(){}
 * 방법2. $(document).ready(function(){})
 * 방법3. $(function(){})
 */
$(function(){
  
  $('.gnb a').mouseover(function(){
   $(this).css('background-color', 'silver');      
  })
  $('.gnb a').mouseout(function(){
    $(this).css('background-color', '');      
  })
  
  var img = new Image();
  $(img).attr('src', getContextPath() + '/resource/image/naver.png');
  $(img).attr('width', '150px');
  $('.logo').html(img);
  
})


// 컨텍스트패스를 반환하는 함수
function getContextPath(){
  /*
    location.href: http://localhost:8080/jsp/ex03_layout/main1.jsp
    location.host: localhost:8080
  */
  var begin = location.href.indexOf(location.host) + location.host.length;    // jsp앞 /의 index
  var end = location.href.indexOf('/', begin + 1);                            // jsp뒤 /의 index (begin+1부터 /의 index)
  return location.href.substring(begin, end);                                 // begin 포함O, end 포함X
  
}