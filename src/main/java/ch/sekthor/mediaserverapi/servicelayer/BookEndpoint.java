package ch.sekthor.mediaserverapi.servicelayer;

import ch.sekthor.mediaserverapi.persitence.book.Book;
import ch.sekthor.mediaserverapi.persitence.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BookEndpoint {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book/{mediaId}")
    public Book findBookById(@PathVariable Long mediaId) {
        return bookRepository.findById(mediaId).get();
    }

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/recent")
    public List<Book> findRecentBooks() {
        return bookRepository.findRecentBooks();
    }

    @GetMapping("/book/dummy")
    public Book getDummyBook() {
        return new Book();
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addMovie(@RequestBody Book book) {
        try {
            bookRepository.save(book);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/book/{bookId}")
    public ResponseEntity<Void> putMovie(@PathVariable Long bookId, @RequestBody Book book) {

        if (!bookRepository.findById(bookId).isPresent()){
            return ResponseEntity.notFound().build();
        }
        book.setMediaId(bookId);
        bookRepository.save(book);
        return ResponseEntity.noContent().build();
    }
}
