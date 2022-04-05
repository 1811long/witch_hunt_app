package model.partie;

import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.joueur.Identite;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;

import java.util.HashMap;

/**
 * Cette classe repr�sente le tour de jeu dans la partie
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class Tour {

	/**
	 * Repr�sente le num�ro du tour
	 */
	public static int numeroDeTour = 1;

	/**
	 * Repr�sente le joueur en cours
	 */
	private Joueur joueurEnCours;

	/**
	 * Repr�sente le joueur qui pr�c�de le joueur en cours
	 */
	private Joueur joueurPrecedent;

	/**
	 * Repr�sente le joueur ayant remport� le tour
	 */
	private Joueur joueurGagnant;

	/**
	 * Repr�sente l'�tat du joueur en cours, s'il est accus� alors True, sinon False
	 */
	private boolean estAccuse;

	/**
	 * Repr�sente si le joueur pr�c�dent a joueur la carte Ducking Stool
	 */
	private boolean estDuckingStool = false;

	/**
	 * Repr�sente si le joueur pr�c�dent a joueur la carte Evil Eye
	 */
	private boolean estEvilEye = false;

	/**
	 * Repr�sente la liste des joueurs en vie
	 */
	private ListeJoueur listeJoueurEnVie;

	/**
	 * Permet de savoir si un joueur est r�v�l� ou non
	 */
	private HashMap<Joueur, Boolean> estRevele = new HashMap<>();


	/**
	 * Permet de savoir si le joueur pr�c�dent a jou� la carte Ducking Stool
	 * @return True si oui, False sinon
	 */
	public boolean estDuckingStool() {
		return estDuckingStool;
	}

	/**
	 *  Permet de savoir si le joueur pr�c�dent a jou� la carte Evil Eye
	 * @return True si oui, False sinon
	 */
	public boolean estEvilEye() {
		return this.estEvilEye;
	}

	/**
	 * Permet de r�initialiser un tour pour le recommencer
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
	 * Permet d'obtenir le joueur ayant remport� le tour
	 * @return Le joueur ayant remport� le tour
	 */
	public Joueur getJoueurGagnant() {
		return this.joueurGagnant;
	}

	/**
	 * Permet d'initializer le tour suivant comme un tour Ducking Stool - soit le joueur suivant r�v�le son identit�,
	 * soit il jete une de sa carte
	 * @param b true ou false
	 * @return void
	 */
	public void setEstDuckingStool(boolean b) {
		this.estDuckingStool = b;
		if (b) {
			Partie.getInstance().update(new ObjectMessage(EventType.TOUR_DUCKING_STOOL,
					"Soit vous r�v�lez votre identit�, soit vous jetez une carte"));
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
	 * Permet de d�finir l'�tat de joueur suivant - est accus� ou non ?
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
	 * Permet de d�finir le joueur en cours selon son num�ro
	 * 
	 * @param idJoueursEnCours Num�ro du joueur en cours
	 * @return void
	 */
	public void setJoueurEnCours(int idJoueursEnCours) {
		Joueur joueurSuivant = Partie.getInstance().getListeJoueur().getJoueurParId(idJoueursEnCours);
		this.joueurPrecedent = this.joueurEnCours;
		this.joueurEnCours = joueurSuivant;
		Partie.getInstance().update(new ObjectMessage(EventType.PASSER_TOUR_SUIVANT));
	}

	/**
	 * Permet de r�v�ler le r�le d'un joueur dans la liste des joueurs
	 * @param joueur Le joueur r�v�l�
	 * @return void
	 */
	public void revelerJoueur(Joueur joueur) {
		estRevele.replace(joueur, true);
		Partie.getInstance().update(new ObjectMessage(EventType.JOUEUR_REVELE,
				"Joueur " + joueur.getIdJoueur() + " est r�v�l�" + " et il est un " + joueur.getIdentite()));
	}

	/**
	 * Permet d'accuser un joueur et mettre le joueur accus� comme le joueur suivant
	 * @param joueur Le joueur � accuser
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
	 * Permet d'�liminer un joueur
	 * 
	 * @param joueur Le joueur � �liminer
	 * @return void
	 */
	public void eleminerJoueur(Joueur joueur) {
		listeJoueurEnVie.supprimerJoueur(joueur);
		Partie.getInstance().update(
				new ObjectMessage(EventType.JOUEUR_ELIMINER, "Joueur " + joueur.getIdJoueur() + " est éliminé"));
	}

	/**
	 * Permet de passer au tour suivant
	 * @return void
	 */
	public void passerTourSuivant() {
		Partie.getInstance().update(new ObjectMessage(EventType.PASSER_TOUR_SUIVANT));
	}

	/**
	 * Permet de r�v�ler l'identit� d'un joueur
	 * 
	 * @param joueur Le joueur qui doit r�v�ler son r�le
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
	 * Permet de savoir si un tour est termin�e
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
	 * Permet d'obtenir le nombre de joueur non r�v�l�
	 * 
	 * @return Le nombre de joueur non r�v�l�
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
	 * Permet d'obtenir le joueur pr�c�dent
	 * 
	 * @return Le joueur pr�c�dent
	 */
	public Joueur getJoueurPrecedent() {
		return joueurPrecedent;
	}

	/**
	 * Permet de savoir si un joueur est r�v�l�
	 * 
	 * @param joueur Un joueur
	 * @return True si il est r�v�l�, non sinon
	 */
	public boolean joueurEstRevele(Joueur joueur) {
		return estRevele.get(joueur);
	}

	/**
	 * Permet d'initialiser la liste des joueurs r�v�l�s
	 * 
	 * @param listeJoueurEnVie Une liste de joueur avec leur �tat r�v�l�
	 */
	public void initializeEstReveleHashMap(ListeJoueur listeJoueurEnVie) {
		for (Joueur joueur : listeJoueurEnVie.getListeJoueur()) {
			estRevele.put(joueur, false);
		}
	}

	/**
	 * Permet de savoir si le joueur en cours est accus�
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
