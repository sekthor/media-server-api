package ch.sekthor.mediaserverapi.persitence.show;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Episode {

    @Id @GeneratedValue
    private Long episodeId;
    private int episodeNo;
    private String name;
    private String filePath;

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public int getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(int episodeNo) {
        this.episodeNo = episodeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
