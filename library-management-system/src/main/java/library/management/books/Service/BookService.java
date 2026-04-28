package library.management.books.Service;
import library.management.books.Dto.BookDto;
import library.management.books.Entity.AuthorEntity;
import library.management.books.Entity.BookEntity;
import library.management.books.Repo.AuthorRepo;
import library.management.books.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    public List<BookDto> createAll(List<BookDto> bookDto){
        List<BookEntity> books=bookDto.stream().map(dto ->{

            BookEntity book=new BookEntity();
            book.setAvailableCopies(dto.getAvailableCopies());
            book.setName(dto.getName());
            book.setCategory(dto.getCategory());

            AuthorEntity author=authorRepo.findById(dto.getAuthorId())
                    .orElseThrow(()->new RuntimeException("Author not found"));

            book.setAuthor(author);

            return book;


        }) .toList();
        List<BookEntity> savedBooks = bookRepo.saveAll(books);

        return savedBooks.stream().map(book ->
              new BookDto(
                      book.getId(),
                      book.getAuthor().getId(),
                      book.getAuthor().getName(),
                      book.getName(),
                      book.getCategory(),
                      book.getAvailableCopies()
              )
        ).toList();
    }
     public List<BookDto> getAllBook(){
         List<BookEntity> Books=bookRepo.findAll();

         return Books.stream().map(book->{
             BookDto dto=new BookDto();
             dto.setId(book.getId());
             dto.setName(book.getName());
             dto.setCategory(book.getCategory());
             dto.setAvailableCopies(book.getAvailableCopies());
            if (book.getAuthor()!=null){
                dto.setAuthorId(book.getAuthor().getId());
                dto.setAuthorName(book.getAuthor().getName());
            }
             return dto;
         }).toList();
     }
     public BookDto getBookById(Long id){
        BookEntity bookEntity=bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("book id not found"));

        BookDto dto= new BookDto();
        dto.setId(bookEntity.getId());
        dto.setName(bookEntity.getName());
        dto.setCategory(bookEntity.getCategory());
        dto.setAvailableCopies(bookEntity.getAvailableCopies());

        //  I forget this colum so when I remember that than add this code
         if (bookEntity.getAuthor() != null) {
             dto.setAuthorId(bookEntity.getAuthor().getId());
             dto.setAuthorName(bookEntity.getAuthor().getName()); // naya line
         }
        return dto;
     }
     public BookDto updateBooks(Long id ,BookDto bookDto){

        BookEntity bookEntity=bookRepo.findById(id)
                .orElseThrow(()->new RuntimeException("book id not found"));


         bookEntity.setName(bookDto.getName());
         bookEntity.setAvailableCopies(bookDto.getAvailableCopies());
         bookEntity.setCategory(bookDto.getCategory());

         AuthorEntity author = authorRepo.findById(bookDto.getAuthorId())
                 .orElseThrow(() -> new RuntimeException("Author not found"));

         bookEntity.setAuthor(author);

         BookEntity updateBook=bookRepo.save(bookEntity);

         BookDto response = new BookDto();
         response.setId(bookEntity.getId());
         response.setName(bookEntity.getName());
         response.setCategory(bookEntity.getCategory());
         response.setAvailableCopies(bookEntity.getAvailableCopies());

         response.setAuthorId(updateBook.getAuthor().getId());
         response.setAuthorName(updateBook.getAuthor().getName());
         return response;
     }

     public void deleteBook(Long id){
        BookEntity bookEntity=bookRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Book id not found "));
        bookRepo.delete(bookEntity);
     }
}
