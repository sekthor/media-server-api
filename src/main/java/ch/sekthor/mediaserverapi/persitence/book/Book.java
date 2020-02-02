package ch.sekthor.mediaserverapi.persitence.book;

import ch.sekthor.mediaserverapi.persitence.Media;

import javax.persistence.Entity;

@Entity
public class Book extends Media {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
