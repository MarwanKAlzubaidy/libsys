package ics324.project.libsys.repo;

import ics324.project.libsys.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}