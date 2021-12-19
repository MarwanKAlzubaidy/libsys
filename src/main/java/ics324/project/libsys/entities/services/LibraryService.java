package ics324.project.libsys.entities.services;

import ics324.project.libsys.entities.Library;
import ics324.project.libsys.repo.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository){this.libraryRepository = libraryRepository;}

    public List<Library> getAllLibraries() { return libraryRepository.findAll(); }

    public void saveLibrary(Library library) { libraryRepository.save(library); }

    public void deleteLibrary(Library library) { libraryRepository.delete(library); }
}
