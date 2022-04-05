package model.cartes;

import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.joueur.Identite;
import model.joueur.Joueur;
import model.partie.Partie;

/**
 * Cette classe représente la carte Inquisition du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class Inquisition extends RumourCarte {
	/**
	 * Cette méthode est le constructeur de la carte Inquisition. On utilise les
	 * méthodes set de la classe mère RumourCard pour construire la carte
	 */
	public Inquisition() {
		setHuntEffet("Choose next player, before their turn secretly look at their identity");
		setWitchEffet("Discard a card from your hand, take next turn");
		setCondition("Only playable if you has been revealed as a Villager");
		setNom("Inquisition");
	}

	/**
	 * Permet de savoir si l'effet Hunt est jouable. Cette méthode vérifie si le
	 * rôle du joueur a été révélé
	 * @param utilisateur L'utilisateur de la carte
	 */
	public boolean huntEffetJouable(Joueur utilisateur) {
		if (utilisateur.getIdentite() == Identite.Villager
				&& Partie.getInstance().getTourEnCours().joueurEstRevele(utilisateur)) {
			return true;
		}
		setMessage("Vous n'Ãªtes pas rÃ©vÃ©lÃ© en tant que Villager pour jouer cette Carte");
		return false;
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
	 * Permet d'utiliser l'effet Hunt de la carte. Consiste à choisir le prochain joueur et de regarder son rôle discrètement 
	 * @param utilisateur L'utilisateur de la carte
	 * @param joueurAffecte Le joueur choisi par l'utilisateur
	 */
	public void appliquerHuntEffet(Joueur utilisateur, Joueur joueurAffecte) {
		utilisateur.revelerCarte(this);
		Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurAffecte);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE, "Joueur " + utilisateur.getIdJoueur()
				+ " viens de jouer la carte " + this.getNom() + " sur joueur " + joueurAffecte.getIdJoueur()));
	}
	/**
	 * Permet d'utiliser l'effet Witch de la carte. Consiste à révéler une carte dans la main de l'utilisateur et de jouer au prochain tour
	 * @param utilisateur L'utilisateur de la carte
	 * @param carteJetee La carte à révéler
	 */
	public void appliquerWitchEffet(Joueur utilisateur, RumourCarte carteJetee) {

		if (carteJetee == this) {
			utilisateur.jeterCarte(this);
		} else {
			utilisateur.revelerCarte(this);
			utilisateur.jeterCarte(carteJetee);
		}

		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,
				"Joueur " + utilisateur.getIdJoueur() + " viens de jouer la carte " + this.getNom()));

		Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);
		Partie.getInstance().getTourEnCours().setEstAccuse(false);
	}
}
