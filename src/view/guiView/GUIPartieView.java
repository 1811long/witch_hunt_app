package view.guiView;

import controller.guiController.PartieActionController;
import model.cartes.RumourCarte;
import model.evenementPartie.EventType;
import model.evenementPartie.ObjectMessage;
import model.joueur.Joueur;
import model.joueur.JoueurVirtuel;
import model.joueur.ListeJoueur;
import model.partie.Partie;
import model.partie.Tour;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe représentant la fenêtre de jeu en mode GUI
 * Cette classe adapte le patron de conception Observer/Observable, en effet elle observe la partie en cours.
 * @author Tran Le Vu Long
 */
public class GUIPartieView extends JFrame implements Observer {

        /**
         * Représente la partie en cours
         */
        private Partie partie;

        /**
         * Représente un panel qui contient les informations du joueur en cours
         */
        private JPanel tablePanel = new JPanel();

        /**
         * Représente un panel qui contient les informations liées au joueur en cours
         */
        private JPanel mainJoueurPanel = new JPanel();

        /**
         * Représente un panel qui contient les cartes disponibles du joueur en cours
         */
        private JPanel carteInfoPanel = new JPanel();

        /**
         * Représente un label qui affiche le numéro de tour en cours
         */
        private JLabel numeroTourPanel = new JLabel("Numero de Tour : 1");

        /**
         * Représente un label qui affiche le joueur en cours
         */
        private JLabel joueurEnCours;

        /**
         * Représente un label qui affiche les actions effectuées par le joueur
         */
        private JLabel status;

        /**
         * Représente la description de l'effet Hunt quand utilisateur clique sur une de ses cartes
         */
        private JTextField huntEffetText = new JTextField("Hunt Effet :");

        /**
         * Représente la description de l'effet Witch quand utilisateur clique sur une de ses cartes
         */
        private JTextField witchEffetText = new JTextField("Witch Effet :");

        /**
         * Représente la description de condition pour jouer une carte quand utilisateur clique sur une de ses cartes
         */
        private JTextField conditionText = new JTextField("Condition : ");

        /**
         * Représente le button "Accuser"
         */
        private JButton accuserButton = new JButton("Accuser");

        /**
         * Représente le button "Faire Hunt"
         */
        private JButton faireHuntButton = new JButton("Faire Hunt");

        /**
         * Représente le button "Relever ID"
         */
        private JButton releverIDButton =  new JButton("Relever Id");

        /**
         * Représente le button "Faire Witch"
         */
        private JButton faireWitchButton = new JButton("Faire Witch");

        /**
         * Représente le button "Jeter Carte"
         */
        private JButton jeterCarteButton = new JButton("Jeter carte");

        /**
         * Représente le button "Voir Point"
         */
        private JButton voirPointButton = new JButton("Voir points");

        /**
         * La liste de cartes de joueur en cours
         */
        private ArrayList<JButton> carteEnCoursButton = new ArrayList<>();

        /**
         * Représente la dernière carte cliquée par le joueur en cours
         */
        private RumourCarte carteClicked = null;

        /**
         * Représente l'effet que le joueur veut faire avec sa carte - Hunt ou Witch
         */
        private String effetType = null;

        /**
         * Représente le font par défaut de l'interface graphique
         */
        public final static Font FONT_DEFAULT = new Font("Tahoma",Font.BOLD,13);

        /**
         * Représente le couleur par défaut de l'interface graphique
         */
        public final static Color COLOR_DEFAULT = new Color(162, 161, 149);


        /**
         * Constructor de l'interface graphique d'une partie
         * @param partie la partie en cours
         * @return void
         */
        GUIPartieView(Partie partie){
            this.partie = partie;
            ListeJoueur joueurs = partie.getListeJoueur();

            for (Joueur joueur : joueurs.getListeJoueur()){
                JoueurUI joueurUI = new JoueurUI(joueur,joueurs.getNbJoueur());
                add(joueurUI);
            }

            partie.addObserver(this);
            initcomponents();
        }

        /**
         * Méthode permet d'ajouter le controleur pour cette vue
         * @param controller
         * @return void
         */
         public void addController (PartieActionController controller){
            faireHuntButton.addActionListener(controller);
            faireWitchButton.addActionListener(controller);
            jeterCarteButton.addActionListener(controller);
            accuserButton.addActionListener(controller);
            releverIDButton.addActionListener(controller);
            voirPointButton.addActionListener(controller);
        }

