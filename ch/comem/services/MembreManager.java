/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Membre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cayan
 */
@Stateless
public class MembreManager implements MembreManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Long createMembre(String nom, String prenom, String email, int point) {
        Membre membre = new Membre();
        membre.setNom(nom);
        membre.setPrenom(prenom);
        membre.setEmail(email);
        membre.setPoint(point);
        em.persist(membre);
        em.flush();
        return membre.getId();
    }

    

    
}
