package view.carteInputGUI;

import model.cartes.RumourCarte;
import model.partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Cette classe représente une fenêtre qui permet au joueur de saisir une carte
 * qu'il veut jeter
 * @author Tran Le Vu Long
 */
public class JeterCarteUI extends WindowInputUI{
    private RumourCarte carteJetee;

    /**
     * Constructor de la fenêtre jeterCarteUI
     */
    public JeterCarteUI(){
        super();
        setLocationRelativeTo(null);

        generaterInputForm();
    }

    /**
     * Getter de la carte jetée par l'utilisateur
     * @return carteJetee
     */
    public RumourCarte getCarteJetee(){
        return carteJetee;
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de choisir une carte qu'il veut jeter
     */
    private void generaterInputForm() {

        Partie partie = Partie.getInstance();
        ArrayList<RumourCarte> listeCarteEnCours = partie.
                                                    getTourEnCours().
                                                    getJoueurEnCours().
                                                    getMain().
                                                    getListeCartes();
        if (listeCarteEnCours.size() == 0){
            JOptionPane.showMessageDialog(null,
            "Vous n'avez plus de carte !",
            "Message",
            JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        JPanel panel = new JPanel();
        panel.setBounds(110, 125, 430, 147);
        panel.setLayout(new GridLayout(0, 4, 5, 0));
        add(panel);
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

}

