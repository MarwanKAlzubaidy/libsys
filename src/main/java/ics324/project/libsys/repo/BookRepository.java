package ics324.project.libsys.repo;

import ics324.project.libsys.entities.Author;
import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.user.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
 //   List<Book> findByAuthorsContaining(Set<Author> authors);

    List<Book> findByPublishDate(LocalDate publishDate);

    List<Book> findByAuthorsIn(Set<Author> authors);

    List<Book> findByPublishDateAndAuthorsIn(LocalDate publishDate, Set<Author> authors);

    List<Book> findByTitleContainingIgnoreCaseAndAuthorsIn(String title, Set<Author> authors);

    List<Book> findByTitleContainingIgnoreCaseAndPublishDate(String title, LocalDate publishDate);

    List<Book> findByTitleContainingIgnoreCaseAndPublishDateAndAuthorsIn(String title, LocalDate publishDate, Set<Author> authors);


    List<Book> findByTitleContainingIgnoreCase(String title);



    @Query(value = "select * from book b having 0= (select count(c.book_id) from owned_copy c  where b.id=c.book_id and c.availability=1)+(select count(l.book_id) from loaned_copy l  where b.id=l.book_id and l.availability=1 and l.return_to_library_status=1);",nativeQuery = true)
    List<Book> findReserveBooks();

}