        /**
         * Méthode permet d'initializer toutes les composantes de la vue graphique
         * @return void
         */
        public void initcomponents(){
            setBounds(200, 0, 1300, 850);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().setBackground(COLOR_DEFAULT);
            setLayout(null);
            setLocationRelativeTo(null);



            tablePanel.setBackground(new Color(150, 195, 163));
            tablePanel.setLayout(null);
            tablePanel.setBounds(300, 179, 703, 404);
            tablePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
            add(tablePanel);

            Font fontText = new Font("arial",0,11);
            huntEffetText.setFont(fontText);
            witchEffetText.setFont(fontText);
            conditionText.setFont(fontText);

            huntEffetText.setEditable(false);
            witchEffetText.setEditable(false);
            conditionText.setEditable(false);
            carteInfoPanel.setBounds(66, 230, 500, 140);
            tablePanel.add(carteInfoPanel);
            carteInfoPanel.setLayout(new BoxLayout(carteInfoPanel, BoxLayout.Y_AXIS));

            carteInfoPanel.add(huntEffetText);
            carteInfoPanel.add(witchEffetText);
            carteInfoPanel.add(conditionText);

            status = new JLabel("");
            status.setBounds(100,35,500,85);
            status.setFont(FONT_DEFAULT);
            add(status);

            joueurEnCours = new JLabel();
            joueurEnCours.setBounds(100, 20, 244, 64);
            joueurEnCours.setFont(FONT_DEFAULT);
            add(joueurEnCours);

            numeroTourPanel.setBounds(1100,20,244,64);
            numeroTourPanel.setFont(FONT_DEFAULT);
            add(numeroTourPanel);

            voirPointButton.setBounds(100,715,105,77);
            voirPointButton.setActionCommand("Voir Point");
            add(voirPointButton);

            accuserButton.setBounds(955, 715, 105, 77);
            accuserButton.setActionCommand("Accuser");
            add(accuserButton);

            faireHuntButton.setBounds(1102, 715, 100, 77);
            faireHuntButton.setActionCommand("Faire Hunt");
            add(faireHuntButton);

            releverIDButton.setBounds(955, 715, 105, 77);
            releverIDButton.setActionCommand("Reveler ID");
            releverIDButton.setVisible(false);
            add(releverIDButton);


            faireWitchButton.setBounds(1102, 715, 100, 77);
            faireWitchButton.setActionCommand("Faire Witch");
            add(faireWitchButton);
            faireWitchButton.setVisible(false);


            jeterCarteButton.setBounds(1102, 715, 100, 77);
            jeterCarteButton.setActionCommand("Jeter Carte");
            add(jeterCarteButton);
            jeterCarteButton.setVisible(false);

        }

