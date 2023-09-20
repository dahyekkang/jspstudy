package util;

public class PageVo {   // 페이지 정보를 담고 있다.
  
  private int page;     // 1 2      // 현재 페이지 번호 (요청 파라미터로 받는다.)
  private int total;    // 120      // 전체 항목(게시글)의 개수(DB에서 구한 뒤, 요청 파라미터로 받는다.)
  private int display;  // = 20     // 한 페이지에 표시할 항목(게시글)의 개수(요청 파라미터로 받는다. 우선 정해놓는다. 나중엔 바꾸고싶으면 바꿀 수 있다.)
  private int begin;    // 120 100  // 한 페이지에 표시되는 항목의 시작 번호(계산한다.)
  private int end;      // 101 81   // 한 페이지에 표시되는 항목의 종료 번호(계산한다.)

  public void setPage(int page, int total, int display) {
    
    /* 한 페이지를 나타낼 때 필요한 정보 */
    
    // 받은 정보 저장
    this.page = page;
    this.total = total;
    this.display = display;
    
    // 계산한 정보 저장
    begin = (page - 1) * display + 1;   // 1 21 41 ...
    end = begin + display - 1;          // 20 40 ...
    if(end > total) {
      end = total;
    }
  }
}
