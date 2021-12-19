package ics324.project.libsys.repo;

import ics324.project.libsys.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}