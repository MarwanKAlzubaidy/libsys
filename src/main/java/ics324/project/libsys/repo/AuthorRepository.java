package ics324.project.libsys.repo;

import ics324.project.libsys.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}