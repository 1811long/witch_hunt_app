package model.cartes;

import model.evenementPartie.EventType;
import model.joueur.Joueur;
import model.evenementPartie.ObjectMessage;
import model.partie.Partie;

/**
 * Cette classe représente la carte Toad du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class Toad extends RumourCarte {
	/**
	 * Cette méthode est le constructeur de la carte Toad. On utilise les méthodes
	 * set de la classe mère RumourCard pour construire la carte
	 */
	public Toad() {
		setHuntEffet("Reveal another player's identity");
		setWitchEffet("Take next turn");
		setCondition("Rien");
		setNom("Toad");
	}

	/**
	 * Permet de savoir si l'effet Hunt est jouable. L'effet Hunt n'a pas de
	 * condition pour être utilisé
	 *  @param utilisateur L'utilisateur de la carte
	 */
	public boolean huntEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet de savoir si l'effet Witch est jouable. L'effet Witch de cette carte
	 * n'a pas de condition pour être utilisé
	 *  @param utilisateur L'utilisateur de la carte
	 */

	public boolean witchEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet d'utiliser l'effet Hunt de la carte. Consiste à révéler son identité
	 * 
	 * @param utilisateur L'utilisateur de la carte
	 */
	public void appliquerHuntEffet(Joueur utilisateur) {
		utilisateur.revelerCarte(this);
		Partie.getInstance().getTourEnCours().revelerJoueur(utilisateur);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,
				"Joueur " + utilisateur.getIdJoueur() + " viens de jouer la carte " + this.getNom()));
	}

	/**
	 * Permet d'utiliser l'effet Witch de la carte. Consiste à jouer à prendre la
	 * main au prochain tour
	 * 
	 * @param utilisateur L'utilisateur de la carte
	 */
	public void appliquerWitchEffet(Joueur utilisateur) {
		utilisateur.revelerCarte(this);
		Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);
		Partie.getInstance().getTourEnCours().setEstAccuse(false);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,
				"Joueur " + utilisateur.getIdJoueur() + " viens de jouer la carte " + this.getNom()));
	}
}
