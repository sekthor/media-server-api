package ch.sekthor.mediaserverapi.persitence.show;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {

    @Query(value = "SELECT * from media JOIN tv_show using (media_id) ORDER BY media_id DESC LIMIT 10;", nativeQuery = true)
    public List<TvShow> findTenRecentTvShows();
}
