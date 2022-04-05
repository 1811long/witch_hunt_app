package view.carteInputGUI;

import model.joueur.Joueur;
import model.joueur.ListeJoueur;
import model.partie.Partie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe repr�sente une fen�tre qui permet au joueur de saisir
 * un joueur � accuser
 * @author Tran Le Vu Long
 */
public class AccuserUI extends WindowInputUI{
    /**
     * Repr�sente le joueur choisi pour accuser
     */
    private Joueur joueurChoisi;

    /**
     * Getter du joueur choisi � accuser
     * @return joueurChoisi
     */
    public Joueur getJoueurChoisi(){
        return joueurChoisi;
    }

    /**
     * Constructor de la fen�tre AccuserUI
     */
    public AccuserUI(){
        super();
        setTitle("Choisir un joueur pour accuser");
        generateInputForm();
    }

    /**
     * M�thode permet d'ajouter Controller ou une classe impl�mentant interface ActionListener � cette fen�tre
     * @param actionListener
     */
    public void addActionListener(ActionListener actionListener){
        validerButton.addActionListener(actionListener);
        annulerButton.addActionListener(actionListener);
    }

    /**
     * M�thode qui g�n�re la formulaire pour choisir un joueur � accuser
     */
    private void generateInputForm() {
        ListeJoueur listeJoueurAccusable = Partie.getInstance().getTourEnCours().getJoueurAccusable();
        ButtonGroup group = new ButtonGroup();

        setSize(503,393);
        setLocationRelativeTo(null);
        JLabel descriptionLabel = new JLabel("Choisir un joueur parmi les joueurs suivants pour accuser");
        descriptionLabel.setFont(WindowInputUI.FONT_DEFAULT);
        descriptionLabel.setBounds(109, 49, 450, 27);
        add(descriptionLabel);

        JPanel panel = new JPanel();
        panel.setBounds(109, 86, 326, 169);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);

        validerButton.setLocation(145,315);
        annulerButton.setLocation(255,315);

        for (Joueur joueur : listeJoueurAccusable.getListeJoueur()){
            if (joueur.getIdJoueur() != Partie.getInstance().getTourEnCours().getJoueurEnCours().getIdJoueur()){
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
