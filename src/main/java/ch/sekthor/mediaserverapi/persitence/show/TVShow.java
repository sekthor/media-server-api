package ch.sekthor.mediaserverapi.persitence.show;

import ch.sekthor.mediaserverapi.persitence.Media;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class TVShow extends Media {

    @OneToMany
    private List<Season> seasons;

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }
}
