/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.messages.Message;
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

    /**
     * 
     * @param id
     * @param newTitre
     * @param newUrl
     * @param newDuree 
     */
    @Override
    public void updateVideo(Long id, String newTitre, String newUrl, double newDuree) {
        Video retourVideo = em.find(Video.class, id);
        if(retourVideo != null){
            retourVideo.setTitre(newTitre);
            retourVideo.setUrl(newUrl);
            retourVideo.setDuree(newDuree);
            em.persist(retourVideo);
            em.flush();
            Message.setStatut(Message.TypeStatut.OBJETMODIFIE);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        } 
    }
    /**
     * 
     * @param id 
     */
    @Override
    public void removeVideo(Long id) {
        Video retourVideo = em.find(Video.class, id);
        if(retourVideo != null){
            em.remove(retourVideo);
            Message.setStatut(Message.TypeStatut.OBJETSUPPRIME);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        }
    }
    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public Video readVideo(Long id) {
        Video retourVideo = em.find(Video.class, id);
        if(retourVideo != null){
            Message.setStatut(Message.TypeStatut.OBJECTVALIDE);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        }
        return retourVideo;
    }
    
    

    

}
