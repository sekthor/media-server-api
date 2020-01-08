package ch.sekthor.mediaserverapi;

import ch.sekthor.mediaserverapi.persitence.Movie;
import ch.sekthor.mediaserverapi.persitence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MediaServerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaServerApiApplication.class, args);
    }

}
