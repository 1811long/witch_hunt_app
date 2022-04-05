/**
 * Contient principalement le moteur du jeu
 */
package model.partie;

import model.joueur.Joueur;

/**
 * L'interface visitor qui permet les classes implémentant cette interface de visiter le joueur et
 * effectuer des traitements, dans le contexte de Witch Hunt, c'est le tableau de bord qui implémente cette méthode
 * et il va visiter le joueur pour tirer ses points
 * @author Tran Le Vu Long
 */
public interface Visitor {
	/**
	 * La méthode qui permet de visiter un joueur et effectuer les traitements correspondants
	 * @param joueur
	 */
	public void visit(Joueur joueur);
}
