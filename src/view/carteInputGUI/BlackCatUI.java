package view.carteInputGUI;

import model.cartes.EffetType;
import model.cartes.RumourCarte;
import model.partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Cette classe représente une fenêtre qui permet au joueur de saisir les inputs
 * demandés par la carte Black Cat
 * @author Tran Le Vu Long
 */
public class BlackCatUI extends WindowInputUI {
    private RumourCarte carteJetee = null;

    /**
     * Constructor de la fenêtre BlackCat UI
     * @param effet
     */
    public BlackCatUI(String effet){
        super(effet);
        setLocationRelativeTo(null);
        generateNewFrame(effet);
    }

    /**
     * Getter de la carte jetée par l'utilisateur
     * @return
     */
    public RumourCarte getCarteJetee() {
        return carteJetee;
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     */
    private void generateNewFrame(String effet) {
        switch(effet){
            case EffetType.HUNT:{
                ArrayList<RumourCarte> listeCarteJetee = Partie.getInstance().getTable().getCartesJetees();

                JLabel choisirCarte = new JLabel("Choisir une carte jetee parmi les cartes suivantes :");
                choisirCarte.setFont(FONT_DEFAULT);
                choisirCarte.setBounds(47,130,527,30);
                add(choisirCarte);

                JPanel cartePanel = new JPanel();
                JScrollPane carteScrollPane = new JScrollPane();
                carteScrollPane.setBounds(47, 169, 527, 158);
                cartePanel.setLayout(new GridLayout(0,4,5,0));
                carteScrollPane.setViewportView(cartePanel);
                add(carteScrollPane);
                for (RumourCarte carte : listeCarteJetee){
                    JButton newButtonCarte = new JButton(carte.getNom());
                    newButtonCarte.putClientProperty("Carte Choisie", carte);
                    newButtonCarte.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            carteJetee = (RumourCarte) ((JButton)e.getSource()).getClientProperty("Carte Choisie");
                        }
                    });
                    cartePanel.add(newButtonCarte);
                }
                if (listeCarteJetee.size() < 5) carteScrollPane.getHorizontalScrollBar().setEnabled(false);
                break;
            }
            case EffetType.WITCH:{
                setDialogMessage("Vous allez prendre le prochain tour", "Message BlackCat");
                break;
            }
        }
    }
}
