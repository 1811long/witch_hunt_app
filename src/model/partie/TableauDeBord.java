package model.partie;

import model.joueur.Joueur;

/**
 * Cette classe d�finit le tableau de bord de la partie, qui impl�mente l'interface Visitor
 * qui permet de visiter le joueur et mettre � jour son point.
 * @author SOYLEMEZ Mehmet
 *
 */
public class TableauDeBord implements Visitor {
	/**
	 * Repr�sente un tableau d'entier de taille 7x7, la valeur � position (i,j) repr�sente le point du joueur (j+1)
     * dans le tour num�ro (i+1)
	 */
    private int[][] tableauDeBord = new int[7][7];


    /**
     * M�thode qui permet de vister un joueur et tirer son point pour mettre � jour le tableau
     * @param joueur
     * @return void
     */
    @Override
    public void visit(Joueur joueur) {
        int point = joueur.getPoint();
        int numeroTour = Partie.getInstance().getTourEnCours().numeroDeTour;
        int idJoueur = joueur.getIdJoueur();

        tableauDeBord[numeroTour - 1][idJoueur - 1] = point;
    }

    /**
     * Permet d'afficher les points des joueurs dans la console
     * @return void
     */
    public void afficherPoint() {
        System.out.println("Tableau de points : ");
        System.out.print("         ");
        int nbreJoueur = Partie.getInstance().getListeJoueur().getNbJoueur();
        for (int i = 1; i <= nbreJoueur; i++) {
            System.out.print("Joueur " + i + " ");
        }
        System.out.println();
        for (int i = 0; i < Tour.numeroDeTour; i++) {
            int s = i + 1;
            System.out.print("Round " + s +"      ");
            for (int j = 0; j < nbreJoueur; j++) {
                System.out.print(tableauDeBord[i][j] +"       ");
            }
            System.out.println();
        }
    }
    
    /**
     * Permet de v�rifier si une partie est termin�
     * @return True si un joueur poss�de 5 points ou plus, False sinon
     */
    public boolean verifierTerminer(){
        int nbrJoueur = Partie.getInstance().getListeJoueur().getNbJoueur();
        for (int i = 0; i < nbrJoueur; i++){
            int totalPoint = 0;
            for (int j = 0; j < Tour.numeroDeTour; j++){
                totalPoint += tableauDeBord[j][i];
            }
            if (totalPoint >= 5) return true;
        }
        return false;
    }


}
