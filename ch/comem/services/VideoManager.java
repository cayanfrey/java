/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Video;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
public class VideoManager implements VideoManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;


    @Override
    public Long createVideo(String titre, String url, double duree) {
        Video video = new Video();
        video.setTitre(titre);
        video.setUrl(url);
        video.setDuree(duree);
        em.persist(video);
        em.flush();
        return video.getId();
    }

    

}
