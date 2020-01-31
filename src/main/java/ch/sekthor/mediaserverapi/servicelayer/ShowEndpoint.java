package ch.sekthor.mediaserverapi.servicelayer;

import ch.sekthor.mediaserverapi.persitence.show.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ShowEndpoint {

    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @GetMapping("/api/shows")
    public List<TvShow> findAllTVShows() {
        return tvShowRepository.findAll();
    }

    @GetMapping("/api/show/{showId}")
    public TvShow findTvShowById(@PathVariable Long showId){
        return tvShowRepository.findById(showId).get();
    }

    @GetMapping("/api/show/{showId}/{seasonNo}/{episodeNo}")
    public Episode findEpisode(@PathVariable Long showId, @PathVariable int seasonNo, @PathVariable int episodeNo){
        TvShow tvShow = tvShowRepository.findById(showId).get();
        for (Season season : tvShow.getSeasons()) {
            if (season.getSeasonNo() == seasonNo) {
                for (Episode episode : season.getEpisodes()) {
                    if (episode.getEpisodeNo() == episodeNo) {
                        return episode;
                    }
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/shows/recent")
    public List<TvShow> findRecentTvShows() {
        return tvShowRepository.findTenRecentTvShows();
    }

    /**
     * Adds a tv-show from a POST-request
     * @author sekthor
     * @param tvShow tv-show from POST body
     * @return ResponseEntity
     */
    @PostMapping("/api/shows")
    public ResponseEntity<Void> addShow(@RequestBody TvShow tvShow) {
        try {

            // save all episodes and seasons first
            for (Season season : tvShow.getSeasons()){
                for (Episode episode : season.getEpisodes()){
                    episodeRepository.save(episode);
                }
                seasonRepository.save(season);
            }

            tvShowRepository.save(tvShow);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/show/{showId}")
    public ResponseEntity<Void> putMovie(@PathVariable Long showId, @RequestBody TvShow tvShow) {

        if (!tvShowRepository.findById(showId).isPresent()){
            return ResponseEntity.notFound().build();
        }

        // save all episodes and seasons first
        for (Season season : tvShow.getSeasons()){
            for (Episode episode : season.getEpisodes()){
                episodeRepository.save(episode);
            }
            seasonRepository.save(season);
        }
        tvShow.setMediaId(showId);
        tvShowRepository.save(tvShow);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/show/{showId}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long showId) {
        try {
            tvShowRepository.deleteById(showId);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/episode/dummy")
    public Episode getDummyEpisode() {
        return new Episode();
    }

    @GetMapping("/api/season/dummy")
    public Season getDummySeason(){
        return new Season();
    }

    @GetMapping("/api/show/dummy")
    public TvShow getDummyShow(){
        return new TvShow();
    }
}
