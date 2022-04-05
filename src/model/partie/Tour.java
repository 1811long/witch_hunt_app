package model.partie;

import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.joueur.Identite;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;

import java.util.HashMap;

/**
 * Cette classe représente le tour de jeu dans la partie
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class Tour {

	/**
	 * Représente le numéro du tour
	 */
	public static int numeroDeTour = 1;

	/**
	 * Représente le joueur en cours
	 */
	private Joueur joueurEnCours;

	/**
	 * Représente le joueur qui précède le joueur en cours
	 */
	private Joueur joueurPrecedent;

	/**
	 * Représente le joueur ayant remporté le tour
	 */
	private Joueur joueurGagnant;

	/**
	 * Représente l'état du joueur en cours, s'il est accusé alors True, sinon False
	 */
	private boolean estAccuse;

	/**
	 * Représente si le joueur précédent a joueur la carte Ducking Stool
	 */
	private boolean estDuckingStool = false;

	/**
	 * Représente si le joueur précédent a joueur la carte Evil Eye
	 */
	private boolean estEvilEye = false;

	/**
	 * Représente la liste des joueurs en vie
	 */
	private ListeJoueur listeJoueurEnVie;

	/**
	 * Permet de savoir si un joueur est révélé ou non
	 */
	private HashMap<Joueur, Boolean> estRevele = new HashMap<>();


	/**
	 * Permet de savoir si le joueur précédent a joué la carte Ducking Stool
	 * @return True si oui, False sinon
	 */
	public boolean estDuckingStool() {
		return estDuckingStool;
	}

	/**
	 *  Permet de savoir si le joueur précédent a joué la carte Evil Eye
	 * @return True si oui, False sinon
	 */
	public boolean estEvilEye() {
		return this.estEvilEye;
	}

	/**
	 * Permet de réinitialiser un tour pour le recommencer
	 * @return void
	 */
	public void reinitialiser() {
		this.joueurEnCours = this.joueurGagnant;
		this.joueurPrecedent = null;
		this.estAccuse = false;
		estRevele = new HashMap<>();
		estDuckingStool = false;
		estEvilEye = false;

		this.listeJoueurEnVie = new ListeJoueur();

		for (Joueur joueur : Partie.getInstance().getListeJoueur().getListeJoueur()) {
			joueur.supprimerToutesCartes();
			this.listeJoueurEnVie.ajouterJoueur(joueur);
		}
		initializeEstReveleHashMap(listeJoueurEnVie);
	}

	/**
	 * Constructeur de la classe Tour
	 * 
	 * @param joueurEnCours Le joueur en cours
	 * @param listeJoueurEnVie La liste des joueurs en vie
	 */
	public Tour(Joueur joueurEnCours, ListeJoueur listeJoueurEnVie) {
		this.joueurEnCours = joueurEnCours;
		this.joueurPrecedent = null;
		this.estAccuse = false;

		this.listeJoueurEnVie = new ListeJoueur();
		for (Joueur joueur : listeJoueurEnVie.getListeJoueur()) {
			this.listeJoueurEnVie.ajouterJoueur(joueur);
		}

		initializeEstReveleHashMap(listeJoueurEnVie);
	}

	/**
	 * Permet d'obtenir le joueur ayant remporté le tour
	 * @return Le joueur ayant remporté le tour
	 */
	public Joueur getJoueurGagnant() {
		return this.joueurGagnant;
	}

	/**
	 * Permet d'initializer le tour suivant comme un tour Ducking Stool - soit le joueur suivant révèle son identité,
	 * soit il jete une de sa carte
	 * @param b true ou false
	 * @return void
	 */
	public void setEstDuckingStool(boolean b) {
		this.estDuckingStool = b;
		if (b) {
			Partie.getInstance().update(new ObjectMessage(EventType.TOUR_DUCKING_STOOL,
					"Soit vous révélez votre identité, soit vous jetez une carte"));
		}
	}

	/**
	 * Permet d'initializer le tour suivant comme un tour Evil Eye
	 * @param b true ou false
	 * @return void
	 */
	public void setEstEvilEye(boolean b) {
		this.estEvilEye = b;
		if (b) {
			Partie.getInstance().update(new ObjectMessage(EventType.TOUR_EVIL_EYE,
					"Vous devez accuser si possible un autre joueur que joueur " + joueurPrecedent.getIdJoueur()));
		}
	}

	/**
	 * Permet de définir l'état de joueur suivant - est accusé ou non ?
	 * @param b True si oui False sinon
	 * @return void
	 */
	public void setEstAccuse(boolean b) {
		this.estAccuse = b;
	}

	/**
	 * Permet de mettre un joueur comme le joueur en cours
	 * @param joueurEnCours
	 * @return void
	 */
	public void setJoueurEnCours(Joueur joueurEnCours) {
		this.joueurPrecedent = this.joueurEnCours;
		this.joueurEnCours = joueurEnCours;
	}

	/**
	 * Permet d'obtenir la liste des joueurs en vie
	 * 
	 * @return La liste des joueurs en vie
	 */
	public ListeJoueur getListeJoueurEnVie() {
		return this.listeJoueurEnVie;
	}

	/**
	 * Permet de définir le joueur en cours selon son numéro
	 * 
	 * @param idJoueursEnCours Numéro du joueur en cours
	 * @return void
	 */
	public void setJoueurEnCours(int idJoueursEnCours) {
		Joueur joueurSuivant = Partie.getInstance().getListeJoueur().getJoueurParId(idJoueursEnCours);
		this.joueurPrecedent = this.joueurEnCours;
		this.joueurEnCours = joueurSuivant;
		Partie.getInstance().update(new ObjectMessage(EventType.PASSER_TOUR_SUIVANT));
	}

	/**
	 * Permet de révéler le rôle d'un joueur dans la liste des joueurs
	 * @param joueur Le joueur révélé
	 * @return void
	 */
	public void revelerJoueur(Joueur joueur) {
		estRevele.replace(joueur, true);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUEUR_REVELE,
				"Joueur " + joueur.getIdJoueur() + " est révélé" + " et il est un " + joueur.getIdentite()));
	}

	/**
	 * Permet d'accuser un joueur et mettre le joueur accusé comme le joueur suivant
	 * @param joueur Le joueur à accuser
	 * @return void
	 */
	public void accuserJoueur(Joueur joueur) {
		this.joueurPrecedent = this.joueurEnCours;
		this.joueurEnCours = joueur;
		this.estAccuse = true;
		if (this.estEvilEye == true)
			this.estEvilEye = false;
		Partie.getInstance().update(new ObjectMessage(EventType.JOUEUR_ACCUSER,
				"Joueur " + joueurPrecedent.getIdJoueur() + " viens d'accuser joueur " + joueur.getIdJoueur()));
	}

	/**
	 * Permet d'éliminer un joueur
	 * 
	 * @param joueur Le joueur à éliminer
	 * @return void
	 */
	public void eleminerJoueur(Joueur joueur) {
		listeJoueurEnVie.supprimerJoueur(joueur);
		Partie.getInstance().update(
				new ObjectMessage(EventType.JOUEUR_ELIMINER, "Joueur " + joueur.getIdJoueur() + " est Ã©liminÃ©"));
	}

	/**
	 * Permet de passer au tour suivant
	 * @return void
	 */
	public void passerTourSuivant() {
		Partie.getInstance().update(new ObjectMessage(EventType.PASSER_TOUR_SUIVANT));
	}

	/**
	 * Permet de révéler l'identité d'un joueur
	 * 
	 * @param joueur Le joueur qui doit révéler son rôle
	 * @return void
	 */
	public void releverID(Joueur joueur) {
		revelerJoueur(joueur);
		if (estDuckingStool == true) {
			estDuckingStool = false;
		}

		if (joueur.getIdentite() == Identite.Witch) {
			joueurPrecedent.ajouterPoint(1);
			eleminerJoueur(joueur);
			this.joueurEnCours = joueurPrecedent;
			this.joueurPrecedent = joueur;
			this.estAccuse = false;
		} else {
			if (estDuckingStool)
				joueurPrecedent.ajouterPoint(-1);
			this.joueurEnCours = joueur;
			this.estAccuse = false;
		}
	}

	/**
	 * Permet de savoir si un tour est terminée
	 * 
	 * @return True si il reste qu'un seul joueur en vie, False sinon
	 */
	public boolean estTermine() {
		if (getNbJoueurNonRevelee() == 1) {
			for (Joueur joueur : listeJoueurEnVie.getListeJoueur()) {
				if (estRevele.get(joueur) == false) {
					joueurGagnant = joueur;
					if (joueurGagnant.getIdentite() == Identite.Witch) {
						joueurGagnant.ajouterPoint(2);
					} else {
						joueurGagnant.ajouterPoint(1);
					}
					joueurPrecedent = null;
					joueurEnCours = joueurGagnant;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Permet d'obtenir le nombre de joueur non révélé
	 * 
	 * @return Le nombre de joueur non révélé
	 */
	public int getNbJoueurNonRevelee() {
		int res = 0;
		for (Joueur joueur : estRevele.keySet()) {
			if (!estRevele.get(joueur))
				res++;
		}
		return res;
	}

	/**
	 * Permet d'obtenir le joueur en cours
	 * 
	 * @return Le joueur en cours
	 */
	public Joueur getJoueurEnCours() {
		return joueurEnCours;
	}

	/**
	 * Permet d'obtenir le joueur précédent
	 * 
	 * @return Le joueur précédent
	 */
	public Joueur getJoueurPrecedent() {
		return joueurPrecedent;
	}

	/**
	 * Permet de savoir si un joueur est révélé
	 * 
	 * @param joueur Un joueur
	 * @return True si il est révélé, non sinon
	 */
	public boolean joueurEstRevele(Joueur joueur) {
		return estRevele.get(joueur);
	}

	/**
	 * Permet d'initialiser la liste des joueurs révélés
	 * 
	 * @param listeJoueurEnVie Une liste de joueur avec leur état révélé
	 */
	public void initializeEstReveleHashMap(ListeJoueur listeJoueurEnVie) {
		for (Joueur joueur : listeJoueurEnVie.getListeJoueur()) {
			estRevele.put(joueur, false);
		}
	}

	/**
	 * Permet de savoir si le joueur en cours est accusé
	 * 
	 * @return True si oui, False sinon
	 */
	public boolean joueurEnCoursEstAccuse() {
		return this.estAccuse;
	}

	/**
	 * Permet d'obtenir la liste des joueurs accusables
	 * 
	 * @return Une liste des joueurs accusables
	 */
	public ListeJoueur getJoueurAccusable() {
		ListeJoueur listeJoueurAccusable = new ListeJoueur();
		int nbJoueurNonRevelee = getNbJoueurNonRevelee();

		for (Joueur joueur : listeJoueurEnVie.getListeJoueur()) {
			if (joueur != null && !estRevele.get(joueur) && joueur.getIdJoueur() != joueurEnCours.getIdJoueur()) {
				if (estEvilEye) {
					if (nbJoueurNonRevelee >= 3) {
						if (joueur.getIdJoueur() == joueurPrecedent.getIdJoueur())
							continue;
						else
							listeJoueurAccusable.ajouterJoueur(joueur);
					} else {
						listeJoueurAccusable.ajouterJoueur(joueur);
					}
				} else {
					listeJoueurAccusable.ajouterJoueur(joueur);
				}
			}
		}
		return listeJoueurAccusable;
	}
}
