package view.carteInputGUI;

import model.cartes.EffetType;
import model.cartes.RumourCarte;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;
import model.partie.Partie;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Cette classe représente une fenêtre qui permet au joueur de saisir les inputs
 * demandés par la carte PetNewt
 * @author Tran Le Vu Long
 */

public class PetNewtUI extends WindowInputUI implements ActionListener{
    private RumourCarte carteReveleeChoisie = null;
    private Joueur joueurSuivant = null;
    private JPanel panelReveleeCarte;

    /**
     * Constructor de la fenêtre PetNewUI
     * @param effet
     */
    public PetNewtUI(String effet){
        super(effet);
        setLocationRelativeTo(null);
        generateNewFrame(effet);
    }

    /**
     * Getter du joueur suivant
     * @return joueurSuivant
     */
    public Joueur getJoueurSuivant() {
        return joueurSuivant;
    }

    /**
     * Getter de carte révélée choisie par l'utilisateur
     * @return carteReveleeChoisie
     */
    public RumourCarte getCarteReveleeChoisie() {
        return carteReveleeChoisie;
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     * @param effet
     */
    private void generateNewFrame(String effet) {
        switch(effet){
            case EffetType.HUNT:{
                Partie partie = Partie.getInstance();
                ListeJoueur listeJoueurEnVie = partie.getTourEnCours().getListeJoueurEnVie();
                ButtonGroup group = new ButtonGroup();

                JLabel chooseNextPlayer = new JLabel("Choisir un joueur pour prendre une carte révélée");
                chooseNextPlayer.setFont(WindowInputUI.FONT_DEFAULT);
                chooseNextPlayer.setBounds(105,50,430,50);
                add(chooseNextPlayer);

                JPanel panel = new JPanel();
                panel.setBounds(105, 101, 430, 121);
                panel.setBorder(new LineBorder(new Color(0,0,0)));
                add(panel);

                JLabel choisirCarte = new JLabel("Vous pouvez choisir un carte parmi les cartes suivantes");
                choisirCarte.setFont(WindowInputUI.FONT_DEFAULT);
                choisirCarte.setBounds(106,230,429,15);
                add(choisirCarte);

                for (Joueur joueur : listeJoueurEnVie.getListeJoueur()) {
                    if (joueur.getIdJoueur() != partie.getTourEnCours().getJoueurEnCours().getIdJoueur()) {
                        JRadioButton radioButton = new JRadioButton("Joueur " + joueur.getIdJoueur());
                        radioButton.putClientProperty("Joueur Choisi", joueur);
                        radioButton.addActionListener(this);
                        group.add(radioButton);
                        panel.add(radioButton);
                    }
                }
                break;
            }
            case EffetType.WITCH:{
                  setDialogMessage("Vous allez prendre le tour prochain","Pet Newt Witch");
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        joueurSuivant = (Joueur) ((JRadioButton)e.getSource()).getClientProperty("Joueur Choisi");
        generateReveleeCarte(joueurSuivant);
    }

    private void generateReveleeCarte(Joueur joueurChoisi) {
        ArrayList<RumourCarte> listeCarte = Partie.getInstance().getTable().getCartesRevelees(joueurChoisi);
        if (panelReveleeCarte != null){
            panelReveleeCarte.removeAll();
        }
        panelReveleeCarte = new JPanel();
        panelReveleeCarte.setBounds(106, 266, 429, 129);
        panelReveleeCarte.setLayout(new GridLayout(0,4,5,0));
        panelReveleeCarte.setBorder(new LineBorder(new Color(0,0,0)));
        add(panelReveleeCarte);

        for (RumourCarte carte: listeCarte){
            JButton newButtonCarte = new JButton(carte.getNom());
            newButtonCarte.putClientProperty("Carte Choisie", carte);
            newButtonCarte.setMargin(new Insets(0,0,0,0));
            newButtonCarte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carteReveleeChoisie = (RumourCarte) ((JButton)e.getSource()).getClientProperty("Carte Choisie");
                }
            });
            panelReveleeCarte.add(newButtonCarte);
        }
        panelReveleeCarte.revalidate();
        panelReveleeCarte.repaint();
    }
}
