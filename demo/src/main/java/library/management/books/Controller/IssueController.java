package library.management.books.Controller;

import library.management.books.Dto.IssueRequestDto;
import library.management.books.Dto.IssueResponseDto;
import library.management.books.Service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/book")
    public IssueResponseDto IssueBook(@RequestBody IssueRequestDto RequestDto){
        return issueService.issueBook(RequestDto);

    }
    @PutMapping("/return/{issueId}")
    public String returnBook(@PathVariable Long issueId){
        return issueService.returnBook(issueId);
    }
}
