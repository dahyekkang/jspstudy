package util;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PageVo {   // 페이지 정보를 담고 있다.
  
  // 전체 항목의 개수 : 120, 한 페이지 : 20, 1블록에 2개씩(1블록:1,2 2블록:3,4)
  
  private int page;     // 1 2      // 현재 페이지 번호 (요청 파라미터로 받는다.)
  private int total;    // 120      // 전체 항목(게시글)의 개수(DB에서 구한 뒤, 요청 파라미터로 받는다.)
  private int display;  // = 20     // 한 페이지에 표시할 항목(게시글)의 개수(요청 파라미터로 받는다. 우선 정해놓는다. 나중엔 바꾸고싶으면 바꿀 수 있다.)
  private int begin;    // 120 100  // 한 페이지에 표시되는 항목의 시작 번호(계산한다.)
  private int end;      // 101 81   // 한 페이지에 표시되는 항목의 종료 번호(계산한다.)
  
  private int totalPage;            // 전체 페이지의 개수(계산한다.)
  private int pagePerBlock = 2;     // 한 블록에 표시되는 페이지의 개수(임의로 정한다.)
  private int beginPage;            // 한 블록에 표시되는 페이지의 시작 번호(계산한다.)
  private int endPage;              // 한 블록에 표시되는 페이지의 종료 번호(계산한다.)

  public void setPaging(int page, int total, int display) {
    
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
    
    /* 전체 페이지를 나타낼 때 필요한 정보 */
    
    // 전체 페이지 계산
    totalPage = (int)Math.ceil((double)total / display);
    
    // 각 블록의 시작 페이지와 종료 페이지 계산
    // block | beginPage   endPage
    //   1   |    1           2
    //   2   |    3           4
    //   3   |    5           6
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
    endPage = beginPage + pagePerBlock - 1;
    if(endPage > totalPage) {
      endPage = totalPage;
    }
    
  }
  
  public String getPaging(String url) {
    
    // <a href="https://comic.naver.com/webtoon/list?page=1">1</a>
    // <a href="https://comic.naver.com/webtoon/list?page=2">2</a>
    
    StringBuilder sb = new StringBuilder();
    
    sb.append("<div>");
    
    // 이전 블록  (ajax에선 이걸 못 쓴다.)
    if(beginPage == 1) {
      sb.append("<span>이전</span>");
    } else {
      sb.append("<a href=\"" + url + "?page=" + (beginPage - 1) + "\">이전</a>");
    }
    
    
    // 페이지 번호
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {   // 현재 페이지는 클릭이 안 되게
        sb.append("<span>" + p + "</span>");
      } else {
        sb.append("<a href=\"" + url + "?page=" + p + "\">" + p + "</a>");        
      }
    }
    
    // 다음 블록
    if(endPage == totalPage) {
      sb.append("<span>다음</span>");
    } else {
      sb.append("<a href=\"" + url + "?page=" + (endPage + 1) + "\">다음</a>");
    }
    sb.append("</div>");
    
    return sb.toString();
    
  }
  
  
}
