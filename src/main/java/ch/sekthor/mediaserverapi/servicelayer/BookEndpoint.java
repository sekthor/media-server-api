package ch.sekthor.mediaserverapi.servicelayer;

import ch.sekthor.mediaserverapi.persitence.book.Book;
import ch.sekthor.mediaserverapi.persitence.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BookEndpoint {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/api/book/{mediaId}")
    public Book findBookById(@PathVariable Long mediaId) {
        return bookRepository.findById(mediaId).get();
    }

    @GetMapping("/api/books")
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    @GetMapping("/api/books/recent")
    public List<Book> findRecentBooks() {
        return bookRepository.findRecentBooks();
    }

    @GetMapping("/api/book/dummy")
    public Book getDummyBook() {
        return new Book();
    }

    @PostMapping("/api/books")
    public ResponseEntity<Void> addMovie(@RequestBody Book book) {
        try {
            bookRepository.save(book);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
