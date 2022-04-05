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
 * demandés par la carte Hooked Nose
 * @author Tran Le Vu Long
 */
public class HookedNoseUI extends WindowInputUI implements ActionListener{

    private Joueur joueurChoisi = null;
    private RumourCarte carteChoisie = null;
    private JPanel panelRandomCartes;

    /**
     * Getter de la carte choisie par l'utilisateur
     * @return
     */
    public RumourCarte getCarteChoisie() {
        return carteChoisie;
    }

    /**
     * Getter de le joueur choisi par l'utilisateur
     * @return
     */
    public Joueur getJoueurChoisi() {
        return joueurChoisi;
    }

    /**
     * Constructor de la fenêtre HookedNose UI
     * @param effet
     */
    public HookedNoseUI(String effet){
        super(effet);
        generateInputForm(effet);
        setLocationRelativeTo(null);
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     */
    public void generateInputForm(String effet){

        switch (effet){
            case EffetType.HUNT:{
                Partie partie = Partie.getInstance();
                ListeJoueur listeJoueurEnVie = partie.getTourEnCours().getListeJoueurEnVie();

                ButtonGroup group = new ButtonGroup();

                JLabel chooseNextPlayer = new JLabel("Choisir le joueur suivant");
                chooseNextPlayer.setFont(WindowInputUI.FONT_DEFAULT);
                chooseNextPlayer.setBounds(105,50,430,50);
                add(chooseNextPlayer);

                JPanel panel = new JPanel();
                panel.setBounds(105, 101, 430, 121);
                panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
                add(panel);

                JLabel chooseRandomCard = new JLabel("Vous pouvez choisir un carte aléatoire parmi ses cartes");
                chooseRandomCard.setFont(WindowInputUI.FONT_DEFAULT);
                chooseRandomCard.setBounds(106,230,429,15);
                add(chooseRandomCard);

                for (Joueur joueur : listeJoueurEnVie.getListeJoueur()){
                    if (joueur.getIdJoueur() != partie.getTourEnCours().getJoueurEnCours().getIdJoueur()){
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
                generateRandomCard(Partie.getInstance().getTourEnCours().getJoueurPrecedent());
                break;
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        joueurChoisi = (Joueur) ((JRadioButton)e.getSource()).getClientProperty("Joueur Choisi");
        generateRandomCard(joueurChoisi);
    }

    private void generateRandomCard(Joueur joueurChoisi) {
        ArrayList<RumourCarte> listeCarte = joueurChoisi.getMain().getListeCartes();


        if (panelRandomCartes != null){
            panelRandomCartes.removeAll();
        }

        panelRandomCartes = new JPanel();
        panelRandomCartes.setBounds(106, 266, 429, 129);
        panelRandomCartes.setLayout(new GridLayout(0,4,5,0));
        panelRandomCartes.setBorder(new LineBorder(new Color(0,0,0)));

        add(panelRandomCartes);

        for (RumourCarte carte: listeCarte){
            JButton newButtonCarte = new JButton("random card");
            newButtonCarte.putClientProperty("Carte Choisie", carte);
            newButtonCarte.setMargin(new Insets(0,0,0,0));
            newButtonCarte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carteChoisie = (RumourCarte) ((JButton)e.getSource()).getClientProperty("Carte Choisie");
                }
            });
            panelRandomCartes.add(newButtonCarte);
        }
        panelRandomCartes.revalidate();
        panelRandomCartes.repaint();
    }


}
