package ics324.project.libsys.repo;

import ics324.project.libsys.entities.copy.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}