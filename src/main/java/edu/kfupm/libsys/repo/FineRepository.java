package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {
}