package model.cartes;

import model.evenementPartie.EventType;
import model.joueur.Joueur;
import model.evenementPartie.ObjectMessage;
import model.partie.Partie;

/**
 * Cette classe représente la carte Ducking Stool du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class DuckingStool extends RumourCarte {
	/**
	 * Cette méthode est le constructeur de la carte Ducking Stool. On utilise les
	 * méthodes set de la classe mère RumourCard pour construire la carte
	 */
	public DuckingStool() {
		setHuntEffet("Choose next player \n, they must reveal their identity or discard a card from their hand");
		setWitchEffet("Choose next player");
		setCondition("Rien");
		setNom("Ducking Stool");
	}

	/**
	 * Permet de savoir si l'effet Hunt est jouable. L'effet Hunt n'a pas de
	 * condition pour être utilisé
	 * @param utilisateur L'utilisateur de la carte
	 * @return boolean
	 */
	public boolean huntEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet de savoir si l'effet Witch est jouable. L'effet Witch de cette carte
	 * n'a pas de condition pour être utilisé
	 * @param utilisateur L'utilisateur de la carte
	 * @return boolean
	 */
	public boolean witchEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet d'utiliser l'effet Hunt de la carte. Consiste à choisir un joueur. Le
	 * joueur choisi devra révéler son identité ou révéler une Rumour Card de sa
	 * main
	 * 
	 * @param utilisateur L'utilisateur de la carte
	 * @param joueurSuivant Le joueur choisi par l'utilisateur
	 */
	public void appliquerHuntEffet(Joueur utilisateur, Joueur joueurSuivant) {
		utilisateur.revelerCarte(this);

		Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurSuivant);
		Partie.getInstance().getTourEnCours().setEstDuckingStool(true);

		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE, "Joueur " + utilisateur.getIdJoueur()
				+ " viens de jouer la carte " + this.getNom() + " sur joueur " + joueurSuivant.getIdJoueur()));
	}
	/**
	 * Permet d'utiliser l'effet Witch de la carte. Consiste à choisir le prochain joueur qui jouera au tour suivant
	 * @param utilisateur L'utilisateur de la carte
	 * @param joueurSuivant Le joueur choisi par l'utilisateur
	 */
	public void appliquerWitchEffet(Joueur utilisateur, Joueur joueurSuivant) {
		utilisateur.revelerCarte(this);

		Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurSuivant);
		Partie.getInstance().getTourEnCours().setEstAccuse(false);

		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE, "Joueur " + utilisateur.getIdJoueur()
				+ " viens de jouer la carte " + this.getNom() + " sur joueur " + joueurSuivant.getIdJoueur()));
	}
}
