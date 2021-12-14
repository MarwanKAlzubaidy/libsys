package ics324.project.libsys.repo;

import ics324.project.libsys.entities.copy.LoanedCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanedCopyRepository extends JpaRepository<LoanedCopy, Long> {
}