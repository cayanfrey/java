/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import javax.ejb.Local;

/**
 *
 * @author Cayan
 */
@Local
public interface GroupeManagerLocal {

    Long createGroupe(String nom);
    
}
