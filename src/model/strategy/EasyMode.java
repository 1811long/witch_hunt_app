package model.strategy;

import model.joueur.Joueur;
import model.joueur.JoueurVirtuel;
import model.joueur.ListeJoueur;
import model.partie.Partie;
import model.partie.Tour;

import java.util.Random;

/**
 * Cette classe repr�sente le niveau de difficult� facile pour le joueur virtuel
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class EasyMode implements Strategy {
	/**
	 * Repr�sente un joueur virtuel qui utilise cette strategie
	 */
	private JoueurVirtuel joueurVirtuel;

	/**
	 * M�thode Constructeur de la classe EasyMode
	 * @param joueurVirtuel Le joueurVirtuel qui utilise cette strategie
	 */
	public EasyMode(JoueurVirtuel joueurVirtuel) {
		this.joueurVirtuel = joueurVirtuel;
	}

	/**
	 * Cette m�thode d�crit la strat�gie du joueur virtuel. Le joueur virtuel aura
	 * pour seul but d'accuser un autre joueur de facon al�atoire quand il est � son tour, quand il est accus�, il va automatiquement
	 * relever son identit�,
	 */
	@Override
	public void jouer() {
		Tour tour = Partie.getInstance().getTourEnCours();

		if (tour.joueurEnCoursEstAccuse() || tour.estDuckingStool()) {
			tour.releverID(joueurVirtuel);
		} else {
			ListeJoueur listeJoueur = tour.getJoueurAccusable();
			int nbJoueurs = listeJoueur.getNbJoueur();

			Random rand = new Random();
			int idJoueurAccuse = rand.nextInt(nbJoueurs);
			Joueur joueurAccuse = listeJoueur.getListeJoueur().get(idJoueurAccuse);

			tour.accuserJoueur(joueurAccuse);
		}

		tour.passerTourSuivant();
	}
}
