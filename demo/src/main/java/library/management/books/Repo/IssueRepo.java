package library.management.books.Repo;

import library.management.books.Entity.BookEntity;
import library.management.books.Entity.IssueEntity;
import library.management.books.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepo extends JpaRepository<IssueEntity,Long> {

    boolean existsByUserAndBookAndReturnDateIsNull(UserEntity user, BookEntity book);

}
