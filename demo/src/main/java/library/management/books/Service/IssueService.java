package library.management.books.Service;
import library.management.books.Dto.IssueRequestDto;
import library.management.books.Dto.IssueResponseDto;
import library.management.books.Entity.BookEntity;
import library.management.books.Entity.IssueEntity;
import library.management.books.Entity.UserEntity;
import library.management.books.Repo.BookRepo;
import library.management.books.Repo.IssueRepo;
import library.management.books.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepo issueRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

                   // MAIN METHOD (Issue Book) // create
    public IssueResponseDto issueBook(IssueRequestDto dto){

                      // User fetch
        UserEntity user=userRepo.findById(dto.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));

                     //book fetch
        BookEntity book=bookRepo.findById(dto.getBookId())
                .orElseThrow(()->new RuntimeException("Book not found"));

        //   Duplicate check (NEW)
        if(issueRepo.existsByUserAndBookAndReturnDateIsNull(user, book)){
            throw new RuntimeException("Book already issued to this user");
        }

                // Availability check
        if (book.getAvailableCopies()<=0){
            throw new RuntimeException("Book not available");
        }
           // Decrease copies
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepo.save(book);

        IssueEntity issue=new IssueEntity();
        issue.setUser(user);
        issue.setBook(book);
        issue.setDueDate(LocalDate.now().plusDays(7));
        issue.setIssueDate(LocalDate.now());

        IssueEntity saved=issueRepo.save(issue);

        IssueResponseDto responseDto=new IssueResponseDto();
        responseDto.setId(saved.getId());
        responseDto.setUserName(user.getName());
        responseDto.setBookName(book.getName());
        responseDto.setIssueDate(saved.getIssueDate());
        responseDto.setDueDate(saved.getDueDate());

        return responseDto;
    }

    public String returnBook(Long issueId) {

        IssueEntity issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        // Already returned check
        if (issue.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        // Set return date
        issue.setReturnDate(LocalDate.now());

        String message = "Book returned successfully";

        if (issue.getReturnDate().isAfter(issue.getDueDate())) {
            message = "Book returned late";
        }

       // Increase available copies (ALWAYS chalega)
        BookEntity book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        issueRepo.save(issue);

        return message;
    }
    public List<IssueResponseDto> getAllIssues(){
        return issueRepo.findAll().stream().map(issue -> {
            IssueResponseDto dto = new IssueResponseDto();
            dto.setId(issue.getId());
            dto.setUserName(issue.getUser().getName());
            dto.setBookName(issue.getBook().getName());
            dto.setIssueDate(issue.getIssueDate());
            return dto;
        }).toList();
    }
}
