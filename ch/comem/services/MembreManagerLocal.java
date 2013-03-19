/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Membre;
import javax.ejb.Local;

/**
 *
 * @author Cayan
 */
@Local
public interface MembreManagerLocal {

    Long createMembre(String nom, String prenom, String email, int point);
    
    void updateMembre(Long id, String newNom, String newPrenom, String newEmail, int newPoints);

    void deleteMembre(Long id);

    Membre readMembre(Long id);
    
    Long addGroupe(Long grpId, Long membreId);
    
}
