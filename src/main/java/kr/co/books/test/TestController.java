package kr.co.books.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
    @GetMapping("/test/create")
    public String boardCreateTest() {
        return "board/board_create"; 
    }
    
    @GetMapping("/test/free")
    public String boardFreeTest() {
        return "board/board_free"; 
    }
    
    @GetMapping("/test/notice")
    public String boardNoticeTest() {
        return "board/board_notice"; 
    }
    
    @GetMapping("/test/update")
    public String boardUpdateTest() {
        return "board/board_update"; 
    }
    
    @GetMapping("/test/view")
    public String boardViewTest() {
        return "board/board_view"; 
    }
    
    @GetMapping("/test/wantbook")
    public String boardWantbookTest() {
        return "board/board_wantbook"; 
    }
    
    
    @GetMapping("/test/add")
    public String bookAddTest() {
        return "book/book_add"; 
    }
    
    @GetMapping("/test/new")
    public String bookNewTest() {
        return "book/book_new"; 
    }
    
    @GetMapping("/test/recommend")
    public String bookRecommendTest() {
        return "book/book_recommend"; 
    }
    
    @GetMapping("/test/search")
    public String bookSearchTest() {
        return "book/book_search"; 
    }
    
    @GetMapping("/test/book_update")
    public String bookBookUpdateTest() {
        return "book/book_update"; 
    }
    
    
    @GetMapping("/test/checkout")
    public String bookCheckOutTest() {
        return "book/book_checkout_list"; 
    }
    
    @GetMapping("/test/search_admin")
    public String bookSearch_AdminTest() {
        return "book/book_search_admin"; 
    }
    
    @GetMapping("/test/mybook")
    public String bookMyBookTest() {
        return "book/mybook"; 
    }
    
    @GetMapping("/test/register")
    public String bookRegisterTest() {
        return "member/register"; 
    }
    
    @GetMapping("/test/list")
    public String bookListTest() {
        return "member/member_list"; 
    }
}
