package model.partie;

import model.cartes.RumourCarte;
import model.joueur.Joueur;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe repr�sente la table de jeu, les cartes r�v�l�es ou les cartes jet�es pendant la partie
 * @author SOYLEMEZ Mehmet
 *
 */
public class Table {
	/**
	 * Repr�sente la liste des cartes r�v�l�es
	 */
	private HashMap<RumourCarte, Joueur> cartesRevelees = new HashMap<>();
	/**
	 * Repr�sente la liste des cartes jet�es
	 */
	private ArrayList<RumourCarte> cartesJetees = new ArrayList<>();

	/**
	 * Constructeur de la classe Table
	 */
	public Table() {

	}

	/**
	 * Permet de retirer une carte parmi les cartes r�v�l�es
	 * @param carte Une carte
	 * @return void
	 */
	public void removeCarteRevelee(RumourCarte carte) {
		cartesRevelees.remove(carte);
	}

	/**
	 * Permet d'obtenir la liste des cartes r�v�l�es
	 * @return La liste des cartes r�v�l�es
	 */
	public HashMap<RumourCarte, Joueur> getCartesRevelees() {
		return this.cartesRevelees;
	}

	/**
	 * Permet d'obtenir la liste des cartes jet�es
	 * @return La liste des cartes jet�es
	 */
	public ArrayList<RumourCarte> getCartesJetees() {
		return cartesJetees;
	}

	/**
	 * Permet de r�initialiser les listes de cartes
	 * @return void
	 */
	public void reinitialiser() {
		cartesRevelees = new HashMap<>();
		cartesJetees = new ArrayList<>();
	}

	/**
	 * Permet d'obtenir la liste des cartes r�v�l�es d'un joueur
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
	 * Permet de r�v�ler une carte
	 * @param joueur Un joueur qui veut r�veler la carte
	 * @param carte Une de ses cartes
	 * @return void
	 */
	public void revelerCarte(Joueur joueur, RumourCarte carte) {
		cartesRevelees.put(carte, joueur);
	}

	/**
	 * Permet de jeter une carte
	 * @param carte Une carte jet�e par un joueur
	 * @return void
	 */
	public void jeterCarte(RumourCarte carte) {
		cartesJetees.add(carte);
	}

}
