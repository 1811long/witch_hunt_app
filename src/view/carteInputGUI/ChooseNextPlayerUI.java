package view.carteInputGUI;

import model.joueur.Joueur;
import model.joueur.ListeJoueur;
import model.partie.Partie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe repr�sente une fen�tre qui permet au joueur de choisir le joueur suivant
 * @author Tran Le Vu Long
 */
public class ChooseNextPlayerUI extends WindowInputUI{
    private Joueur joueurChoisi;

    /**
     * Constructor de la fen�tre chooseNextPlayerUI
     */
    public ChooseNextPlayerUI( ) {
        super();
        setLocationRelativeTo(null);
        generateInputForm();
    }

    /**
     * Getter du joueur choisi � accuser
     * @return joueurChoisi
     */
    public Joueur getJoueurChoisi() {
        return joueurChoisi;
    }

    /**
     * M�thode qui g�n�re le formulaire pour le joueur en cours de saisir les inputs demand�s par la carte
     */
    private void generateInputForm(){
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
    }
}
