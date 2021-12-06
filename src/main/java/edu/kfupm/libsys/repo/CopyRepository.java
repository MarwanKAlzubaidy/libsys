package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.copy.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}