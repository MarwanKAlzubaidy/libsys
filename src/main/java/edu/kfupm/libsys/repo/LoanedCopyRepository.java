package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.copy.LoanedCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanedCopyRepository extends JpaRepository<LoanedCopy, Long> {
}