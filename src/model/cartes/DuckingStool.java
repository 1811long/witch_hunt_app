package model.cartes;

import model.evenementPartie.EventType;
import model.joueur.Joueur;
import model.evenementPartie.ObjectMessage;
import model.partie.Partie;

/**
 * Cette classe repr�sente la carte Ducking Stool du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class DuckingStool extends RumourCarte {
	/**
	 * Cette m�thode est le constructeur de la carte Ducking Stool. On utilise les
	 * m�thodes set de la classe m�re RumourCard pour construire la carte
	 */
	public DuckingStool() {
		setHuntEffet("Choose next player \n, they must reveal their identity or discard a card from their hand");
		setWitchEffet("Choose next player");
		setCondition("Rien");
		setNom("Ducking Stool");
	}

	/**
	 * Permet de savoir si l'effet Hunt est jouable. L'effet Hunt n'a pas de
	 * condition pour �tre utilis�
	 * @param utilisateur L'utilisateur de la carte
	 * @return boolean
	 */
	public boolean huntEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet de savoir si l'effet Witch est jouable. L'effet Witch de cette carte
	 * n'a pas de condition pour �tre utilis�
	 * @param utilisateur L'utilisateur de la carte
	 * @return boolean
	 */
	public boolean witchEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet d'utiliser l'effet Hunt de la carte. Consiste � choisir un joueur. Le
	 * joueur choisi devra r�v�ler son identit� ou r�v�ler une Rumour Card de sa
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
	 * Permet d'utiliser l'effet Witch de la carte. Consiste � choisir le prochain joueur qui jouera au tour suivant
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
