package ics324.project.libsys.repo;

import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.copy.LoanedCopy;
import ics324.project.libsys.enums.Availability;
import ics324.project.libsys.enums.returnStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LoanedCopyRepository extends JpaRepository<LoanedCopy, Long> {
    Collection<? extends Copy> findByBookAndAvailabilityAndReturnToLibraryStatus(Book book, Availability availability, returnStatus notReturned);
}