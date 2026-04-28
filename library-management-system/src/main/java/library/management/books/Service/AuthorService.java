package library.management.books.Service;
import library.management.books.Dto.AuthorDto;
import library.management.books.Entity.AuthorEntity;
import library.management.books.Repo.AuthorRepo;
import library.management.books.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    public List<AuthorDto> createAll(List<AuthorDto> authorDto){
        List<AuthorEntity> Authors=authorDto.stream().map(Dto->{

            AuthorEntity author=new AuthorEntity();
            author.setName(Dto.getName());
            author.setNumber(Dto.getNumber());
            author.setEmail(Dto.getEmail());
            return author;
        }).toList();

        List<AuthorEntity> savedAuthors = authorRepo.saveAll(Authors);

        List<AuthorDto> result = savedAuthors.stream().map(a->
                new AuthorDto(
                        a.getId(),
                        a.getName(),
                        a.getEmail(),
                        a.getNumber()
                )
        ).toList();

        return result;
    }
    public List<AuthorDto>getAllAuthor(){
        List<AuthorEntity> Authors=authorRepo.findAll();
        return Authors.stream().map(AuthorEntity ->{

            AuthorDto dto=new AuthorDto();
            dto.setEmail(AuthorEntity.getEmail());
            dto.setNumber(AuthorEntity.getNumber());
            dto.setName(AuthorEntity.getName());
            dto.setId(AuthorEntity.getId());

            return dto;
        }).toList();
    }

    public AuthorDto getAuthorById(Long id){

        AuthorEntity authorEntity=authorRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Author not found"));

        AuthorDto dto=new AuthorDto();
        dto.setId(authorEntity.getId());
        dto.setName(authorEntity.getName());
        dto.setEmail(authorEntity.getEmail());
        dto.setNumber(authorEntity.getNumber());
        return dto;
    }
    public AuthorDto updateAuthor(Long id ,AuthorDto authorDto){

        AuthorEntity authorEntity = authorRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Author not found id"));


        authorEntity.setEmail(authorDto.getEmail());
        authorEntity.setName(authorDto.getName());
        authorEntity.setNumber(authorDto.getNumber());

        AuthorEntity updateAuthor=authorRepo.save(authorEntity);

        AuthorDto response = new AuthorDto();
        response.setId(updateAuthor.getId());
        response.setNumber(updateAuthor.getNumber());
        response.setEmail(updateAuthor.getEmail());
        response.setName(updateAuthor.getName());

        return response;
    }

    public void deleteAuthor(Long id){
        AuthorEntity authorEntity = authorRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("AuthorId not found "));
        authorRepo.delete(authorEntity);
    }
    }
