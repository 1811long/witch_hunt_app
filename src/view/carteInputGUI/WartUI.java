package view.carteInputGUI;

import model.cartes.EffetType;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;
import model.partie.Partie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente une fenêtre qui permet au joueur de saisir les inputs
 * demandés par la carte Wart
 * @author Tran Le Vu Long
 */
public class WartUI extends WindowInputUI{
    private Joueur joueurChoisi = null;

    /**
     * Getter du joueurChoisi par l'utilisateur
     * @return
     */
    public Joueur getJoueurChoisi() {
        return joueurChoisi;
    }

    /**
     * Constructor de la fenêtre WartUI
     * @param effet
     */
    public WartUI(String effet){
        super(effet);
        setLocationRelativeTo(null);

        generateInputForm(effet);
    }
    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     * @param effet
     */
    private void generateInputForm(String effet) {
        switch (effet){
            case EffetType.HUNT:{
                Partie partie = Partie.getInstance();
                ListeJoueur listeJoueurEnVie = partie.getTourEnCours().getListeJoueurEnVie();

                setSize(503,393);
                setTitle("Wart Hunt");
                setLocationRelativeTo(null);

                JLabel descriptionLabel = new JLabel("Choisir un joueur parmi les joueurs suivants pour le tour prochain");
                descriptionLabel.setFont(WindowInputUI.FONT_DEFAULT);
                descriptionLabel.setBounds(20, 49, 450, 27);
                add(descriptionLabel);

                ButtonGroup group = new ButtonGroup();
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
            case EffetType.WITCH:{
                setDialogMessage("Vous allez prendre le tour prochain", "Wart Witch");
                break;
            }
        }
    }


}
