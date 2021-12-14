package ics324.project.libsys.entities.services;

import ics324.project.libsys.entities.Book;
import ics324.project.libsys.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllbooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
