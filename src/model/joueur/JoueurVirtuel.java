package model.joueur;

import model.strategy.Strategy;

/**
 * Cette classe représente un joueur virtuel
 * @author SOYLEMEZ Mehmet
 *
 */
public class JoueurVirtuel extends Joueur{

	/**
	 * Représente la stratégie affectée pour ce joueur.
	 */
    private Strategy strategy;

    /**
     * Constructeur de la classe JoueurVirtuel
     * @param idJoueur Le numéro du joueur
     * @param identite Le rôle du joueur
     * @param strategy choix de la stratégie - Difficle ou Facile
     */
    public JoueurVirtuel(int idJoueur, Identite identite, Strategy strategy) {
        super(idJoueur, identite);
        this.strategy = strategy;
    }

    /**
     * Constructeur de la classe Joueur Virtuel
     * @param idJoueur Le numéro du joueur
     * @param identite Le rôle du joueur
     */
    public JoueurVirtuel(int idJoueur, Identite identite){
        super(idJoueur, identite);
    }

    /**
     * Permet de définir la stratégie
     * @param strategy La stratégie souhaitée
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
