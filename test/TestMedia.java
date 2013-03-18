/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.services.PhotoManagerLocal;
import ch.comem.services.VideoManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
@WebService
public class TestMedia implements TestMediaLocal {
    @EJB
    private PhotoManagerLocal photoManager;
    @EJB
    private VideoManagerLocal videoManager;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addTestMedia(String titre, String url, String vignetteUrl, double duree) {
        for (int i = 0; i < 10; i++) {
            photoManager.createPhoto(titre, url, vignetteUrl);
            videoManager.createVideo(duree);
        }
    }

}
