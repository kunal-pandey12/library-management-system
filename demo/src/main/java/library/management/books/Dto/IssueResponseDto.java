package library.management.books.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueResponseDto {
    private Long id;
    private String userName;
    private String bookName;
    private LocalDate issueDate;
}


