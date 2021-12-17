package ics324.project.libsys.entities.services;

import ics324.project.libsys.entities.Author;
import ics324.project.libsys.entities.Book;
import ics324.project.libsys.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    public void deleteBook(Book book) {

        bookRepository.delete(book);
    }

    public List<Book> getfilterdBooks(String title, LocalDate publishDate, Set<Author> authors) {
        if (title.equals("")) {
            if (publishDate == null)
                if (authors.isEmpty())
                    return bookRepository.findAll();
                else
                    return bookRepository.findByAuthorsIn(authors);
            else if (authors.isEmpty())
                return bookRepository.findByPublishDate(publishDate);
            else
                return bookRepository.findByPublishDateAndAuthorsIn(publishDate, authors);
        } else if (publishDate == null)
            if (authors.isEmpty())
                return bookRepository.findByTitleContainingIgnoreCase(title);
            else
                return bookRepository.findByTitleContainingIgnoreCaseAndAuthorsIn(title, authors);
        else {
            if (authors.isEmpty())
                return bookRepository.findByTitleContainingIgnoreCaseAndPublishDate(title, publishDate);
            else return bookRepository.findByTitleContainingIgnoreCaseAndPublishDateAndAuthorsIn(title, publishDate, authors);


        }


    }


}
