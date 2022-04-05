package model.cartes;

import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.joueur.Identite;
import model.joueur.Joueur;
import model.partie.Partie;

/**
 * Cette classe représente la carte Angry Mob du jeu Witch Hunter
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class AngryMob extends RumourCarte {

	/**
	 * Cette méthode est le constructeur de la carte Angry Mob. On utilise les
	 * méthodes set de la classe mère RumourCard pour construire la carte
	 */
	public AngryMob() {
		setHuntEffet("Reveal another player's identity");
		setWitchEffet("Take next turn");
		setCondition("Only playable if you has been revealed as a Villager");
		setNom("Angry Mob");
	}

	/**
	 * Permet de savoir si l'effet Hunt est jouable. Cette méthode vérifie si le rôle
	 * du joueur a été révélé
	 * @param utilisateur utilisateur de cette carte
	 */
	public boolean huntEffetJouable(Joueur utilisateur) {
		if (utilisateur.getIdentite() != Identite.Villager
				|| !Partie.getInstance().getTourEnCours().joueurEstRevele(utilisateur)) {
			setMessage("Vous n'Ãªtes pas rÃ©vÃ©lÃ© comme un Villager pour jouer cette carte");
			return false;
		}
		if (Partie.getInstance().getTourEnCours().getJoueurAccusable().getNbJoueur() == 0) {
			setMessage("Il n'y a plus de joueur accusables !");
			return false;
		}
		return true;
	}

	/**
	 * Permet de savoir si l'effet Witch est jouable.
	 * @param utilisateur utilisateur de cette carte
	 */
	public boolean witchEffetJouable(Joueur utilisateur) {
		return true;
	}

	/**
	 * Permet d'utiliser l'effet Hunt de la carte. 
	 * Consiste à révéler le rôle d'un joueur du jeu
	 * 
	 * @param utilisateur Le joueur qui utilise cette carte
	 * @param joueurAffecte Le joueur qu'on souhaite révéler
	 */
	public void appliquerHuntEffet(Joueur utilisateur, Joueur joueurAffecte) {
		utilisateur.revelerCarte(this);
		Partie.getInstance().getTourEnCours().revelerJoueur(joueurAffecte);

		if (joueurAffecte.getIdentite() == Identite.Witch) {
			utilisateur.ajouterPoint(2);
			Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);
		} else {
			joueurAffecte.ajouterPoint(2);
			Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurAffecte);
		}

		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE, "Joueur " + utilisateur.getIdJoueur()
				+ " viens de jouer la carte " + this.getNom() + " sur joueur " + joueurAffecte.getIdJoueur()));
	}

	/**
	 * Permet d'utiliser l'effet Witch de la carte.
	 * Consiste à prendre la main au prochain tour
	 * 
	 * @param utilisateur Le joueur qui utilise cette carte
	 */
	public void appliquerWitchEffet(Joueur utilisateur) {
		utilisateur.revelerCarte(this);

		Partie.getInstance().getTourEnCours().setEstAccuse(false);
		Partie.getInstance().getTourEnCours().setJoueurEnCours(utilisateur);

		Partie.getInstance().update(new ObjectMessage(EventType.JOUER_CARTE,
				"Joueur " + utilisateur.getIdJoueur() + " viens de jouer la carte " + this.getNom()));
	}
}
