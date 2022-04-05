package model.cartes;

import model.evenementPartie.EventType;
import model.joueur.Joueur;
import model.evenementPartie.ObjectMessage;
import model.partie.Partie;
/**
 * Cette classe représente la carte Wart du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class Wart extends RumourCarte {
	/**
	 * Cette méthode est le constructeur de la carte Wart. On utilise les méthodes
	 * set de la classe mère RumourCard pour construire la carte
	 */
    public Wart(){
        setHuntEffet("Choose next player");
        setWitchEffet("Take next turn");
        setCondition("Rien");
        setNom("Wart");
    }
    /**
	 * Permet de savoir si l'effet Hunt est jouable. L'effet Hunt n'a pas de
	 * condition pour être utilisé
     *  @param utilisateur L'utilisateur de la carte
	 */
    public boolean huntEffetJouable(Joueur utilisateur){
        return true;
    }
    /**
	 * Permet de savoir si l'effet Witch est jouable. L'effet Witch de cette carte
	 * n'a pas de condition pour être utilisé
     *  @param utilisateur L'utilisateur de la carte
	 */
    public boolean witchEffetJouable(Joueur utilisateur){
        return true;
    }

    /**
     * Permet d'utiliser l'effet Hunt de la carte. Consiste à choisir le prochain joueur qui jouera au prochain tour
     * @param utilisateur L'utilisateur de la carte
     * @param joueurSuivant joueur choisi par l'utilisateur
     */
    public void appliquerHuntEffet(Joueur utilisateur, Joueur joueurSuivant){
        utilisateur.revelerCarte(this);
        Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurSuivant);
        Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,"Joueur " + utilisateur.getIdJoueur()
                        +" viens de jouer la carte " + this.getNom() + " sur joueur " + joueurSuivant.getIdJoueur()
                )
        );
    }
    /**
     * Permet d'utiliser l'effet Witch de la carte. Consiste à jouer au prochain tour
     * @param utilisateur L'utilisateur de la carte
     */
    public void appliquerWitchEffet(Joueur utilisateur){
        utilisateur.revelerCarte(this);
        Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);
        Partie.getInstance().getTourEnCours().setEstAccuse(false);
        Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,"Joueur " + utilisateur.getIdJoueur()
                        +" viens de jouer la carte " + this.getNom()
                )
        );
    }
}
