package ch.sekthor.mediaserverapi.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    public List<Media> findByReleaseYear(@Param("releaseYear") int releaseYear);

    @Query(value = "SELECT m.* FROM media m JOIN media_tags WHERE media_Id = media_media_Id AND tags = 'crime' GROUP BY media_id", nativeQuery = true)
    public List<Media> findByTag(String Tag);


}
