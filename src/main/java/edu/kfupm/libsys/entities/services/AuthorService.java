package edu.kfupm.libsys.entities.services;

import edu.kfupm.libsys.entities.Author;
import edu.kfupm.libsys.repo.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository   repository;


    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }
    public List<Author> findAllAuthor(){
        return repository.findAll();
    }
    public void saveAuthor(Author a){

       repository.save(a);

    }

    public void deleteAuthor(Author author) {

        repository.delete(author);
    }
}
