package model.cartes;

import model.evenementPartie.EventType;
import model.joueur.Joueur;
import model.evenementPartie.ObjectMessage;
import model.partie.Partie;

import java.util.ArrayList;
/**
 * Cette classe repr�sente la carte Pointed Hat du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class PointedHat extends RumourCarte {
	/**
	 * Cette m�thode est le constructeur de la carte Pointed Hat.
	 * On utilise les m�thodes set de la classe m�re RumourCard pour construire la carte
	 */
    public PointedHat(){
        setHuntEffet("Take one of your own revealed Rumour Cards into your hand and take next turn");
        setWitchEffet("Take one of your own revealed Rumour Cards into your hand, choose next player");
        setCondition("Only playable if you have a revealed Rumour Card");
        setNom("Pointed Hat");
    }
    /**
     * Permet de savoir si l'effet Hunt est jouable.
     * Cette m�thode contr�le si le joueur a d�j� r�v�l� une Rumour Card
     * @param  utilisateur Le joueur qui utilise cette carte
     */
    public boolean huntEffetJouable(Joueur utilisateur){
        ArrayList<RumourCarte> listeCarteRevelee = Partie.getInstance().getTable().getCartesRevelees(utilisateur);
        if (listeCarteRevelee.size() > 0) return true;
        setMessage("Vous n'avez pas encore de carte revelée pour jouer cette carte");
        return false;
    }
    /**
     * Permet de savoir si l'effet Witch est jouable.
     * Cette m�thode contr�le si le joueur a d�j� r�v�l� une Rumour Card
     * @param  utilisateur Le joueur qui utilise cette carte
     */
    public boolean witchEffetJouable(Joueur utilisateur){
        ArrayList<RumourCarte> listeCarteRevelee = Partie.getInstance().getTable().getCartesRevelees(utilisateur);
        if (listeCarteRevelee.size() > 0) return true;
        setMessage("Vous n'avez pas encore de carte revelée pour jouer cette carte");
        return false;
    }
    /**
     * Permet d'utiliser l'effet Hunt de la carte.
     * Consiste � r�cup�rer une Rumour Card r�v�l� et choisir le prochain joueur
     * @param  utilisateur Le joueur qui utilise cette carte
     * @param  carteChoisie La carte choisie
     * @param  joueurSuivant Le joueur suivant
     */
    public void appliquerHuntEffet(Joueur utilisateur, RumourCarte carteChoisie, Joueur joueurSuivant){
        utilisateur.revelerCarte(this);

        if (carteChoisie != null){
            utilisateur.getMain().ajouterCarte(carteChoisie);
        }

        Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurSuivant);
        Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,"Joueur " + utilisateur.getIdJoueur()
                        +" viens de jouer la carte " + this.getNom() + " sur joueur " + joueurSuivant.getIdJoueur()
                )
        );
    }
    /**
     * Permet d'utiliser l'effet Witch de la carte.
     * Consiste � r�cup�rer une Rumour Card r�v�l� et devenir le prochain joueur
     * @param utilisateur Le joueur qui utilise cette carte
     * @param carteChoisie La carte que le joueur a choisi
     */
    public void appliquerWitchEffet(Joueur utilisateur, RumourCarte carteChoisie){
        utilisateur.revelerCarte(this);

        if (carteChoisie != null){
            utilisateur.getMain().ajouterCarte(carteChoisie);
        }

        Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);
        Partie.getInstance().getTourEnCours().setEstAccuse(false);

        Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,"Joueur " + utilisateur.getIdJoueur()
                        +" viens de jouer la carte " + this.getNom()
                )
        );
    }
}
