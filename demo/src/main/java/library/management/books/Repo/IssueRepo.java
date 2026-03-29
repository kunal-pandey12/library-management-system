package library.management.books.Repo;

import library.management.books.Entity.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepo extends JpaRepository<IssueEntity,Long> {

}
