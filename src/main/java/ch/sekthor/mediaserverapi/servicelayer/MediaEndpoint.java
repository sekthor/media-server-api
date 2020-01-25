package ch.sekthor.mediaserverapi.servicelayer;

import ch.sekthor.mediaserverapi.persitence.Media;
import ch.sekthor.mediaserverapi.persitence.MediaRepository;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MediaEndpoint {

    @Autowired
    MediaRepository mediaRepository;

    @GetMapping("/media")
    public List<Media> findAllMedia() {
        return mediaRepository.findAll();
    }

    @GetMapping("/media/tag/{tag}")
    public List<Media> findMediaByTag(@PathVariable String tag){
        return mediaRepository.findByTag(tag);

        /*
        Session session = em.unwrap(Session.class);
        MultiIdentifierLoadAccess<Media> multiLoadAccess = session.byMultipleIds(Media.class);
        List<Media> persons = multiLoadAccess.multiLoad();
         */
    }


    /*
    @PostMapping("/media/{mediaId}/addtag")
    public ResponseEntity<Void> addTag(@PathVariable Long mediaId, @RequestBody Tag tag) {
        try{
            Media media = mediaRepository.findById(mediaId).get();
            media.addTag(tag);
            mediaRepository.save(media);
            return ResponseEntity.accepted().build();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }*/
}
