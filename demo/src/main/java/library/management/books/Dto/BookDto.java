package library.management.books.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long authorId;
    private String name;
    private String category;
    private Long availableCopies;
    private List<Long> authorIds;
}
