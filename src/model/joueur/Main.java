package model.joueur;

import model.cartes.RumourCarte;
import model.partie.Partie;

import java.util.ArrayList;
/**
 * Cette classe représente la main d'un joueur
 * @author SOYLEMEZ Mehmet
 *
 */
public class Main {

	/**
	 * Représente la liste des cartes en main
	 */
    private ArrayList<RumourCarte> listeCartes = new ArrayList<>();

    /**
     * Constructeur de la classe Main
     */
    public Main(){
    }

    /**
     * Constructeur de la classe Main
     * @param listeCartes Une liste de cartes
     */
    public Main (ArrayList<RumourCarte> listeCartes){
        this.listeCartes = listeCartes;
    }

    /**
     * Permet d'obtenir la liste des cartes en main d'un joueur
     * @return listeCartes la liste de cartes d'un joueur
     */
    public ArrayList<RumourCarte> getListeCartes(){
        return listeCartes;
    }

    /**
     * Permet de définir les cartes en main
     * @param listeCartes
     * @return void
     */
    public void setMain(ArrayList<RumourCarte> listeCartes){
        this.listeCartes = listeCartes;
    }

    /**
     * Permet de rajouter des cartes dans la main
     * @param carte Une carte à rajouter
     * @return void
     */
    public void ajouterCarte(RumourCarte carte){
        this.listeCartes.add(carte);
    }

    /**
     * Permet d'obtenir le nombre de cartes en main
     * @return Le nombre de carte en main
     */
    public int getNbCartes(){
        return listeCartes.size();
    }

    /**
     * Permet de supprimer une carte en main
     * @param carte La carte à supprimer
     * @return void
     */
    public void supprimerCarte(RumourCarte carte){
        this.listeCartes.remove(carte);
    }

    /**
     * Permet de jeter une carte en main
     * @param carte La carte à jeter
     * @return void
     */
    public void jeterCarte(RumourCarte carte){
        supprimerCarte(carte);
        Partie.getInstance().getTable().jeterCarte(carte);
    }


}
