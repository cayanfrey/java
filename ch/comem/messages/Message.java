/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.messages;

/**
 *
 * @author bastieneichenberger
 */
public class Message {

    private static TypeStatut statut;

    public static TypeStatut getStatut() {
        return statut;
    }

    public static void setStatut(TypeStatut statut) {
        Message.statut = statut;
    }

    public enum TypeStatut {

        objetValide,
        objetInexistant,
        objetCree,
        objetMemorise,
        objetSupprime,
        objetEfface,
        objetModifie,
        objetEdite,
        erreur;
    }
}

