/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.messages.DaoException;
import ch.comem.models.Membre;
import ch.comem.services.MembreManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author bastieneichenberger
 * Controller pour les membre
 * Attention, les points ne sont pas encore changés
 */
@Stateless
@Path("membres")
public class MembreFacadeREST {
    
    @EJB
    private MembreManagerLocal membreManager;
    
    
    @POST
    @Consumes({"application/xml", "application/json"}) //mimeType
    @Produces({"application/xml", "application/json"})
    public Response addMembre(Membre membre) {
        try{
            membreManager.createMembre(membre.getNom(), membre.getPrenom(), membre.getEmail(), 0);
            return Response.status(200).build();
        }
        catch(DaoException DaoEx){
            return Response.status(404).build();
        }
    }
    // pourquoi le delete ne fonctionne pas ?
    @DELETE
    @Produces({"application/xml", "application/json"})
    @Path("{id}")
    public Response delMembre(@PathParam("id") Long id){
        try{
            membreManager.deleteMembre(id);
            return Response.status(200).build();
        }
        catch(DaoException DaoEx){
            return Response.status(404).build();
        }
    }
    
    
    @PUT
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Response editMembre(Membre membre) {
        try{
            membreManager.updateMembre(membre.getId(), membre.getNom(), membre.getPrenom(), membre.getEmail(), 0);
            return Response.status(200).build();
        }
        catch(DaoException DaoEx){
            return Response.status(404).build();
        }
    }
   
    
}
