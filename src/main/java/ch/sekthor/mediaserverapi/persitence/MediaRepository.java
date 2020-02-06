package ch.sekthor.mediaserverapi.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    public List<Media> findByReleaseYear(@Param("releaseYear") int releaseYear);

    @Query("SELECT m FROM Media m WHERE ?1 member of m.tags")
    public List<Media> findByTag(String tag);

    @Query("SELECT m FROM Media m WHERE m.title LIKE %?1%")
    public List<Media> findBySearchQuery(String query);

}
