package model.joueur;


import model.cartes.RumourCarte;
import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.partie.Partie;
import model.partie.Visitor;

/**
 * Cette classe représente un joueur 
 * @author SOYLEMEZ Mehmet
 *
 */
public class Joueur {
	/**
	 * Représente le numéro du joueur
	 */
    private int idJoueur;
    /**
     * Représente le rôle du joueur
     */
    private Identite identite;
    /**
     * Représente les cartes en main du joueur
     */
    private Main main;
    /**
     * Représente les points du joueur
     */
    private int point;
    
    /**
     * Constructeur de la classe Joueur
     * @param idJoueur Le numéro du joueur
     * @param identite L'identité du joueur, Witch ou Villager
     */
    public Joueur(int idJoueur, Identite identite){
        this.idJoueur = idJoueur;
        this.identite = identite;
        this.point = 0;
        this.main = new Main();
    }

    /**
     * Getter de la main
     * @return la main de joueur
     */
    public Main getMain() {
        return main;
    }
    /**
     * Setter de la main
     * @param main main a affecter
     * @return void
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Permet de rajouter des points au joueur
     * @param points Nombre de points
     * @return void
     */
    public void ajouterPoint(int points){
        this.point += points;
        Partie.getInstance().updatePoint();
    }

    /**
     * Permet de supprimer toutes les cartes en main du joueur
     * @return void
     */
    public void supprimerToutesCartes(){
        main = new Main();
    }

    /**
     * Permet d'obtenir le numéro du joueur
     * @return Le numéro du joueur
     */
    public int getIdJoueur() {
        return idJoueur;
    }

    /**
     * Permet de définir le numéro du joueur
     * @param idJoueur Le numéro souhaité
     * @return void
     */
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    /**
     * Permet d'obtenir le rôle du joueur
     * @return Le rôle du joueur
     */
    public Identite getIdentite() {
        return identite;
    }

    /**
     * Permet de définir le rôle du joueur
     * @param identite
     * @return void
     */
    public void setIdentite(Identite identite) {
        this.identite = identite;
    }

    /**
     * Permet de le tableau de bord de visiter ce joueur et mettre à jour les points
     * @param v une classe qui a droit de visiter ce joueur, cette classe doit implémenter l'interface Visitor
     */
    public void accept (Visitor v){
        v.visit(this);
    }

    /**
     * Permet d'obtenir le nombre de point du joueur
     * @return Le nombre de point
     */
    public int getPoint(){
        return this.point;
    }

    /**
     * Permet de savoir si le joueur est virtuel ou non
     * @return False car non virtuel
     */
    public boolean estVirtuel(){
        return false;
    }

    /**
     * Permet de révéler une carte en main
     * @param carte La carte à révéler
     * @return void
     */
    public void revelerCarte(RumourCarte carte){
        main.supprimerCarte(carte);
        Partie.getInstance().getTable().revelerCarte(this,carte);
    }

    /**
     * Permet de jeter une carte en main sur la table
     * @param carte La carte à jeter
     */
    public void jeterCarte (RumourCarte carte){
        main.jeterCarte(carte);
        Partie.getInstance().update(new ObjectMessage(EventType.JETER_CARTE,
                "Joueur " + idJoueur + " viens de jeter le carte " +
                        carte.getNom())
        );
    }
}
