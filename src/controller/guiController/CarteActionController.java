package controller.guiController;

import model.cartes.*;
import model.joueur.Identite;
import model.joueur.Joueur;
import model.partie.Partie;
import view.carteInputGUI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Le controleur des cartes en mode GUI.
 * Ce controleur permet de prendre les inputs de joueur (à travers la vue GUI) et
 * effectuer l'effet de carte souhaitée (à traver le modèle de carte)
 * @author Tran Le Vu Long, SOYLEMEZ Mehmet
 */

public class CarteActionController implements ActionListener {

    /**
     * Représente la fenêtre pour saisir les inputs de la carte jouée.
     * Par ex : La carte Angry Mob, effet Hunt demande de saisir un joueur pour révéler
     */
    private WindowInputUI carteInputUI;

    /**
     * Représente la carte jouée par le joueur
     */
    private RumourCarte carte;

    /**
     * Constructor de la CarteActionController
     * @param carteInputUI
     * @param carte
     */
    public CarteActionController(WindowInputUI carteInputUI, RumourCarte carte){
        this.carteInputUI = carteInputUI;
        this.carte = carte;
        carteInputUI.addListener(this);
    }

    /**
     * Méthode qui permet de demander au joueur de saisir les inputs demandés et d'effectuer
     * l'effet de la carte jouée
     * @param L'évènement réalisé
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Joueur utilisateur = Partie.getInstance().getTourEnCours().getJoueurEnCours();
        switch (e.getActionCommand()){
            case "Valider":{
                switch (carte.getNom()){
                    case "Angry Mob":
                        AngryMobUI angryMobUI = (AngryMobUI) carteInputUI;
                        AngryMob angryMobCarte = (AngryMob) carte;

                        String effetType = angryMobUI.getEffetType();

                        if (effetType == "Hunt"){
                            angryMobCarte.appliquerHuntEffet(utilisateur,angryMobUI.getJoueurChoisi());
                        }else{
                            angryMobCarte.appliquerWitchEffet(utilisateur);
                        }

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        angryMobUI.setVisible(false);
                        break;
                    case "Inquisition":
                        InquisitionUI inquisitionUI = (InquisitionUI) carteInputUI;
                        Inquisition inquisitionCarte = (Inquisition) carte;
                        effetType = inquisitionUI.getEffetType();

                        if (effetType == "Hunt"){
                            inquisitionCarte.appliquerHuntEffet(utilisateur,inquisitionUI.getJoueurChoisi());
                            JOptionPane.showMessageDialog(null,"Le joueur que vous venez de choisir est un " +
                                            inquisitionUI.getJoueurChoisi().getIdentite(),
                                            "Message Inquisition",
                                            JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            inquisitionCarte.appliquerWitchEffet(utilisateur,inquisitionUI.getCarteJetee());
                        }

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        inquisitionUI.setVisible(false);
                        break;
                    case "Pointed Hat":
                        PointedHatUI pointedHatUI = (PointedHatUI) carteInputUI;
                        PointedHat pointedHatCarte = (PointedHat) carte;
                        effetType = pointedHatUI.getEffetType();

                        if (effetType == "Hunt"){
                            pointedHatCarte.appliquerHuntEffet(utilisateur,pointedHatUI.getCarteReveleeChoisie(),pointedHatUI.getJoueurChoisi());
                        }else{
                            pointedHatCarte.appliquerWitchEffet(utilisateur,pointedHatUI.getCarteReveleeChoisie());
                            JOptionPane.showMessageDialog(null,
                                    "Vous allez prendre le prochain tour",
                                    "message",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        pointedHatUI.setVisible(false);
                        break;
                    case "Hooked Nose":
                        HookedNoseUI hookedNoseUI = (HookedNoseUI) carteInputUI;
                        HookedNose hookedNoseCarte = (HookedNose) carte;
                        effetType = hookedNoseUI.getEffetType();

                        if (effetType == "Hunt"){
                            hookedNoseCarte.appliquerHuntEffet(utilisateur,hookedNoseUI.getJoueurChoisi(),hookedNoseUI.getCarteChoisie());
                        }else{
                            hookedNoseCarte.appliquerWitchEffet(utilisateur,hookedNoseUI.getCarteChoisie());
                        }

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        hookedNoseUI.setVisible(false);
                        break;
                    case "Broomstick":
                        BroomStickUI broomStickUI = (BroomStickUI) carteInputUI;
                        BroomStick broomStickCarte = (BroomStick) carte;

                        effetType = broomStickUI.getEffetType();

                        if (effetType == "Hunt"){
                            broomStickCarte.appliquerHuntEffet(utilisateur,broomStickUI.getJoueurChoisi());
                        }else{
                            broomStickCarte.appliquerWitchEffet(utilisateur);
                        }

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        broomStickUI.setVisible(false);
                        break;
                    case "Wart":
                        WartUI wartUI = (WartUI) carteInputUI;
                        Wart wartCarte = (Wart) carte;
                        effetType = wartUI.getEffetType();

                        if (effetType == "Hunt"){
                            wartCarte.appliquerHuntEffet(utilisateur,wartUI.getJoueurChoisi());
                        }else{
                            wartCarte.appliquerWitchEffet(utilisateur);
                        }

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        wartUI.setVisible(false);
                        break;
                    case "Ducking Stool":
                        DuckingStoolUI duckingStoolUI = (DuckingStoolUI) carteInputUI;
                        DuckingStool duckingStoolCarte = (DuckingStool) carte;
                        effetType = duckingStoolUI.getEffetType();

                        if (effetType == "Hunt"){
                            duckingStoolCarte.appliquerHuntEffet(utilisateur,duckingStoolUI.getJoueurChoisi());
                        }else{
                            duckingStoolCarte.appliquerWitchEffet(utilisateur,duckingStoolUI.getJoueurChoisi());
                        }

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        duckingStoolUI.setVisible(false);
                        break;

                    case "CauIdron" :
                        CauIdronUI cauIdronUI = (CauIdronUI) carteInputUI;
                        CauIdron cauIdronCarte = (CauIdron) carte;
                        effetType = cauIdronUI.getEffetType();

                        if (effetType == "Hunt"){
                            cauIdronCarte.appliquerHuntEffet(utilisateur);
                            cauIdronUI.setVisible(false);
                            if (utilisateur.getIdentite() == Identite.Witch){
                                int idJoueurSuivant = utilisateur.getIdJoueur() - 1;
                                if (idJoueurSuivant == 0) idJoueurSuivant = Partie.getInstance().getListeJoueur().getNbJoueur();
                                Partie.getInstance().getTourEnCours().setJoueurEnCours(idJoueurSuivant);
                            }else{
                                cauIdronUI.setVisible(false);
                                ChooseNextPlayerUI chooseNextPlayerUI = new ChooseNextPlayerUI();
                                chooseNextPlayerUI.setVisible(true);
                                final Joueur[] joueurChoisi = new Joueur[1];
                                chooseNextPlayerUI.addListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getActionCommand() == "Valider"){
                                                joueurChoisi[0] = chooseNextPlayerUI.getJoueurChoisi();
                                                Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurChoisi[0]);
                                                Partie.getInstance().getTourEnCours().passerTourSuivant();

                                            chooseNextPlayerUI.setVisible(false);
                                        }else{
                                                chooseNextPlayerUI.setVisible(false);
                                        }
                                    }
                                });
                            }

                        }else{
                            cauIdronCarte.appliquerWitchEffet(utilisateur);
                            cauIdronUI.setVisible(false);
                        }

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        break;
                    case "Evil Eye":
                        EvilEyeUI evilEyeUI = (EvilEyeUI) carteInputUI;
                        EvilEye evilEyeCarte = (EvilEye) carte;
                        effetType = evilEyeUI.getEffetType();

                        if (effetType == "Hunt"){
                            evilEyeCarte.appliquerHuntEffet(utilisateur,evilEyeUI.getJoueurChoisi());
                        }else{
                            evilEyeCarte.appliquerWitchEffet(utilisateur,evilEyeUI.getJoueurChoisi());
                        }
                        evilEyeUI.setVisible(false);

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        break;

                    case "Toad":
                        ToadUI toadUI = (ToadUI) carteInputUI;
                        Toad toadCarte = (Toad) carte;
                        effetType = toadUI.getEffetType();

                        if (effetType == "Hunt"){
                            toadCarte.appliquerHuntEffet(utilisateur);
                            toadUI.setVisible(false);
                            if (utilisateur.getIdentite() == Identite.Witch){
                                int idJoueurSuivant = utilisateur.getIdJoueur() - 1;
                                if (idJoueurSuivant == 0) idJoueurSuivant = Partie.getInstance().getListeJoueur().getNbJoueur();
                                Partie.getInstance().getTourEnCours().setJoueurEnCours(idJoueurSuivant);
                            }else{
                                toadUI.setVisible(false);
                                ChooseNextPlayerUI chooseNextPlayerUI = new ChooseNextPlayerUI();
                                chooseNextPlayerUI.setVisible(true);
                                final Joueur[] joueurChoisi = new Joueur[1];
                                chooseNextPlayerUI.addListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getActionCommand() == "Valider"){
                                            joueurChoisi[0] = chooseNextPlayerUI.getJoueurChoisi();
                                            Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurChoisi[0]);
                                            Partie.getInstance().getTourEnCours().passerTourSuivant();
                                            chooseNextPlayerUI.setVisible(false);
                                        }else{
                                            chooseNextPlayerUI.setVisible(false);
                                        }
                                    }
                                });
                            }
                        }else{
                            toadCarte.appliquerWitchEffet(utilisateur);
                        }
                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        toadUI.setVisible(false);
                        break;
                    case "Black Cat":
                        BlackCatUI blackCatUI = (BlackCatUI) carteInputUI;
                        BlackCat blackCatCarte = (BlackCat) carte;
                        effetType = blackCatUI.getEffetType();
                        if (effetType == "Hunt"){
                            blackCatCarte.appliquerHuntEffet(utilisateur,blackCatUI.getCarteJetee());
                        }else{
                            blackCatCarte.appliquerWitchEffet(utilisateur);
                        }
                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        blackCatUI.setVisible(false);
                        break;
                    case "Pet Newt" :
                        PetNewtUI petNewtUI = (PetNewtUI) carteInputUI;
                        PetNewt petNewtCarte = (PetNewt) carte;
                        effetType = petNewtUI.getEffetType();

                        if (effetType == "Hunt"){
                            petNewtCarte.appliquerHuntEffet(utilisateur,petNewtUI.getCarteReveleeChoisie());
                        }else{
                            petNewtCarte.appliquerWitchEffet(utilisateur);
                        }

                        petNewtUI.setVisible(false);
                        ChooseNextPlayerUI chooseNextPlayerUI = new ChooseNextPlayerUI();
                        chooseNextPlayerUI.setVisible(true);

                        final Joueur[] joueurChoisi = new Joueur[1];
                        chooseNextPlayerUI.addListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (e.getActionCommand() == "Valider"){
                                    chooseNextPlayerUI.setVisible(false);
                                    joueurChoisi[0] = chooseNextPlayerUI.getJoueurChoisi();
                                    Partie.getInstance().getTourEnCours().setJoueurEnCours(joueurChoisi[0]);
                                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                                }else{
                                    chooseNextPlayerUI.setVisible(false);
                                }
                            }
                        });

                        Partie.getInstance().getTourEnCours().passerTourSuivant();
                        break;
                    }
                    break;
            }
            case "Annuler":
                carteInputUI.setVisible(false);
                break;
        }

    }
}