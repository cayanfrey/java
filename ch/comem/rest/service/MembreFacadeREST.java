/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.models.Membre;
import ch.comem.services.MembreManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author bastieneichenberger
 * Controller pour les membres
 * Les erreurs sont gérées par la class DAOExceptionMapper
 * si le code est différent de 200, il y a eu un problème
 */
@Stateless
@Path("membres")
public class MembreFacadeREST {
    
    @EJB
    private MembreManagerLocal membreManager;
    
    /**
     * méthode qui permet de récupérer un Membre avec son ID
     * @param id
     * @return un objet Membre
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Membre getMembre(@PathParam("id") Long id) {
        Membre membre = membreManager.readMembre(id);
        return membre;
    }
    /**
     * méthode qui permet d'ajouter un membre
     * @param membre, membre au format xml ou json
     * @return le membre créé
     */ 
    @POST
    @Consumes({"application/xml", "application/json"}) //mimeType
    @Produces({"application/xml", "application/json"})
    public Membre addMembre(Membre membre) {
        Long id = membreManager.createMembre(membre.getNom(), membre.getPrenom(), membre.getEmail(), 0);
        Membre membreAjoute = membreManager.readMembre(id);
        return membreAjoute;
    }
    /**
     * méthode qui permet de supprimer un membre
     * @param id
     * @return 200 OK si tout est ok, sinon une erreur 404 NOT_FOUND si le membre existait pas
     */
    @DELETE
    @Path("{id}")
    public Response delMembre(@PathParam("id") Long id){
        membreManager.deleteMembre(id);
        return Response.status(200).entity("le membre a bien été supprimé").build();
    }
    
    
    /**
     * méthode qui permet de mettre à jour un membre
     * @param membre, un objet membre à mettre à jour avec son id
     * @return le membre mis à jour
     */
    @PUT
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Membre updateMembre(Membre membre) {
        Long idMembre = membre.getId();
        membreManager.updateMembre(idMembre, membre.getNom(), membre.getPrenom(), membre.getEmail(), 0);
        Membre updateMembre = membreManager.readMembre(idMembre);
        return updateMembre;
    }
   
}
