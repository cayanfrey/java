/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Photo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
public class PhotoManager implements PhotoManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;


    @Override
    public Long createPhoto(String titre, String url, String vignetteUrl) {
        Photo photo = new Photo();
        photo.setTitre(titre);
        photo.setUrl(url);
        photo.setVignetteUrl(vignetteUrl);
        em.persist(photo);
        em.flush();
        return photo.getId();
    }

    

}
