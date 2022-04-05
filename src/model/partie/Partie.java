package model.partie;

import model.cartes.*;
import model.cartes.RumourCarte;
import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * Cette classe représente une partie du jeu Witch Hunter, elle adapte le patron de conception Singleton
 * qui permet de toutes les autres classes d'y accéder, et le patron de conception Observer/Observable pour se mettre à jour
 * quand il y a un évènement qui se produit pendant le jeu.
 * @author SOYLEMEZ Mehmet
 */

public class Partie extends Observable {

    /**
	 * Représente la table dans laquelle on retrouvera les cartes jouées
	 */
    private Table table;

    /**
     * Représente la liste des joueurs dans la partie
     */
    private ListeJoueur listeJoueur;

    /**
     * Représente le tour courant de la partie
     */
    private Tour tourEnCours;

    /**
     * Représente le tableau de bord de la partie
     */
    private TableauDeBord tableauDeBord;

    /**
     * Représente la liste des cartes dans la partie
     */
    private static ArrayList<RumourCarte> listCartes = new ArrayList<RumourCarte>(){
        {
            add(new AngryMob());
            add(new Inquisition());
            add(new PointedHat());
            add(new HookedNose());
            add(new BroomStick());
            add(new Wart());
            add(new DuckingStool());
            add(new CauIdron());
            add(new EvilEye());
            add(new Toad());
            add(new BlackCat());
            add(new PetNewt());
        }
    };

    /**
     * Représente l'unicité d'une partie
     */
    private static Partie instance;

    /**
     * Constructeur de la classe Partie
     * @param listeJoueur La liste des joueurs dans la partie
     */
    public Partie(ListeJoueur listeJoueur) {
        this.listeJoueur = listeJoueur;
        table = new Table();
        tourEnCours = new Tour(listeJoueur.getJoueurParId(1), listeJoueur);
        tableauDeBord = new TableauDeBord();
        instance = this;
    }

    /**
     * Permet de réinitialiser la partie à chaque fois un tour est terminé
     * @return void
     */
    public void reinitialiser(){
        tourEnCours.reinitialiser();
        table.reinitialiser();
    }

    /**
     * Permet d'obtenir le tableau de bord
     * @return Le tableau de bord de la partie
     */
    public TableauDeBord getTableauDeBord(){
        return this.tableauDeBord;
    }

    /**
     * Permet de mettre à jour le nombre de points
     * @return void
     */
    public void updatePoint(){
        for (Joueur joueur: listeJoueur.getListeJoueur()){
            joueur.accept(tableauDeBord);
        }

    }

    /**
     * Permet de notifier les Observers (les vues) pour qu'elles puissent mettre à jour.
     * @param args
     */
    public void update(ObjectMessage args){
        this.setChanged();
        this.notifyObservers(args);
    }

    /**
     * Permet d'obtenir la référence de la partie en cours
     * @return Une référence ver la partie en cours
     */
    public static Partie getInstance() {
        return instance;
    }

    /**
     * Getter de la table
     * @return La table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Permet de définir la table
     * @param table La table
     * @return void
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Permet d'obtenir la liste des joueurs
     * @return La liste des joueurs
     */
    public ListeJoueur getListeJoueur() {
        return listeJoueur;
    }

    /**
     * Permet de définir la liste des joueurs
     * @param listeJoueur Une liste de joueurs
     */
    public void setListeJoueur(ListeJoueur listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    /**
     * Permet d'obtenir le tour en cours
     * @return Le tour en cours
     */
    public Tour getTourEnCours() {
        return tourEnCours;
    }

    /**
     * Permet de distribuer les cartes aux joueurs
     * @return void
     */
    public void distribuerCarte() {
        int nbreJoueur = listeJoueur.getNbJoueur();
        for (int i = 0; i < 12; i++) {
            if (nbreJoueur == 5 && i >= 10) {
                table.jeterCarte(listCartes.get(i));
                continue;
            }
            Joueur joueur = listeJoueur.getListeJoueur().get(i % nbreJoueur);
            RumourCarte carte = listCartes.get(i);
            joueur.getMain().ajouterCarte(carte);
        }
    }

    /**
     * Permet de mélanger les cartes
     * @return void
     */
    public void melangerCarte() {
        Random rand = new Random();
        for (int i = 0; i < 12; i++) {
            int r = i + rand.nextInt(12-i);
            RumourCarte temp = listCartes.get(i);
            listCartes.set(i, listCartes.get(r));
            listCartes.set(r, temp);
        }
    }

    /**
     * Setter de tourEnCours
     * @param tourEnCours Un tour
     * @return void
     */
    public void setTourEnCours(Tour tourEnCours) {
        this.tourEnCours = tourEnCours;
    }

    /**
     * Permet de débuter une partie
     * @return void
     */
    public void commencer(){
        this.melangerCarte();
        this.distribuerCarte();
        this.setChanged();
        this.notifyObservers(new ObjectMessage(EventType.PASSER_TOUR_SUIVANT));
    }

    /**
     * Permet de savoir si une partie est terminée
     * @return True si elle est terminée, False sinon
     */
    public boolean estTermine() {
        return tableauDeBord.verifierTerminer();
    }
}
