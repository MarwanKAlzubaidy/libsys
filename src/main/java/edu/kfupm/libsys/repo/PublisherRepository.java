package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}