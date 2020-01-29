package ch.sekthor.mediaserverapi.persitence.movie;

import ch.sekthor.mediaserverapi.persitence.Media;
import javax.persistence.Entity;

@Entity
public class Movie extends Media {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
