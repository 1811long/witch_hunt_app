package model.joueur;

import java.util.ArrayList;

/**
 * Cette classe repr�sente la liste des joueurs en cours, elle offre plusieurs
 * m�thodes de plus qu'une collection pour acc�der ou tirer les joueurs qui satisfait certaines conditions de jeu
 *
 * @author SOYLEMEZ Mehmet
 *
 */
public class ListeJoueur {
	/**
	 * Repr�sente la liste des joueurs en utilisant la collection ArrayList
	 */
	private ArrayList<Joueur> listeJoueur = new ArrayList<>();

	/**
	 * Constructeur de la classe ListeJoueur
	 */
	public ListeJoueur() {

	}

	/**
	 * Permet d'obtenir un joueur dans la liste par son num�ro id
	 * @param idJoueur Le num�ro de joueur souhait�
	 * @return Le joueur du num�ro souhait�
	 */
	public Joueur getJoueurParId(int idJoueur) {
		for (Joueur joueur : listeJoueur) {
			if (joueur.getIdJoueur() == idJoueur) {
				return joueur;
			}
		}
		return null;
	}

	/**
	 * Permet d'ajouter un joueur dans la liste de joueurs
	 * @param joueur Le joueur � rajouter
	 * @return void
	 */
	public void ajouterJoueur(Joueur joueur) {
		listeJoueur.add(joueur);
	}

	/**
	 * Permet de supprimer un joueur dans la liste des joueurs
	 * @param joueurSupprime Le joueur � supprimer
	 * @return void
	 */
	public void supprimerJoueur(Joueur joueurSupprime) {
		for (Joueur joueur : listeJoueur) {
			if (joueur.getIdJoueur() == joueurSupprime.getIdJoueur()) {
				listeJoueur.remove(joueur);
				break;
			}
		}
	}

	/**
	 * Permet d'obtenir la liste des joueurs
	 * @return La liste des joueurs
	 */
	public ArrayList<Joueur> getListeJoueur() {
		return listeJoueur;
	}

	/**
	 * Permet de d�finir la liste des joueurs
	 * @param listeJoueur Une liste de joueur
	 * @return void
	 */
	public void setListeJoueur(ArrayList<Joueur> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}

	/**
	 * Permet d'obtenir le nombre de joueur
	 * @return Le nombre de joueur
	 */
	public int getNbJoueur() {
		return this.listeJoueur.size();
	}



}
