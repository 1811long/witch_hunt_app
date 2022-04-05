package controller.consoleController;

import model.cartes.EffetType;
import model.cartes.RumourCarte;
import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.inputSupport.InputHandle;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;
import model.partie.Partie;
import view.consoleView.ConsolePartieView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Controleur de la partie en mode console.
 * Ce controleur adapte le patron de conception Observer/Observable pour observer la console (comme la vue) et
 * gérer les actions souhaitées par le joueur (ACCUSER - RELEVER-ID - FAIRE HUNT - FAIRE WITCH - JETER CARTE...)
 *
 * @author Tran Le Vu Long, SOYLEMEZ Mehmet
 */

public class ConsolePartieActionController implements Observer{

    /**
     * La vue de la Partie en mode Console
     */
    private ConsolePartieView consolePartieView;

    /**
     * Constructeur de ConsolePartieActionController
     * @param consolePartieView
     */
    public ConsolePartieActionController(ConsolePartieView consolePartieView) {
        this.consolePartieView = consolePartieView;
        consolePartieView.addObserver(this);
    }

    /**
     * Lire l'action souhaitée par le joueur et effectuer les actions correspondantes
     * @return void
     * @throws IOException
     */
    public void getAction() throws IOException {

        String saisie = null;
        saisie = new InputHandle().lireChaine();

        if (saisie != null){
            switch (saisie) {
                case ConsolePartieView.ACCUSER :{
                    System.out.println("Choisir un joueur parmi les joueurs suivant pour accuser :");
                    ListeJoueur listeJoueur = Partie.getInstance().getTourEnCours().getJoueurAccusable();
                    for (Joueur joueur: listeJoueur.getListeJoueur()){
                        System.out.print("Joueur " + joueur.getIdJoueur() + " ");
                    }
                    System.out.println();
                    Joueur joueur = new InputHandle().getJoueurChoisi();
                    Partie.getInstance().getTourEnCours().accuserJoueur(joueur);
                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                    break;
                }
                case ConsolePartieView.RELEVER_ID:{
                    System.out.println("Vous avez décidé de révéler votre identité");
                    Joueur joueurEnCours = Partie.getInstance().getTourEnCours().getJoueurEnCours();
                    Partie.getInstance().getTourEnCours().releverID(joueurEnCours);
                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                    break;
                }
                case ConsolePartieView.FAIRE_HUNT:{
                    ArrayList<RumourCarte> listeCartes = Partie.getInstance().getTourEnCours().getJoueurEnCours().getMain().getListeCartes();
                    System.out.println("Choisir une carte parmi vos cartes ci - dessous pour faire Hunt");
                    new InputHandle().afficherCartes(listeCartes);
                    RumourCarte carte = new InputHandle().getCarteChoisie(listeCartes);
                    CarteActionController carteActionController = new CarteActionController(carte,EffetType.HUNT);
                    break;
                }
                case ConsolePartieView.FAIRE_WITCH:{
                    ArrayList<RumourCarte> listeCartes = Partie.getInstance().getTourEnCours().getJoueurEnCours().getMain().getListeCartes();
                    System.out.println("Choisir une carte parmi vos cartes ci - dessous pour faire Witch");
                    new InputHandle().afficherCartes(listeCartes);
                    RumourCarte carte = new InputHandle().getCarteChoisie(listeCartes);

                    CarteActionController carteActionController = new CarteActionController(carte, EffetType.WITCH);
                    break;
                }
            }
        }
    }


    /**
     * Permet de se notifier si le joueur est en cours de saisir les actions souhaitées
     * @param L'élement observable 
     * @param L'argument
     */
    @Override
    public void update(Observable o, Object arg) {
        ObjectMessage objectMessage = (ObjectMessage) arg;
        if (objectMessage == null) return;

        switch (objectMessage.getEventType()){
            case EventType.GET_JOUEUR_ACTION :{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getAction();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            }
        }
    }
}
