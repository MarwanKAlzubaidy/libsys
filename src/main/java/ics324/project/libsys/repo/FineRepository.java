package ics324.project.libsys.repo;

import ics324.project.libsys.entities.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {
}