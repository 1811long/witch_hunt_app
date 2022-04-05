package model.strategy;
/**
 * Interface qui repr�sente les strategies des joueurs virtuels
 * @author SOYLEMEZ Mehmet
 */
public interface Strategy {
	/**
	 * Cette m�thode permet aux joueurs virtuels qui poss�de un objet impl�mentant cette interface
	 * de jouer automatiquement selon le niveau de difficult�
	 */
    public void jouer();
}
