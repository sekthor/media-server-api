package ch.sekthor.mediaserverapi.persitence.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * from media JOIN book using (media_id) ORDER BY media_id DESC LIMIT 10;", nativeQuery = true)
    List<Book> findRecentBooks();
}
