package library.management.books.Controller;

import library.management.books.Dto.AuthorDto;
import library.management.books.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public List<AuthorDto> createAuthor( @RequestBody List<AuthorDto> authorDto){
        return authorService.createAll(authorDto);
    }

    @GetMapping("/getAll")
    public List<AuthorDto>getAllAuthor(){
        return authorService.getAllAuthor();
    }

    @GetMapping("/{id}")
    public AuthorDto getById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto){
        return authorService.updateAuthor(id,authorDto);
    }
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return "AuthorId delete successfully";
    }

}
