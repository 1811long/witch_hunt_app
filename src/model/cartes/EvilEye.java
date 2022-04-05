package model.cartes;

import model.evenementPartie.EventType;
import model.joueur.Joueur;
import model.evenementPartie.ObjectMessage;
import model.partie.Partie;

/**
 * Cette classe repr�sente la carte Evil Eye du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class EvilEye extends RumourCarte {
	/**
	 * Cette m�thode est le constructeur de la carte Evil Eye. On utilise les
	 * m�thodes set de la classe m�re RumourCard pour construire la carte
	 */
	public EvilEye() {
		setHuntEffet("Choose next player, on their turn they must accuse other player than you");
		setWitchEffet("Choose next player, on their turn they must accuse other player than you");
		setCondition("Rien");
		setNom("Evil Eye");
	}

	/**
	 * Permet de savoir si l'effet Hunt est jouable. L'effet Hunt n'a pas de
	 * condition pour �tre utilis�
	 * @param utilisateur L'utilisateur de la carte
	 */
	public boolean huntEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet de savoir si l'effet Witch est jouable. L'effet Witch de cette carte
	 * n'a pas de condition pour �tre utilis�
	 * @param utilisateur L'utilisateur de la carte
	 */
	public boolean witchEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet d'utiliser l'effet Hunt de la carte. Consiste � choisir le prochain
	 * joueur qui devra accuser un autre joueur que l'utilisateur de la carte
	 * 
	 * @param utilisateur L'utilisateur de la carte
	 * @param joueurSuivant Le joueur choisi par l'utilisateur
	 */
	public void appliquerHuntEffet(Joueur utilisateur, Joueur joueurSuivant) {

		utilisateur.revelerCarte(this);

		Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurSuivant);
		Partie.getInstance().getTourEnCours().setEstEvilEye(true);

		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE, "Joueur " + utilisateur.getIdJoueur()
				+ " viens de jouer la carte " + this.getNom() + " sur joueur " + joueurSuivant.getIdJoueur()));
	}

	/**
	 * Permet d'appliquer l'effet Witch de la carte. Consiste � choisir le prochain
	 * joueur qui devra accuser un autre joueur que l'utilisateur de la carte
	 * 
	 * @param utilisateur L'utilisateur de la carte
	 * @param joueurSuivant Le joueur choisi par l'utilisateur
	 */
	public void appliquerWitchEffet(Joueur utilisateur, Joueur joueurSuivant) {
		utilisateur.revelerCarte(this);

		Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurSuivant);
		Partie.getInstance().getTourEnCours().setEstAccuse(false);
		Partie.getInstance().getTourEnCours().setEstEvilEye(true);

		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE, "Joueur " + utilisateur.getIdJoueur()
				+ " viens de jouer la carte " + this.getNom() + " sur joueur " + joueurSuivant.getIdJoueur()));

	}
}
