package view.consoleView;

import model.cartes.RumourCarte;
import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.inputSupport.InputHandle;
import model.partie.Partie;
import model.partie.Tour;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * La vue de la partie en mode Console
 * Cette vue est observée par la ConsolePartieController
 * @author Tran Le Vu Long
 */
public class ConsolePartieView extends Observable implements Observer{

    /**
     * Représente la partie en cours
     */
    private Partie partie;


    public static final String RELEVER_ID = "R";
    public static final String ACCUSER = "A";
    public static final String FAIRE_HUNT = "H";
    public static final String FAIRE_WITCH = "W";
    public static final String JETER_CARTE = "J";

    /**
     * Constructor de la ConsolePartieView
     * @param partie
     */
    public ConsolePartieView(Partie partie){
        this.partie = partie;
        partie.addObserver(this);
    }

    /**
     * La méthode qui permet de mettre à jour la console quand un évènement se produit
     * Les évènements sont par ex : ACCUSER - RELEVER ID - PASSER_TOUR_SUIVANT...
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
            ObjectMessage objectMessage = (ObjectMessage) arg;
            if (objectMessage == null) return;
            switch (objectMessage.getEventType()){
                case EventType.JOUER_CARTE:
                case EventType.JOUEUR_ACCUSER:
                case EventType.JOUEUR_REVELE:
                case EventType.JOUEUR_ELIMINER: {
                    System.out.println();
                    System.out.println(objectMessage.getMessage());
                    break;
                }
                case EventType.JETER_CARTE:{
                    System.out.println();
                    genererCartesEnCours();
                    System.out.println(objectMessage.getMessage());
                    break;
                }
                case EventType.TOUR_DUCKING_STOOL:
                case EventType.TOUR_EVIL_EYE: {
                    setActionsPossibles();
                    System.out.println(objectMessage.getMessage());
                    break;
                }
                case EventType.PASSER_TOUR_SUIVANT: {

                    if (Partie.getInstance().getTourEnCours().estTermine()){
                        System.out.println("Le tour est terminé, joueur " + Partie.getInstance().getTourEnCours().getJoueurGagnant().getIdJoueur()+ " a gagné");
                        if (!Partie.getInstance().estTermine()){
                            System.out.println("Tour numero " + Tour.numeroDeTour + " commence");
                        }else{
                            System.out.println("La partie est terminée");
                        }
                        return;
                    }

                    System.out.println();
                    setJoueurEnCours();
                    System.out.println();
                    genererCartesEnCours();
                    System.out.println();
                    setActionsPossibles();

                    if (!Partie.getInstance().getTourEnCours().getJoueurEnCours().estVirtuel()){
                        this.setChanged();
                        this.notifyObservers(new ObjectMessage(EventType.GET_JOUEUR_ACTION));
                    }


                    break;
                }
            }
    }

    /**
     * Permet d'afficher le joueur en cours
     * @return void
     */
    private void setJoueurEnCours(){
        System.out.println("Joueur " + Partie.getInstance().getTourEnCours().getJoueurEnCours().getIdJoueur() + " est en cours :");
    }

    /**
     * Permet d'afficher les actions possibles de joueur en cours du tour
     * @return void
     */
    private void setActionsPossibles() {
        if (partie.getTourEnCours().estDuckingStool()){
            System.out.println("Taper " + ConsolePartieView.RELEVER_ID + " pour relever votre identité\n" + "Taper " + ConsolePartieView.JETER_CARTE +" pour jeter une carte");
            return;
        }

        if (partie.getTourEnCours().estEvilEye()){
            System.out.println("Taper " + ConsolePartieView.ACCUSER + " pour accuser");
            return;
        }

        if (partie.getTourEnCours().joueurEnCoursEstAccuse()){
            System.out.println("Taper " + ConsolePartieView.RELEVER_ID + " pour relever votre identité \n" + "Taper " + ConsolePartieView.FAIRE_WITCH + " pour jouer une carte Witch");
        }else{
            System.out.println("Taper " + ConsolePartieView.ACCUSER + " pour accuser un joueur \n" + "Taper " + ConsolePartieView.FAIRE_HUNT + " pour jouer une carte Hunt");
        }
    }

    /**
     * Permet de générer les cartes disponibles du joueur en cours
     */
    private void genererCartesEnCours() {
        ArrayList<RumourCarte> listeCartes = Partie.getInstance().getTourEnCours().getJoueurEnCours().getMain().getListeCartes();
        System.out.println("Voici les cartes du joueur " + Partie.getInstance().getTourEnCours().getJoueurEnCours().getIdJoueur());
        new InputHandle().afficherCartes(listeCartes);
    }
}
