package view.carteInputGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * La classe qui d�fini les configurations communes pour tous les formulaires
 * des cartes dans le jeu.
 * @author Tran Le Vu Long
 */
public class WindowInputUI extends JFrame {
    /**
     * Le bouton "Valider" qui aide utilisateur � valider ses choix
     */
    protected JButton validerButton = new JButton("Valider");

    /**
     * Le bouton "Annuler" qui aide utilisateur � annuler ses choix
     */
    protected JButton annulerButton = new JButton("Annuler");

    /**
     * Repr�sente le type d'effet que le joueur veut faire - Hunt ou Witch
     */
    private String effetType;

    /**
     * Repr�sente le font par d�faut de la fen�tre
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
     * M�thode permet d'ajouter le controller
     * @param listener
     */
    public void addListener(ActionListener listener){
        validerButton.addActionListener(listener);
        annulerButton.addActionListener(listener);
    }

    /**
     * Constructor de la fen�tre windowInputUI
     * @param effet
     */
    public WindowInputUI(String effet){
        this.effetType = effet;
        initialize();
    }

    /**
     * Constructor de la fen�tre windowInputUI
     */
    public WindowInputUI(){
        initialize();
    }

    /**
     * Permet de construire une fen�tre qui repr�sente un message � utilisateur
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
     * Permet d'initialiser tous les composants de la fen�tre
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
