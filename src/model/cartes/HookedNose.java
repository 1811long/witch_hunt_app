package model.cartes;

import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.joueur.Joueur;
import model.partie.Partie;

/**
 * Cette classe repr�sente la carte Hooked Nose du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class HookedNose extends RumourCarte {
	/**
	 * Cette m�thode est le constructeur de la carte Hooked Nose. On utilise les
	 * m�thodes set de la classe m�re RumourCard pour construire la carte
	 */
	public HookedNose() {
		setHuntEffet("Choose next player, take a random card from their hand");
		setWitchEffet("Take one card from the player who accused you, take next turn");
		setCondition("Rien");
		setNom("Hooked Nose");
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
	 * Permet d'utiliser l'effet Hunt de la carte. Consiste � choisir un joueur qui jouera au prochain tour et r�cup�rer une carte au hasard dans sa main avant qu'il joue
	 * @param utilisateur L'utilisateur de la carte
	 * @param joueurSuivant Le joueur choisi par l'utilisateur
	 * @param randomCarte Une carte au hasard parmi celles dans la main du joueur choisi
	 */
	public void appliquerHuntEffet(Joueur utilisateur, Joueur joueurSuivant, RumourCarte randomCarte) {

		if (randomCarte != null) {
			utilisateur.getMain().ajouterCarte(randomCarte);
		}
		utilisateur.revelerCarte(this);
		joueurSuivant.getMain().supprimerCarte(randomCarte);
		Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurSuivant);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,
				"Joueur " + utilisateur.getIdJoueur() + " viens de jouer la carte " + this.getNom()));
	}
	/**
	 * Permet d'utiliser l'effet Witch de la carte. Consiste � r�cup�rer une carte parmi celles dans la main du joueur qui a accus� l'utilisateur et jouer au prochain tour
	 * @param utilisateur L'utilisateur de la carte
	 * @param carteChoisie La carte choisi parmi celles de la main du joueur qui a accus� l'utilisateur
	 */
	public void appliquerWitchEffet(Joueur utilisateur, RumourCarte carteChoisie) {
		if (carteChoisie != null) {
			utilisateur.getMain().ajouterCarte(carteChoisie);
			Partie.getInstance().getTourEnCours().getJoueurPrecedent().getMain().supprimerCarte(carteChoisie);
		}

		utilisateur.revelerCarte(this);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,
				"Joueur " + utilisateur.getIdJoueur() + " viens de jouer la carte " + this.getNom()));

		Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);
		Partie.getInstance().getTourEnCours().setEstAccuse(false);
	}

}
