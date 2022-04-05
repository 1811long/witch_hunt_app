package model.strategy;
/**
 * Interface qui représente les strategies des joueurs virtuels
 * @author SOYLEMEZ Mehmet
 */
public interface Strategy {
	/**
	 * Cette méthode permet aux joueurs virtuels qui possède un objet implémentant cette interface
	 * de jouer automatiquement selon le niveau de difficulté
	 */
    public void jouer();
}
