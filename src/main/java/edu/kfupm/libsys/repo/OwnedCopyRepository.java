package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.copy.OwnedCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedCopyRepository extends JpaRepository<OwnedCopy, Long> {
}