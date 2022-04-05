package model.cartes;

import model.evenementPartie.EventType;
import model.joueur.Joueur;
import model.evenementPartie.ObjectMessage;
import model.partie.Partie;

/**
 * Cette classe repr�sente la carte Black Cat du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class BlackCat extends RumourCarte {
	/**
	 * Cette m�thode est le constructeur de la carte Black Cat. On utilise les
	 * m�thodes set de la classe m�re RumourCard pour construire la carte
	 */
	public BlackCat() {
		setHuntEffet("Add one discarded to your hand and then discard this card, take next turn");
		setWitchEffet("Take next turn");
		setCondition("Rien");
		setNom("Black Cat");
	}

	/**
	 * Permet de savoir si l'effet Hunt est jouable. L'effet Hunt n'a pas de
	 * condition pour �tre utilis�
	 * @param utilisateur utilisateur de cette Carte
	 */
	public boolean huntEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet de savoir si l'effet Witch est jouable. L'effet Witch de cette carte
	 * n'a pas de condition pour �tre utilis�
	 * @param utilisateur utilisateur de cette Carte
	 */
	public boolean witchEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet d'utiliser l'effet Hunt de la carte. Consiste � r�cup�rer une carte
	 * r�v�l� et de r�v�ler notre carte, puis prendre la main au prochain tour
	 * 
	 * @param utilisateur L'utilisateur de la carte
	 * @param carteChoisie La carte choisie
	 */
	public void appliquerHuntEffet(Joueur utilisateur, RumourCarte carteChoisie) {
		if (carteChoisie != null) {
			utilisateur.getMain().ajouterCarte(carteChoisie);
		}
		utilisateur.jeterCarte(this);
		Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,
				"Joueur " + utilisateur.getIdJoueur() + " viens de jouer la carte " + this.getNom()));
	}

	/**
	 * Permet d'utiliser l'effet Witch de la carte.
	 * Consiste � prendre la main au prochain tour
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
