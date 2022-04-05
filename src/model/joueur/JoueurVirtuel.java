package model.joueur;

import model.strategy.Strategy;

/**
 * Cette classe repr�sente un joueur virtuel
 * @author SOYLEMEZ Mehmet
 *
 */
public class JoueurVirtuel extends Joueur{

	/**
	 * Repr�sente la strat�gie affect�e pour ce joueur.
	 */
    private Strategy strategy;

    /**
     * Constructeur de la classe JoueurVirtuel
     * @param idJoueur Le num�ro du joueur
     * @param identite Le r�le du joueur
     * @param strategy choix de la strat�gie - Difficle ou Facile
     */
    public JoueurVirtuel(int idJoueur, Identite identite, Strategy strategy) {
        super(idJoueur, identite);
        this.strategy = strategy;
    }

    /**
     * Constructeur de la classe Joueur Virtuel
     * @param idJoueur Le num�ro du joueur
     * @param identite Le r�le du joueur
     */
    public JoueurVirtuel(int idJoueur, Identite identite){
        super(idJoueur, identite);
    }

    /**
     * Permet de d�finir la strat�gie
     * @param strategy La strat�gie souhait�e
     * @return void
     */
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * Permet de savoir si le joueur est virtuel
     * @return True car virtuel
     */
    public boolean estVirtuel(){
        return true;
    }

    /**
     * Permet au joueur virtuel de jouer son tour
     * @return void
     */
    public void jouerSonTour(){
        strategy.jouer();
    }

}
