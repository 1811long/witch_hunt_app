package view.carteInputGUI;


import model.cartes.EffetType;
import model.joueur.Joueur;
import model.joueur.ListeJoueur;
import model.partie.Partie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Cette classe représente une fenêtre qui permet au joueur de saisir les inputs
 * demandés par la carte Angry Mob
 * @author Tran Le Vu Long
 */
public class AngryMobUI extends WindowInputUI {

    private Joueur joueurChoisi;
    private JLabel descriptionLabel;

    /**
     * Constructor de la fenêtre AngryMob UI
     * @param effet
     */
    public AngryMobUI(String effet){
        super(effet);
        generateFormInput(effet);
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     */
    private void generateFormInput(String effet) {

        switch (effet){
            case EffetType.HUNT:
                Partie partie = Partie.getInstance();

                setTitle("Choisir un joueur pour révéler son identité");
                setSize(503,393);
                setLocationRelativeTo(null);

                JLabel descriptionLabel = new JLabel("Choisir un joueur parmi les joueurs suivants pour réveler son identité");
                descriptionLabel.setFont(WindowInputUI.FONT_DEFAULT);
                descriptionLabel.setBounds(20, 49, 450, 27);
                add(descriptionLabel);

                ListeJoueur listeJoueurAccusable = partie.getTourEnCours().getJoueurAccusable();
                ButtonGroup group = new ButtonGroup();
                JPanel panel = new JPanel();
                panel.setBounds(109, 86, 326, 169);
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                add(panel);

                validerButton.setLocation(145,315);
                annulerButton.setLocation(255,315);


                ArrayList<JRadioButton> radioButtons = new ArrayList<>();
                if (listeJoueurAccusable.getNbJoueur() != 0){
                    for (Joueur joueur : listeJoueurAccusable.getListeJoueur()){
                        JRadioButton radioButton = new JRadioButton("Joueur " + joueur.getIdJoueur());
                        radioButton.putClientProperty("Joueur Choisi", joueur);
                        radioButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                joueurChoisi = (Joueur) ((JRadioButton)e.getSource()).getClientProperty("Joueur Choisi");
                            }
                        });

                        radioButtons.add(radioButton);
                        group.add(radioButton);
                        panel.add(radioButton);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Il y a plus de joueur accusables en ce moment",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                break;
            case EffetType.WITCH:
                    setTitle("Angry Mob Message");
                    setDialogMessage("Vous allez prendre le tour prochain, vous êtes sur ?", "Confirmation");
                break;
        }
    }

    /**
     * Getter de joueurChoisi par l'utilisateur
     * @return joueurChoisi
     */
    public Joueur getJoueurChoisi(){
        return this.joueurChoisi;
    }
}
