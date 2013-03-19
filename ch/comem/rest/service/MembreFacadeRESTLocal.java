/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.models.Membre;
import javax.ejb.Local;
import javax.ws.rs.core.Response;

/**
 *
 * @author bastieneichenberger
 * Ne pas implémenter cette class fait de la bull shit
 */
@Local
public interface MembreFacadeRESTLocal {
    public Response addMembre(Membre membre);
    public Response delMembre(Membre membre);
    public Response editMembre(Membre membre);  
}