        /**
         * Méthode pour mettre à jour quand il y a des évènements qui se produisent par le modèle partie
         * @param o
         * @param arg
         * @return void
         */
        @Override
        public void update(Observable o, Object arg) {
            ObjectMessage objectMessage = (ObjectMessage) arg;
            if (objectMessage == null) return;
            switch (objectMessage.getEventType()){
                case EventType.JOUER_CARTE:
                case EventType.JOUEUR_ACCUSER: {
                    status.setText(objectMessage.getMessage());
                    break;
                }
                case EventType.JOUEUR_REVELE:
                case EventType.JOUEUR_ELIMINER: {
                    if (!Partie.getInstance().getTourEnCours().getJoueurEnCours().estVirtuel()) {
                        JOptionPane.showMessageDialog(null, objectMessage.getMessage(), "Message",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        status.setText(objectMessage.getMessage());
                    }
                    break;
                }
                case EventType.JETER_CARTE:{
                    genererCartesEnCours();
                    if (!Partie.getInstance().getTourEnCours().getJoueurEnCours().estVirtuel()) {
                        JOptionPane.showMessageDialog(null, objectMessage.getMessage(), "Message",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }else{
                        status.setText(objectMessage.getMessage());
                    }
                    break;
                }
                case EventType.TOUR_DUCKING_STOOL:
                case EventType.TOUR_EVIL_EYE: {
                    setActionsPossibles();
                    if (!Partie.getInstance().getTourEnCours().getJoueurEnCours().estVirtuel()){
                        JOptionPane.showMessageDialog(null,objectMessage.getMessage(),"Message",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }else{
                        status.setText(objectMessage.getMessage());
                    }
                    break;
                }
                case EventType.PASSER_TOUR_SUIVANT: {
                    if (Partie.getInstance().getTourEnCours().estTermine()){
                        JOptionPane.showMessageDialog(null,
                                "Joueur " + Partie.getInstance().getTourEnCours().getJoueurGagnant().getIdJoueur() + " est le gagnant de ce tour",
                                "Tour terminé",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        if (!Partie.getInstance().estTermine()){
                            Tour.numeroDeTour++;
                            numeroTourPanel.setText("Numero de Tour " + Tour.numeroDeTour);
                            JOptionPane.showMessageDialog(null,
                                    "Tour numéro " + Tour.numeroDeTour + " commence",
                                    "Tour terminé",
                                    JOptionPane.INFORMATION_MESSAGE);
                            Partie.getInstance().reinitialiser();
                            Partie.getInstance().commencer();
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "La partie est terminée, cliquez button voir point pour voir les points des joueurs",
                                    "Partie terminé",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        return;
                    }
                    setJoueurEnCours();
                    setActionsPossibles();
                    genererCartesEnCours();

                    if (partie.getTourEnCours().getJoueurEnCours().estVirtuel()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                if ((partie.getTourEnCours().getJoueurEnCours() instanceof JoueurVirtuel)) {
                                    JoueurVirtuel joueurVirtuel = (JoueurVirtuel) partie.getTourEnCours().getJoueurEnCours();
                                    joueurVirtuel.jouerSonTour();
                                }
                            }
                        });
                    }
                    break;
                }
            }
            carteClicked = null;
        }

        /**
         * Getter de la dernière carte cliquée par le joueur en cours
         * @return la carte appuyée
         */
        public RumourCarte getCarteClicked(){
            return carteClicked;
        }

        /**
         * Getter de le type de l'effet que le joueur souhaite jouer (Hunt ou Witch)
         * @return effetType
         */
        public String getEffetType(){
            return effetType;
        }


        /**
         * Méthode permet d'installer toutes les actions possibles pour le joueur en cours
         * @return void
         */
        private void setActionsPossibles() {
            if (partie.getTourEnCours().estDuckingStool()){
                releverIDButton.setVisible(true);

                if (partie.getTourEnCours().joueurEstRevele(partie.getTourEnCours().getJoueurEnCours())){
                    releverIDButton.setEnabled(false);
                }else{
                    releverIDButton.setEnabled(true);
                }

                jeterCarteButton.setVisible(true);
                faireWitchButton.setVisible(false);
                accuserButton.setVisible(false);
                faireHuntButton.setVisible(false);
                return;
            }

            if (partie.getTourEnCours().estEvilEye()){
                accuserButton.setVisible(true);
                faireWitchButton.setVisible(false);
                faireHuntButton.setVisible(false);
                releverIDButton.setVisible(false);
                jeterCarteButton.setVisible(false);
                return;
            }

            if (partie.getTourEnCours().joueurEnCoursEstAccuse()){
                releverIDButton.setVisible(true);

                if (partie.getTourEnCours().joueurEstRevele(partie.getTourEnCours().getJoueurEnCours())){
                    releverIDButton.setEnabled(false);
                }else{
                    releverIDButton.setEnabled(true);
                }

                faireWitchButton.setVisible(true);
                accuserButton.setVisible(false);
                faireHuntButton.setVisible(false);
                jeterCarteButton.setVisible(false);

            }else{
                accuserButton.setVisible(true);
                faireHuntButton.setVisible(true);
                releverIDButton.setVisible(false);
                faireWitchButton.setVisible(false);
                jeterCarteButton.setVisible(false);
            }
        }

        /**
         * Méthode permet d'afficher le joueur en cours
         * @return void
         */
        private void setJoueurEnCours() {
            joueurEnCours.setText("Joueur " + partie.getTourEnCours().getJoueurEnCours().getIdJoueur() + " est en cours");
        }

        /**
         * Méthode permet de changer le button de carte cliquée par l'utilisateur
         * @param buttonClicked
         * @return void
         */
        private void changeCarteStateButton(JButton buttonClicked){
            for (JButton button : carteEnCoursButton){
                if (button != buttonClicked){
                    button.setBackground(null);
                }
            }
        }

        /**
         * Méthode permet d'afficher toutes les cartes disponibles du joueur en cours
         * @return void
         */
        private void genererCartesEnCours() {
            mainJoueurPanel.removeAll();
            mainJoueurPanel.setBounds(66, 40, 500, 168);
            mainJoueurPanel.setLayout(new GridLayout(0, 4, 0, 0));
            tablePanel.add(mainJoueurPanel);
            ArrayList<RumourCarte> listeCarte = partie.getTourEnCours().getJoueurEnCours().getMain().getListeCartes();
            if (listeCarte.size() >= 1){
                for (RumourCarte carte : listeCarte) {
                    String nomCarte = carte.getNom();
                    JButton buttonCarte = new JButton(nomCarte);
                    buttonCarte.putClientProperty("Carte Clicked",carte);
                    buttonCarte.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton buttonClicked = (JButton)e.getSource();

                            if (buttonClicked.getClientProperty("Carte Clicked") == carteClicked){
                                buttonClicked.setBackground(null);
                                carteClicked = null;
                            }else{
                                buttonClicked.setBackground(new Color(218,247,166));
                                carteClicked = (RumourCarte) ((JButton)e.getSource()).getClientProperty("Carte Clicked");
                                huntEffetText.setText("Hunt Effet : " + carteClicked.getHuntEffet());
                                witchEffetText.setText("Witch Effet : " + carteClicked.getWitchEffet());
                                conditionText.setText("Condition : " + carteClicked.getCondition());
                                changeCarteStateButton(buttonClicked);
                            }
                        }
                    });
                    carteEnCoursButton.add(buttonCarte);
                    mainJoueurPanel.add(buttonCarte);
                }
            }else{
                mainJoueurPanel.setLayout(null);
                JLabel message = new JLabel("Ce joueur n'a plus de cartes !");
                message.setBounds(66,40,200,20);
                message.setFont(new Font("Arial",0,14));
                mainJoueurPanel.add(message);
            }
            mainJoueurPanel.revalidate();
            mainJoueurPanel.repaint();
        }

}
