package controller.guiController;

import model.inputSupport.InterruptableSysIn;
import model.cartes.*;
import model.cartes.EffetType;
import model.joueur.Joueur;
import model.partie.Partie;
import view.carteInputGUI.*;
import view.guiView.GUIPartieView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Le Controleur de la partie en mode GUI.
 * Ce controleur adapte le patron de conception Observer/Observable (Observer la vue partie en mode GUI) pour
 *  recevoir l'action du joueur et effectuer ensuite les traitements, les actions sont
 * (ACCUSER-FAIRE HUNT-RELEVER ID- FAIRE WITCH - JETER CARTE)
 */
public class PartieActionController implements ActionListener {
    /**
     * La vue de la partie en mode GUI
     */
    private GUIPartieView GUIPartieView;

    /**
     * Le constructor de la PartieActionController
     * @param GUIPartieView
     */
    public PartieActionController(GUIPartieView GUIPartieView){
        this.GUIPartieView = GUIPartieView;
        GUIPartieView.addController(this);
    }

    /**
     * Cette méthode permet d'effectuer les traitements selon l'action du joueur (Accuser our Relever ID ou Faire Hunt...)
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        RumourCarte carteClicked = null;
        String effetType = null;
        boolean jouerCarte = false;

        InterruptableSysIn.cancel();

        switch (e.getActionCommand()){
            case "Accuser":
                AccuserUI accuserUI = new AccuserUI();
                accuserUI.setVisible(true);
                accuserUI.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand() == "Valider") {
                            Joueur joueurChoisi = accuserUI.getJoueurChoisi();
                            accuserUI.setVisible(false);
                            Partie.getInstance().getTourEnCours().accuserJoueur(joueurChoisi);
                            Partie.getInstance().getTourEnCours().passerTourSuivant();
                        } else {
                            accuserUI.setVisible(false);
                        }
                    }
                });
                break;
            case "Reveler ID":
                    Partie.getInstance().getTourEnCours().releverID(Partie.getInstance().getTourEnCours().getJoueurEnCours());
                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                break;
            case "Faire Hunt":
                carteClicked = GUIPartieView.getCarteClicked();
                if (carteClicked == null){
                    JOptionPane.showMessageDialog(null,
                            "Vous devez choisir une carte pour faire Hunt!",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }else{
                    jouerCarte = true;
                    effetType = EffetType.HUNT;
                }
                break;
            case "Faire Witch":
                carteClicked = GUIPartieView.getCarteClicked();
                if (carteClicked == null){
                    JOptionPane.showMessageDialog(null,
                            "Vous devez choisir une carte pour faire Witch!",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }else{
                    carteClicked = GUIPartieView.getCarteClicked();
                    jouerCarte = true;
                    effetType = EffetType.WITCH;
                }
                break;
            case "Jeter Carte":{
                    JeterCarteUI jeterCarteUI = new JeterCarteUI();
                    Partie partie = Partie.getInstance();
                    ArrayList<RumourCarte> listeCarteEnCours = partie.
                                                                getTourEnCours().
                                                                getJoueurEnCours().
                                                                getMain().
                                                                getListeCartes();
                    if (listeCarteEnCours.size() == 0){
                        JOptionPane.showMessageDialog(null,
                                "Vous n'avez plus de carte !",
                                "Message",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        return;
                    }else{
                        jeterCarteUI.setVisible(true);
                        final RumourCarte[] carteJetee = new RumourCarte[1];
                        jeterCarteUI.addListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (e.getActionCommand() == "Valider"){
                                    carteJetee[0] = jeterCarteUI.getCarteJetee();
                                    Partie.getInstance().getTourEnCours().setEstDuckingStool(false);
                                    Partie.getInstance().getTourEnCours().getJoueurEnCours().jeterCarte(carteJetee[0]);
                                    Partie.getInstance().getTourEnCours().passerTourSuivant();
                                    jeterCarteUI.setVisible(false);
                                }else{
                                    jeterCarteUI.setVisible(false);
                                }
                            }
                        });
                    }
                break;
            }
            case "Voir Point":{
                Partie.getInstance().getTableauDeBord().afficherPoint();
                break;
            }
        }

        if (jouerCarte){
            generateCarteUI(carteClicked,effetType);
        }
    }

    /**
     * Cette méthode sert à générer la fenêtre (dans le cas où le joueur veut jouer une de ses cartes)
     * pour que le joueur puisse saisir les inputs demandés par la carte
     * @param Une Rumour card
     * @param L'effet de la carte
     */
    public void generateCarteUI(RumourCarte carte, String effetType){

        if (effetType == "Hunt"){
            if (!carte.huntEffetJouable(Partie.getInstance().getTourEnCours().getJoueurEnCours())){
                JOptionPane.showMessageDialog(null,
                        carte.getMessage(),
                        "Pas jouable",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }
        }else{
            if (!carte.witchEffetJouable(Partie.getInstance().getTourEnCours().getJoueurEnCours())){
                JOptionPane.showMessageDialog(null,
                        carte.getMessage(),
                        "Pas jouable",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }
        }

        switch (carte.getNom()){
            case "Angry Mob":{
                AngryMobUI angryMobUI = new AngryMobUI(effetType);
                angryMobUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(angryMobUI,carte);
                break;
            }
            case "Inquisition":{
                InquisitionUI inquisitionUI = new InquisitionUI(effetType);
                inquisitionUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(inquisitionUI,carte);
                break;
            }
            case "Pointed Hat":{
                PointedHatUI pointedHatUI = new PointedHatUI(effetType);
                pointedHatUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(pointedHatUI,carte);
                break;
            }
            case "Hooked Nose":{
                HookedNoseUI hookedNoseUI = new HookedNoseUI(effetType);
                hookedNoseUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(hookedNoseUI,carte);
                break;
            }
            case "Broomstick":{
                BroomStickUI broomStickUI = new BroomStickUI(effetType);
                broomStickUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(broomStickUI, carte);
                break;
            }
            case "Wart":{
                WartUI wartUI = new WartUI(effetType);
                wartUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(wartUI, carte);
                break;
            }
            case "Ducking Stool":{
                DuckingStoolUI duckingStoolUI = new DuckingStoolUI(effetType);
                duckingStoolUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(duckingStoolUI,carte);
                break;
            }
            case "CauIdron":{
                CauIdronUI cauIdronUI = new CauIdronUI(effetType);
                cauIdronUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(cauIdronUI,carte);
                break;
            }
            case "Evil Eye":{
                EvilEyeUI evilEyeUI = new EvilEyeUI(effetType);
                evilEyeUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(evilEyeUI,carte);
                break;
            }
            case "Toad":{
                ToadUI toadUI = new ToadUI(effetType);
                toadUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(toadUI,carte);
                break;
            }
            case "Black Cat":{
                BlackCatUI blackCatUI = new BlackCatUI(effetType);
                blackCatUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(blackCatUI,carte);
                break;
            }
            case "Pet Newt":{
                PetNewtUI petNewtUI = new PetNewtUI(effetType);
                petNewtUI.setVisible(true);
                CarteActionController carteActionController = new CarteActionController(petNewtUI,carte);
                break;
            }
        }
    }


}
