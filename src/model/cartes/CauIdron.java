package model.cartes;

import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.joueur.Joueur;
import model.partie.Partie;

/**
 * Cette classe représente la carte Cauldron du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class CauIdron extends RumourCarte {
	/**
	 * Cette méthode est le constructeur de la carte Cauldron. On utilise les
	 * méthodes set de la classe mère RumourCard pour construire la carte
	 */
	public CauIdron() {
		setHuntEffet("Reveal your identity");
		setWitchEffet("The player who accused you discards from their hand \n take next turn");
		setNom("CauIdron");
	}

	/**
	 * Permet de savoir si l'effet Hunt est jouable. L'effet Hunt n'a pas de
	 * condition pour être utilisé
	 * @param utilisateur L'utilisateur de la carte
	 */
	public boolean huntEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet de savoir si l'effet Witch est jouable. L'effet Witch de cette carte
	 * n'a pas de condition pour être utilisé
	 * @param utilisateur L'utilisateur de la carte
	 */
	public boolean witchEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet d'utiliser l'effet Hunt de la carte. Consiste à révéler son rôle
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
	 * Permet d'utiliser l'effet Witch de la carte. Consiste à révélé une carte dans
	 * la main de celui qui a accusé, puis prend la main au prochain tour
	 * 
	 * @param utilisateur L'utilisateur de la carte
	 */
	public void appliquerWitchEffet(Joueur utilisateur) {
		utilisateur.revelerCarte(this);
		Joueur joueurPrecedent = Partie.getInstance().getTourEnCours().getJoueurPrecedent();

		if (joueurPrecedent.getMain().getListeCartes().size() != 0) {
			joueurPrecedent.jeterCarte(joueurPrecedent.getMain().getListeCartes().get(0));
		}

		Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);
		Partie.getInstance().getTourEnCours().setEstAccuse(false);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,
				"Joueur " + utilisateur.getIdJoueur() + " viens de jouer la carte " + this.getNom()));
	}
}
