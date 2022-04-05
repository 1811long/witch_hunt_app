package model.partie;

import model.cartes.RumourCarte;
import model.joueur.Joueur;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe représente la table de jeu, les cartes révélées ou les cartes jetées pendant la partie
 * @author SOYLEMEZ Mehmet
 *
 */
public class Table {
	/**
	 * Représente la liste des cartes révélées
	 */
	private HashMap<RumourCarte, Joueur> cartesRevelees = new HashMap<>();
	/**
	 * Représente la liste des cartes jetées
	 */
	private ArrayList<RumourCarte> cartesJetees = new ArrayList<>();

	/**
	 * Constructeur de la classe Table
	 */
	public Table() {

	}

	/**
	 * Permet de retirer une carte parmi les cartes révélées
	 * @param carte Une carte
	 * @return void
	 */
	public void removeCarteRevelee(RumourCarte carte) {
		cartesRevelees.remove(carte);
	}

	/**
	 * Permet d'obtenir la liste des cartes révélées
	 * @return La liste des cartes révélées
	 */
	public HashMap<RumourCarte, Joueur> getCartesRevelees() {
		return this.cartesRevelees;
	}

	/**
	 * Permet d'obtenir la liste des cartes jetées
	 * @return La liste des cartes jetées
	 */
	public ArrayList<RumourCarte> getCartesJetees() {
		return cartesJetees;
	}

	/**
	 * Permet de réinitialiser les listes de cartes
	 * @return void
	 */
	public void reinitialiser() {
		cartesRevelees = new HashMap<>();
		cartesJetees = new ArrayList<>();
	}

	/**
	 * Permet d'obtenir la liste des cartes révélées d'un joueur
	 * @param joueur Un joueur
	 * @return Sa liste de cartes
	 */
	public ArrayList<RumourCarte> getCartesRevelees(Joueur joueur) {
		ArrayList<RumourCarte> listeCarteRevelees = new ArrayList<>();
		for (RumourCarte carte : cartesRevelees.keySet()) {
			if (cartesRevelees.get(carte).getIdJoueur() == joueur.getIdJoueur()) {
				listeCarteRevelees.add(carte);
			}
		}
		return listeCarteRevelees;
	}

	/**
	 * Permet de révéler une carte
	 * @param joueur Un joueur qui veut réveler la carte
	 * @param carte Une de ses cartes
	 * @return void
	 */
	public void revelerCarte(Joueur joueur, RumourCarte carte) {
		cartesRevelees.put(carte, joueur);
	}

	/**
	 * Permet de jeter une carte
	 * @param carte Une carte jetée par un joueur
	 * @return void
	 */
	public void jeterCarte(RumourCarte carte) {
		cartesJetees.add(carte);
	}

}
