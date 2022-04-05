package view.carteInputGUI;

import model.cartes.EffetType;
import model.cartes.PointedHat;
import model.cartes.RumourCarte;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;
import model.partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Cette classe représente une fenêtre qui permet au joueur de saisir les inputs
 * demandés par la carte Pointed Hat
 * @author Tran Le Vu Long
 */
public class PointedHatUI extends WindowInputUI{

    private RumourCarte carteReveleeChoisie = null;
    private Joueur joueurChoisi = null;

    private PointedHat pointedHatCarte;

    /**
     * Constructor de la fenêtre PointedHatUI
     * @param effet
     */
    public PointedHatUI(String effet){
        super(effet);
        setLocationRelativeTo(null);
        generateFormInput(effet);
    }

    /**
     * Getter de la carte revélée choisie par l'utilisateur
     * @return carteReveleeChoisie
     */
    public RumourCarte getCarteReveleeChoisie(){
        return this.carteReveleeChoisie;
    }

    /**
     * Getter du joueur choisi par l'utilisateur
     * @return joueurChoisi
     */
    public Joueur getJoueurChoisi(){
        return this.joueurChoisi;
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     * @param effet
     */
    private void generateFormInput(String effet) {
        Partie partie = Partie.getInstance();
        Joueur joueurEnCours = partie.getTourEnCours().getJoueurEnCours();

        JLabel reprendreCarte = new JLabel("Vous pouvez reprendre une de vos cartes révélées");
        reprendreCarte.setFont(WindowInputUI.FONT_DEFAULT);
        reprendreCarte.setBounds(113,90,430,15);
        add(reprendreCarte);


        ArrayList<RumourCarte> listeCarteRevelee = partie.getTable().getCartesRevelees(joueurEnCours);
        JPanel panel = new JPanel();
        panel.setLayout((new GridLayout(0,12,5,0)));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(113,125,430,147);
        scrollPane.setViewportView(panel);
        add(scrollPane);

        for (RumourCarte carte: listeCarteRevelee){
            JButton buttonCarte = new JButton(carte.getNom());
            buttonCarte.putClientProperty("Carte Choisie",carte);
            buttonCarte.setPreferredSize(new Dimension(100,125));
            buttonCarte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carteReveleeChoisie = (RumourCarte) ((JButton)e.getSource()).getClientProperty("Carte Choisie");
                }
            });
            panel.add(buttonCarte);
        }

        if (listeCarteRevelee.size() <= 4){
            scrollPane.getHorizontalScrollBar().setEnabled(false);
        }

        switch (effet){
            case EffetType.HUNT:{
                ListeJoueur listeJoueurEnVie = partie.getTourEnCours().getListeJoueurEnVie();
                ButtonGroup group = new ButtonGroup();

                JLabel chooseNextPlayer = new JLabel("Chosir un joueur pour le tour suivant");
                chooseNextPlayer.setFont(WindowInputUI.FONT_DEFAULT);
                chooseNextPlayer.setBounds(113,315,428,20);
                add(chooseNextPlayer);

                JPanel joueurChoisiPanel = new JPanel();
                joueurChoisiPanel.setBounds(113,350,428,116);
                joueurChoisiPanel.setLayout(new BoxLayout(joueurChoisiPanel,BoxLayout.Y_AXIS));
                add(joueurChoisiPanel);

                for (Joueur joueur : listeJoueurEnVie.getListeJoueur()) {
                    if (joueur.getIdJoueur() != partie.getTourEnCours().getJoueurEnCours().getIdJoueur()) {
                        JRadioButton radioButton = new JRadioButton("Joueur " + joueur.getIdJoueur());
                        radioButton.putClientProperty("Joueur Choisi", joueur);
                        radioButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                joueurChoisi = (Joueur) ((JRadioButton) e.getSource()).getClientProperty("Joueur Choisi");
                            }
                        });
                        group.add(radioButton);
                        joueurChoisiPanel.add(radioButton);
                    }
                }
                break;
            }
        }
    }


}
