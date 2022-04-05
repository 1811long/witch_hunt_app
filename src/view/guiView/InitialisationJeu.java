package view.guiView;

import controller.consoleController.ConsolePartieActionController;
import controller.guiController.PartieActionController;
import model.joueur.Identite;
import model.joueur.Joueur;
import model.joueur.JoueurVirtuel;
import model.joueur.ListeJoueur;
import model.partie.Partie;
import model.strategy.EasyMode;
import model.strategy.HardMode;
import view.consoleView.ConsolePartieView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Cette classe représente la fenêtre d'initialisation du jeu
 * @author Tran Le Vu Long
 */
public class InitialisationJeu extends JFrame implements ActionListener {

    private JPanel settingPanel = new JPanel();
    ArrayList<JPanel> panelJoueurs = new ArrayList<>();
    private int nbrJoueur = 0;

    /**
     * Représente le button "Ajouter un joueur"
     */
    private JButton ajouterJoueurButton = new JButton("Ajouter un joueur");
    /**
     * Représente le button "Supprimer un joueur"
     */
    private JButton supprimerJoueurButton = new JButton("Supprimer un joueur");
    /**
     * Représente le button "Commencer la partie"
     */
    private JButton commencerPartie = new JButton("Commencer la partie");

    /**
     * Représente les choix d'identité pour les joueurs
     */
    private String[] identiteChoix = {"Villager", "Witch"};

    /**
     * Représente les types de joueurs souhaités
     */
    private String[] typeJoueur = {"Réel", "Virtuel"};

    /**
     * Représente les difficultés possibles de joueur Virtuel
     */
    private String[] difficulte = {"Facile", "Difficle"};

    /**
     * Constructor de la fenêtre d'initialisation
     */
    public InitialisationJeu(){
        setBounds(100, 100, 975, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setTitle("Configuration pour le jeu");
        setLocationRelativeTo(null);

        ajouterJoueurButton.setBounds(754,42,155,58);
        ajouterJoueurButton.setActionCommand("Ajouter");
        ajouterJoueurButton.addActionListener(this);
        add(ajouterJoueurButton);

        supprimerJoueurButton.setBounds(754, 130, 155, 58);
        supprimerJoueurButton.setActionCommand("Supprimer");
        supprimerJoueurButton.addActionListener(this);
        add(supprimerJoueurButton);

        commencerPartie.setBounds(754, 215, 155, 58);
        commencerPartie.setActionCommand("Commencer");
        commencerPartie.addActionListener(this);
        add(commencerPartie);

        settingPanel.setLayout(new GridLayout(6, 0, 20, 50));
        settingPanel.setBounds(34, 42, 692, 371);
        settingPanel.setBackground(null);
        getContentPane().add(settingPanel);
    }

    /**
     * La méthode qui recoit l'action de l'utilisateur et effectuer des traitements correspondants
     * Les actions sont : "Ajouter un joueur", "Supprimer un joueur"
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Ajouter":{
                if (nbrJoueur >= 6) return;
                if (nbrJoueur < 6) nbrJoueur++;


                JPanel panelJoueur = new JPanel();

                panelJoueur.setLayout(new GridLayout(0,4,20,0));
                settingPanel.add(panelJoueur);
                panelJoueurs.add(panelJoueur);

                JLabel idJoueur = new JLabel("Joueur " + String.valueOf(nbrJoueur));

                JComboBox comboBoxIdentite = new JComboBox(identiteChoix);
                JComboBox comboBoxJoueurType = new JComboBox(typeJoueur);
                JComboBox comboBoxDifficulte = new JComboBox(difficulte);

                panelJoueur.add(idJoueur);
                panelJoueur.add(comboBoxIdentite);
                panelJoueur.add(comboBoxJoueurType);

                comboBoxJoueurType.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JComboBox cb = (JComboBox) e.getSource();
                        String typeJoueur = (String) cb.getSelectedItem();
                        if (typeJoueur == "Réel"){
                            panelJoueur.remove(comboBoxDifficulte);
                        }else{
                            panelJoueur.add(comboBoxDifficulte);
                        }
                        revalidate();
                        repaint();
                    }
                });

                revalidate();
                repaint();
                break;
            }
            case "Supprimer":{
                if (nbrJoueur >= 1){
                    nbrJoueur--;
                    settingPanel.remove(nbrJoueur);
                    panelJoueurs.remove(nbrJoueur);
                }

                revalidate();
                repaint();
                break;
            }
            case "Commencer":{
                ListeJoueur listeJoueur = new ListeJoueur();
                for (int id = 1; id <= panelJoueurs.size(); id++){
                    JPanel panelJoueur = panelJoueurs.get(id-1);

                    String identite = (String)((JComboBox) panelJoueur.getComponent(1)).getSelectedItem();
                    String typeJoueur = (String)((JComboBox) panelJoueur.getComponent(2)).getSelectedItem();
                    if (typeJoueur == "Virtuel"){
                        String difficulteNiveau = (String)((JComboBox) panelJoueur.getComponent(3)).getSelectedItem();
                        JoueurVirtuel joueurVirtuel;

                        if (identite == "Witch"){
                            joueurVirtuel = new JoueurVirtuel(id,Identite.Witch);
                        }else{
                            joueurVirtuel = new JoueurVirtuel(id,Identite.Villager);
                        }

                        if (difficulteNiveau == "Facile"){
                            joueurVirtuel.setStrategy(new EasyMode(joueurVirtuel));
                        }else{
                            joueurVirtuel.setStrategy(new HardMode(joueurVirtuel));
                        }
                        listeJoueur.ajouterJoueur(joueurVirtuel);
                    }else{
                        Joueur joueurReel;
                        if (identite == "Witch"){
                             joueurReel = new Joueur(id, Identite.Witch);
                        }else{
                             joueurReel = new Joueur(id,Identite.Villager);
                        }
                        listeJoueur.ajouterJoueur(joueurReel);
                    }
                }
                Partie partie = new Partie(listeJoueur);

                GUIPartieView GUIPartieView = new GUIPartieView(partie);
                PartieActionController partieActionController = new PartieActionController(GUIPartieView);

                ConsolePartieView consolePartieView = new ConsolePartieView(partie);
                ConsolePartieActionController consolePartieActionController = new ConsolePartieActionController(consolePartieView);

                partie.commencer();
                GUIPartieView.setVisible(true);
            }
        }

    }
}
