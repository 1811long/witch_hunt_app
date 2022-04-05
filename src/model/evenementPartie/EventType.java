package model.evenementPartie;
/**
 * Cette classe décrit les différents évènements qui peuvent se produire pendant le jeu
 * @author SOYLEMEZ Mehmet
 *
 */
public class EventType {
	/**
	 * Attribut passer au tour suivant
	 */
    public static final String PASSER_TOUR_SUIVANT = "Passe tour";
    /**
     * Attribut joueur révélé
     */
    public static final String JOUEUR_REVELE = "Joueur revele";
    /**
     * Attribut jouer une carte
     */
    public static final String JOUER_CARTE = "Jouer carte";
    /**
     * Attribut tour de Ducking Stool
     */
    public static final String TOUR_DUCKING_STOOL = "Tour Ducking Stool";
    /**
     * Attribut Tour Evil Eye
     */
    public static final String TOUR_EVIL_EYE = "Evil Eye";
    /**
     * Attribut Tour Jeter une carte
     */
    public static final String JETER_CARTE = "Jeter Carte";
    /**
     * Attribut Joueur éliminé 
     */
    public static final String JOUEUR_ELIMINER = "Joueur Elimine";
    /**
     * Attribut Jouer accuser
     */
    public static final String JOUEUR_ACCUSER = "Joueur Accuser";
    /**
     * Attribut obtenir le joueur en action
     */
    public static final String GET_JOUEUR_ACTION = "Get Joueur Action";
    /**
     * Attribut La partie est terminé
     */
    public static final String PARTIE_TERMINE = "Partie est termine";
}
