package view.carteInputGUI;

import model.cartes.EffetType;
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
 * demandés par la carte Inquisition
 * @author Tran Le Vu Long
 */

public class InquisitionUI extends WindowInputUI {
    private RumourCarte carteJetee;
    private Joueur joueurChoisi;

    /**
     * Constructor de la fenêtre InquisitionUI
     * @param effet
     */
    public InquisitionUI(String effet){
        super(effet);
        setTitle("Inquisition Message");
        setLocationRelativeTo(null);
        generateInputForm(effet);
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     * @param effet
     */
    private void generateInputForm(String effet){
        switch (effet){
            case EffetType.HUNT:{
                Partie partie = Partie.getInstance();
                ListeJoueur listeJoueurEnVie = partie.getTourEnCours().getListeJoueurEnVie();

                ButtonGroup group = new ButtonGroup();
                setSize(503,393);

                JLabel descriptionLabel = new JLabel("Choisir un joueur parmi les joueurs suivants pour joueur le tour suivant");
                descriptionLabel.setFont(WindowInputUI.FONT_DEFAULT);
                descriptionLabel.setBounds(20, 49, 450, 27);
                add(descriptionLabel);

                JPanel panel = new JPanel();
                panel.setBounds(109, 86, 326, 169);
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                add(panel);

                validerButton.setLocation(145,315);
                annulerButton.setLocation(255,315);

                for (Joueur joueur : listeJoueurEnVie.getListeJoueur()){
                    if (joueur.getIdJoueur() != partie.getTourEnCours().getJoueurEnCours().getIdJoueur()){
                        JRadioButton radioButton = new JRadioButton("Joueur " + joueur.getIdJoueur());
                        radioButton.putClientProperty("Joueur Choisi", joueur);
                        radioButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                joueurChoisi = (Joueur) ((JRadioButton)e.getSource()).getClientProperty("Joueur Choisi");
                            }
                        });
                        group.add(radioButton);
                        panel.add(radioButton);
                    }
                }
                break;
            }
            case EffetType.WITCH: {
                Partie partie = Partie.getInstance();
                ArrayList<RumourCarte> listeCarteEnCours = partie.
                                                            getTourEnCours().
                                                            getJoueurEnCours().
                                                            getMain().
                                                            getListeCartes();
                JPanel panel = new JPanel();
                JLabel message = new JLabel();

                message.setText("Jeter une carte parmi les cartes suivantes : ");
                message.setFont(new Font("Arial",Font.BOLD,13));
                message.setBounds(110,80,430,50);
                add(message);
                panel.setBounds(110, 125, 430, 147);
                panel.setLayout(new GridLayout(0, 4, 5, 0));
                add(panel);

                if (listeCarteEnCours.size() == 0) {
                    setDialogMessage("Vous n'avez plus de carte pour jeter", "Inquisition Message");
                } else {
                    for (RumourCarte carte : listeCarteEnCours) {
                        JButton buttonCarte = new JButton(carte.getNom());
                        buttonCarte.putClientProperty("Carte Jetee", carte);
                        buttonCarte.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                carteJetee = (RumourCarte) ((JButton)e.getSource()).getClientProperty("Carte Jetee");
                            }
                        });
                        panel.add(buttonCarte);
                    }
                }
                break;
            }
        }
    }

    /**
     * Getter de le joueur choisi par l'utilisateur
     * @return joueurChoisi
     */
    public Joueur getJoueurChoisi(){
        return joueurChoisi;
    }

    /**
     * Getter de la carte jetée par l'utilisateur
     * @return carteJetee
     */
    public RumourCarte getCarteJetee(){
        return carteJetee;
    }



}
