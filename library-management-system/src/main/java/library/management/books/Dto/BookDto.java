package library.management.books.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    private Long authorId;
    private String authorName;
    private String name;
    private String category;
    private Long availableCopies;
}
