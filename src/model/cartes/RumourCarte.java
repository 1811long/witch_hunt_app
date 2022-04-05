package model.cartes;

import model.joueur.Joueur;

/**
 * Cette classe représente la classe mère de toutes les cartes rumeurs.
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public abstract class RumourCarte {
	/**
	 * Cette attribut représente la description de l'effet Hunt de la carte
	 */
	private String huntEffet;
	/**
	 * Cette attribut représente la description de l'effet Witch de la carte
	 */
	private String witchEffet;
	/**
	 * Cette attribut représente la condition d'utilisation de la carte (si jamais
	 * il y en a)
	 */
	private String condition;
	/**
	 * Cette attribut représente le nom de la carte
	 */
	private String nom;
	/**
	 * Cette attribut
	 * ...............................................................
	 */
	private String message;

	/**
	 * Permet d'obtenir le message
	 * 
	 * @return le message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Permet de définir le message
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Permet d'obtenir l'effet Hunt de la carte
	 * @return L'effet Hunt de la carte sous forme de texte
	 */
	public String getHuntEffet() {
		return huntEffet;
	}
	/**
	 * Permet de définir l'effet Hunt de la carte
	 * @param huntEffet Un texte définissant un effet Hunt
	 */
	public void setHuntEffet(String huntEffet) {
		this.huntEffet = huntEffet;
	}
	/**
	 * Permet d'obtenir l'effet Witch de la carte
	 * @return L'effet Witch de la carte sous forme de texte
	 */
	public String getWitchEffet() {
		return witchEffet;
	}
	/**
	 * Permet de définir l'effet Witch de la carte
	 * @param witchEffet Un texte définissant un effet Witch
	 */
	public void setWitchEffet(String witchEffet) {
		this.witchEffet = witchEffet;
	}
	/**
	 * Permet d'obtenir la condition d'utilisation de la carte
	 * @return La condition (si il y en a) pour pouvoir utiliser la carte sous forme de texte
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * Permet de définir la condition d'utilisation de la carte
	 * @param condition Un texte définissant une condition d'utilisation
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * Permet d'obtenir le nom de la carte
	 * @return Le nom de la carte
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Permet de définir le nom de la carte
	 * @param nom Un texte définissant le nom de la carte
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Méthode abstraite pour savoir si l'effet Hunt est jouable par la carte
	 * @param joueur
	 * @return True si l'effet Hunt est jouable, False sinon
	 */
	public abstract boolean huntEffetJouable(Joueur joueur);

	/**
	 * Méthode abstraite pour savoir si l'effet Witch est jouable par la carte
	 * @param joueur
	 * @return True si l'effet Witch est jouable, False sinon
	 */
	public abstract boolean witchEffetJouable(Joueur joueur);
}
