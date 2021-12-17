package ics324.project.libsys.repo;

import ics324.project.libsys.entities.Author;
import ics324.project.libsys.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

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
}