package ics324.project.libsys.repo;

import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.copy.OwnedCopy;
import ics324.project.libsys.enums.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnedCopyRepository extends JpaRepository<OwnedCopy, Long> {
    List<Copy> findByBookAndAvailability(Book book, Availability availability);
}