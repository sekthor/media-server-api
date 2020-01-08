package ch.sekthor.mediaserverapi.servicelayer;

import ch.sekthor.mediaserverapi.persitence.Movie;
import ch.sekthor.mediaserverapi.persitence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieEndpoint {

    @Autowired
    MovieRepository movieRepository;

    /**
     * Returns every movie in the database
     * @author sekthor
     * @return List of all Movies
     */
    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Returns all movies released in a given year
     * @author sekthor
     * @param releaseYear release year
     * @return movies by year
     */
    @GetMapping("/movies/year/{releaseYear}")
    public List<Movie> findMoviesByYear(@PathVariable int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    /**
     * Returns a single movie by it's id. id is a long, from URI-path
     * @author sekthor
     * @param mediaId long mediaId
     * @return Movie with the id given in URI
     */
    @GetMapping("/movie/{mediaId}")
    public Movie getMovieById(@PathVariable Long mediaId) {
        return movieRepository.findById(mediaId).get();
    }

    /**
     * Adds a movie from a POST-request
     * @author sekthor
     * @param movie movie from POST body
     * @return ResponseEntity
     */
    @PostMapping("/movie/add")
    public ResponseEntity<Void> addMovie(@RequestBody Movie movie) {
        try {
            movieRepository.save(movie);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes the movie with the mediaId provided in the path
     * @param movieId long mediaId
     * @return ResponseEntity
     */
    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long movieId) {
        try {
            movieRepository.deleteById(movieId);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/movie/{movieId}")
    public ResponseEntity<Void> putMovie(@PathVariable Long movieId, @RequestBody Movie movie) {

        if (!movieRepository.findById(movieId).isPresent()){
            return ResponseEntity.notFound().build();
        }
        movie.setMediaId(movieId);
        movieRepository.save(movie);
        return ResponseEntity.noContent().build();
    }

    // temporary: for test purposes
    @GetMapping("/movie/dummy")
    public Movie getDummyMovie(){
        return new Movie();
    }

}
