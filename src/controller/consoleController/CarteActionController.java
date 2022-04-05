package controller.consoleController;

import model.cartes.*;
import model.inputSupport.InputHandle;
import model.joueur.Identite;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;
import model.partie.Partie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Controleur des cartes en mode console.
 * Ce controleur permet de prendre les inputs de joueur (à travers la console) et
 * effectuer l'effet de carte souhaitée (à traver le modèle de carte)
 * @author Tran Le Vu Long , SOYLEMEZ Mehmet
 */
public class CarteActionController {
    /**
     * Représente la carte jouée par le joueur
     */
    private RumourCarte carte;

    /**
     * Représente le type d'effet souhaité par le joueur
     */
    private String effetType;

    /**
     * Représente un objet de la class InputHandle qui permet de faciliter la lecture des données saisies
     * par le joueur
     */
    private InputHandle inputHandle = new InputHandle();

    /**
     * Controleur de carteActionController
     * @param La Rumour Card 
     * @param L'effet possible 
     */
    public CarteActionController(RumourCarte carte, String effetType){
        this.carte = carte;
        this.effetType = effetType;
        try {
            getInputAndMakeEffet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de prendre les entrées demandées par la carte et appliquer cette carte - là
     * @throws IOException
     */
    private void getInputAndMakeEffet() throws IOException {
        Joueur utilisateur = Partie.getInstance().getTourEnCours().getJoueurEnCours();
        switch (carte.getNom()){
            case "Angry Mob" :{
                AngryMob angryMobCarte = (AngryMob) carte;

                if (effetType == EffetType.HUNT){
                    System.out.println("Choisir un joueur parmi les joueur suivants pour le révéler : ");

                    ListeJoueur listeJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie();

                    inputHandle.afficherJoueurs(listeJoueur.getListeJoueur());
                    Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                    angryMobCarte.appliquerHuntEffet(utilisateur,joueurChoisi);
                }else{
                    System.out.println("Vous allez prendre le prochain tour");
                    angryMobCarte.appliquerWitchEffet(utilisateur);
                }

                Partie.getInstance().getTourEnCours().passerTourSuivant();
                break;
            }
            case "Inquisition":{
                Inquisition inquisitionCarte = (Inquisition) carte;
                if (effetType == EffetType.HUNT){
                    System.out.println("Choisir le joueur pour le tour suivant");

                    ListeJoueur listeJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie();
                    inputHandle.afficherJoueurs(listeJoueur.getListeJoueur());
                    Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                    inquisitionCarte.appliquerHuntEffet(utilisateur,joueurChoisi);
                    System.out.println("Le joueur que vous venez de choisir c'est un " + joueurChoisi.getIdentite());
                }else{
                    System.out.println("Vous devez jetez une carte parmi vos cartes suivantes");
                    ArrayList<RumourCarte> listeCarte = Partie.getInstance().getTourEnCours().getJoueurEnCours().getMain().getListeCartes();
                    inputHandle.afficherCartes(listeCarte);
                    RumourCarte carteJetee = inputHandle.getCarteChoisie(listeCarte);

                    inquisitionCarte.appliquerWitchEffet(utilisateur,carteJetee);
                }
                Partie.getInstance().getTourEnCours().passerTourSuivant();
                break;
            }
            case "Pointed Hat":{
                PointedHat pointedHatCarte = (PointedHat) carte;
                if (effetType == EffetType.HUNT) {
                    System.out.println("Choisir une carte parmi vos cartes révélées suivantes pour reprendre");

                    ArrayList<RumourCarte> listeCarteRevelee = Partie.getInstance().getTable().getCartesRevelees(utilisateur);
                    inputHandle.afficherCartes(listeCarteRevelee);
                    RumourCarte carteChoisie = inputHandle.getCarteChoisie(listeCarteRevelee);

                    System.out.println("Choisir un joueur pour jouer le tour suivant");

                    ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                    inputHandle.afficherJoueurs(listJoueur);
                    Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                    pointedHatCarte.appliquerHuntEffet(utilisateur,carteChoisie,joueurChoisi);
                }else{
                    System.out.println("Choisir une carte parmi vos cartes révélées suivantes pour reprendre");

                    ArrayList<RumourCarte> listeCarteRevelee = Partie.getInstance().getTable().getCartesRevelees(utilisateur);
                    inputHandle.afficherCartes(listeCarteRevelee);
                    RumourCarte carteChoisie = inputHandle.getCarteChoisie(listeCarteRevelee);

                    pointedHatCarte.appliquerWitchEffet(utilisateur,carteChoisie);
                }
                Partie.getInstance().getTourEnCours().passerTourSuivant();
                break;
            }
            case "Hooked Nose":{
                    HookedNose hookedNoseCarte = (HookedNose) carte;
                    if (effetType == EffetType.HUNT) {
                        System.out.println("Choisir un joueur pour jouer le tour suivant");

                        ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                        inputHandle.afficherJoueurs(listJoueur);
                        Joueur joueurChoisi = inputHandle.getJoueurChoisi();
                        System.out.println("Vous pouvez prendre une carte aléatoire parmi ses cartes");

                        ArrayList<RumourCarte> listeCartes = joueurChoisi.getMain().getListeCartes();
                        inputHandle.afficherCartes(listeCartes);
                        RumourCarte carteChoisie = inputHandle.getCarteChoisie(listeCartes);

                        hookedNoseCarte.appliquerHuntEffet(utilisateur,joueurChoisi,carteChoisie);
                    }else{
                        System.out.println("Vous pouvez voler une carte de joueur qui vous a accusé");
                        ArrayList<RumourCarte> listeCartes = Partie.getInstance().getTourEnCours().getJoueurPrecedent().getMain().getListeCartes();

                        inputHandle.afficherCartes(listeCartes);
                        RumourCarte carteChoisie = inputHandle.getCarteChoisie(listeCartes);

                        hookedNoseCarte.appliquerWitchEffet(utilisateur,carteChoisie);
                    }
                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                    break;
            }
            case "Broomstick":{
                    BroomStick broomStickCarte = (BroomStick) carte;
                    if (effetType == EffetType.HUNT) {
                        System.out.println("Choisir un joueur pour jouer le tour suivant");
                        ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                        inputHandle.afficherJoueurs(listJoueur);
                        Joueur joueurChoisi = inputHandle.getJoueurChoisi();
                        broomStickCarte.appliquerHuntEffet(utilisateur,joueurChoisi);
                    }else{
                        System.out.println("Vous allez prendre le prochain tour");
                        broomStickCarte.appliquerWitchEffet(utilisateur);
                    }
                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                    break;
            }
            case "Wart": {
                    Wart wartCarte = (Wart) carte;
                    if (effetType == EffetType.HUNT) {
                        System.out.println("Choisir un joueur pour jouer le tour suivant");

                        ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                        inputHandle.afficherJoueurs(listJoueur);
                        Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                        wartCarte.appliquerHuntEffet(utilisateur,joueurChoisi);
                    }else{
                        System.out.println("Vous allez prendre le prochain tour");
                        wartCarte.appliquerWitchEffet(utilisateur);
                    }
                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                    break;
            }
            case "Ducking Stool":{
                    DuckingStool duckingStoolCarte = (DuckingStool) carte;
                    if (effetType == EffetType.HUNT){
                        System.out.println("Choisir un joueur pour jouer le tour suivant");

                        ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                        inputHandle.afficherJoueurs(listJoueur);
                        Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                        duckingStoolCarte.appliquerHuntEffet(utilisateur,joueurChoisi);
                    }else{
                        System.out.println("Choisir un joueur pour jouer le tour suivant");

                        ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                        inputHandle.afficherJoueurs(listJoueur);
                        Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                        duckingStoolCarte.appliquerWitchEffet(utilisateur,joueurChoisi);
                    }
                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                    break;
            }
            case "CauIdron":{
                    CauIdron cauIdronCarte = (CauIdron) carte;
                    if (effetType == EffetType.HUNT){
                        System.out.println("Vous avez décidé de révéler votre identité");
                        cauIdronCarte.appliquerHuntEffet(utilisateur);
                        if (utilisateur.getIdentite() == Identite.Witch){
                            int idJoueurSuivant = utilisateur.getIdJoueur() - 1;
                            if (idJoueurSuivant == 0) idJoueurSuivant = Partie.getInstance().getListeJoueur().getNbJoueur();
                            Partie.getInstance().getTourEnCours().setJoueurEnCours(idJoueurSuivant);
                        }else{
                            System.out.println("Choisir un joueur pour jouer le tour suivant");

                            ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                            inputHandle.afficherJoueurs(listJoueur);
                            Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                            Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurChoisi);
                        }
                    }else{
                        System.out.println("Le joueur qui vous a accusé va jeter une carte dans sa main");
                        cauIdronCarte.appliquerWitchEffet(utilisateur);
                    }
                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                    break;
            }
            case "Evil Eye":{
                    EvilEye evilEyeCarte = (EvilEye) carte;
                    System.out.println("Choisir un joueur pour jouer le tour prochain");

                    ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                    inputHandle.afficherJoueurs(listJoueur);
                    Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                    if (effetType == EffetType.HUNT){
                        evilEyeCarte.appliquerHuntEffet(utilisateur,joueurChoisi);
                    }else{
                        evilEyeCarte.appliquerWitchEffet(utilisateur,joueurChoisi);
                    }

                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                    break;
            } case "Toad": {
                Toad toadCarte = (Toad) carte;
                if (effetType == EffetType.HUNT) {
                    System.out.println("Vous avez décidé de révéler votre identité");
                    toadCarte.appliquerHuntEffet(utilisateur);
                    if (utilisateur.getIdentite() == Identite.Witch) {
                        int idJoueurSuivant = utilisateur.getIdJoueur() - 1;
                        if (idJoueurSuivant == 0) idJoueurSuivant = Partie.getInstance().getListeJoueur().getNbJoueur();
                        Partie.getInstance().getTourEnCours().setJoueurEnCours(idJoueurSuivant);
                    } else {
                        System.out.println("Choisir un joueur pour jouer le tour suivant");

                        ArrayList<Joueur> listJoueur = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
                        inputHandle.afficherJoueurs(listJoueur);
                        Joueur joueurChoisi = inputHandle.getJoueurChoisi();

                        Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurChoisi);
                    }
                } else {
                    toadCarte.appliquerWitchEffet(utilisateur);
                }

                Partie.getInstance().getTourEnCours().passerTourSuivant();
                break;
            } case "Black Cat":{
                BlackCat blackCatCarte = (BlackCat) carte;

                if (effetType == EffetType.HUNT){
                    System.out.println("Vous pouvez ajouter une des cartes jetées dans votre main");

                    ArrayList<RumourCarte> listeCartes = Partie.getInstance().getTable().getCartesJetees();
                    inputHandle.afficherCartes(listeCartes);
                    RumourCarte carteChoisie = inputHandle.getCarteChoisie(listeCartes);

                    blackCatCarte.appliquerHuntEffet(utilisateur,carteChoisie);
                }else{
                    blackCatCarte.appliquerWitchEffet(utilisateur);
                }

                Partie.getInstance().getTourEnCours().passerTourSuivant();
                break;
            } case "Pet Newt":{
                PetNewt petNewtCarte = (PetNewt) carte;

                if (effetType == EffetType.HUNT){
                    HashMap<RumourCarte,Joueur> hash = Partie.getInstance().getTable().getCartesRevelees();
                    ArrayList<RumourCarte> listeCartes = new ArrayList<>(hash.keySet());

                    System.out.println("Vous pouvez prendre une des cartes révélées suivantes");
                    inputHandle.afficherCartes(listeCartes);
                    RumourCarte carteChoisie = inputHandle.getCarteChoisie(listeCartes);

                    petNewtCarte.appliquerHuntEffet(utilisateur,carteChoisie);
                }else{
                    petNewtCarte.appliquerWitchEffet(utilisateur);
                }

                Partie.getInstance().getTourEnCours().passerTourSuivant();
                break;
            }
        }
    }


}
