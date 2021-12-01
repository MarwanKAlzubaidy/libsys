package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}