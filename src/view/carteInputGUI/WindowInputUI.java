package view.carteInputGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * La classe qui défini les configurations communes pour tous les formulaires
 * des cartes dans le jeu.
 * @author Tran Le Vu Long
 */
public class WindowInputUI extends JFrame {
    /**
     * Le bouton "Valider" qui aide utilisateur à valider ses choix
     */
    protected JButton validerButton = new JButton("Valider");

    /**
     * Le bouton "Annuler" qui aide utilisateur à annuler ses choix
     */
    protected JButton annulerButton = new JButton("Annuler");

    /**
     * Représente le type d'effet que le joueur veut faire - Hunt ou Witch
     */
    private String effetType;

    /**
     * Représente le font par défaut de la fenêtre
     */
    public static final Font FONT_DEFAULT = new Font("Tahoma", Font.PLAIN, 14);

    /**
     * Getter d'effetType
     * @return effetType
     */
    public String getEffetType(){
        return this.effetType;
    }

    /**
     * Méthode permet d'ajouter le controller
     * @param listener
     */
    public void addListener(ActionListener listener){
        validerButton.addActionListener(listener);
        annulerButton.addActionListener(listener);
    }

    /**
     * Constructor de la fenêtre windowInputUI
     * @param effet
     */
    public WindowInputUI(String effet){
        this.effetType = effet;
        initialize();
    }

    /**
     * Constructor de la fenêtre windowInputUI
     */
    public WindowInputUI(){
        initialize();
    }

    /**
     * Permet de construire une fenêtre qui représente un message à utilisateur
     * @param message
     * @param title
     * @return void
     */
    public void setDialogMessage(String message, String title){
        setSize(519,152);
        setLocationRelativeTo(null);

        JLabel messageLabel = new JLabel(message);
        getContentPane().add(messageLabel);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBounds(95, 22, 307, 43);

        validerButton.setBounds(102, 82, 114, 21);
        annulerButton.setBounds(299, 82, 112, 21);
    }

    /**
     * Permet d'initialiser tous les composants de la fenêtre
     */
    private void initialize() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setSize( 634, 546);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        validerButton.setBounds(194, 466, 85, 33);
        validerButton.setActionCommand("Valider");
        getContentPane().add(validerButton);

        annulerButton.setBounds(343, 466, 90, 33);
        annulerButton.setActionCommand("Annuler");
        getContentPane().add(annulerButton);
    }


}
