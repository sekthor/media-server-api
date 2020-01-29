package ch.sekthor.mediaserverapi.persitence.show;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Episode {

    @Id @GeneratedValue
    private Long episodeId;
}
