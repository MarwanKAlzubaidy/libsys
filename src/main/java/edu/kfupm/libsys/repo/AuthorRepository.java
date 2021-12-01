package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.Author;
import edu.kfupm.libsys.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}