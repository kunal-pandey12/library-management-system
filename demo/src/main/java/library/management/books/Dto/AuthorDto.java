package library.management.books.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Long authorId;
    private String name;
    private String email;
    private String number;
    private String books;
}
