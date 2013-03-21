/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.models.Statut;
import ch.comem.services.GroupeManagerLocal;
import ch.comem.services.MembreManagerLocal;
import ch.comem.services.MissionManagerLocal;
import ch.comem.services.PhotoManagerLocal;
import ch.comem.services.VideoManagerLocal;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author bastieneichenberger
 */
@WebService
@Stateless
public class PopulateDB implements PopulateDBLocal {
    @EJB
    private VideoManagerLocal videoManager;
    @EJB
    private PhotoManagerLocal photoManager;
    @EJB
    private MissionManagerLocal missionManager;
    @EJB
    private MembreManagerLocal membreManager;
    @EJB
    private GroupeManagerLocal groupeManager;

    @Override
    public void populateDB() {
        
       
        
        Long idBastien = membreManager.createMembre("eichenberger", "bastien", "bastien@gmail.com", 1);
        // ajout d'un groupe faculatif
        Long idDarko = membreManager.createMembre("ursewitch", "darko", "darkMir@gmail.com", 4);
        
        
        Long idGrpPorn = groupeManager.createGroupe(idBastien, "pornColloc");
        Long idGrpClasse = groupeManager.createGroupe(idBastien, "class mm 39");
        
        membreManager.addGroupe(idGrpClasse, idBastien);
        membreManager.addGroupe(idGrpClasse, idDarko);
        
        Long idPhoto = photoManager.createPhoto("nuages", "http://nuage.jpg", "http://vignetteURL");
        Long idVideo = videoManager.createVideo("viveLavie", "http://video.mov", 12.3);
        
        
        Date adj = new Date();
        Date dateFin = addDays(adj, 1);
        
        Long idManger = missionManager.createMission("manger à la cafèt sans mourrir", "aller manger à la cafèt", adj, dateFin, 12, Statut.ENCOURS, "manger", idPhoto, idBastien, idDarko);
        Long idTest = missionManager.createMission("test", "mission test", adj, dateFin, 12, Statut.TERMINE, "manger", idVideo, idDarko, idBastien);
        
    }
    
    public void deleteMembre(Long id){
        membreManager.deleteMembre(id);
    }
    
    @Override
    public void deleteMission(Long id) {
        missionManager.deleteMission(id);
    }
    
    @Override
    public void deleteGroupe(Long id) {
        //groupeManager.deleteGroupe(id);
    }
    
    @Override
    public void updateMembre(Long id) {
          membreManager.updateMembre(id, "modification", "modif", "modific", 10);
    }
      
    @Override
    public void updateGroupe(Long id) {
        groupeManager.updateGroupe(id, "nouveauTitre");
    } 
    
    @Override
    public void updateMission(Long id) {
        missionManager.updateMission(id, "nvlleMission", "nvlleMission", "nvlleMission");
    }
    
    public Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
     }

}
