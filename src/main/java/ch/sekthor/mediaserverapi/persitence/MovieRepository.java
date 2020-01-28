package ch.sekthor.mediaserverapi.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    public List<Movie> findByReleaseYear(@Param("releaseYear") int releaseYear);

    @Query(value = "SELECT * from media JOIN movie using (media_id) ORDER BY media_id DESC LIMIT 10;", nativeQuery = true)
    public List<Movie> findTenRecentMovies();
}
