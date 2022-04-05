package model.strategy;

import model.joueur.Joueur;
import model.joueur.JoueurVirtuel;
import model.joueur.ListeJoueur;
import model.partie.Partie;
import model.partie.Tour;

import java.util.Random;

/**
 * Cette classe représente le niveau de difficulté facile pour le joueur virtuel
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class EasyMode implements Strategy {
	/**
	 * Représente un joueur virtuel qui utilise cette strategie
	 */
	private JoueurVirtuel joueurVirtuel;

	/**
	 * Méthode Constructeur de la classe EasyMode
	 * @param joueurVirtuel Le joueurVirtuel qui utilise cette strategie
	 */
	public EasyMode(JoueurVirtuel joueurVirtuel) {
		this.joueurVirtuel = joueurVirtuel;
	}

	/**
	 * Cette méthode décrit la stratégie du joueur virtuel. Le joueur virtuel aura
	 * pour seul but d'accuser un autre joueur de facon aléatoire quand il est à son tour, quand il est accusé, il va automatiquement
	 * relever son identité,
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
