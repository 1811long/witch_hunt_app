package view.carteInputGUI;

import model.cartes.EffetType;
import model.joueur.Joueur;

import javax.swing.*;

/**
 * Cette classe représente une fenêtre qui permet au joueur de saisir les inputs
 * demandés par la carte CauIdron
 * @author Tran Le Vu Long
 */
public class  CauIdronUI extends WindowInputUI{
    private Joueur joueurChoisi = null;

    /**
     * Constructor de la fenêtre CauIdron UI
     * @param effet
     */
    public CauIdronUI(String effet){
        super(effet);
        setLocationRelativeTo(null);

        generateNewFrame(effet);
    }
    /**
     * Getter du joueur choisi à accuser
     * @return joueurChoisi
     */
    public Joueur getJoueurChoisi(){
        return joueurChoisi;
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     */
    public void generateNewFrame(String effet){
        switch (effet){
            case EffetType.HUNT:{
                setDialogMessage("Vous allez révéler votre identité, vous êtes sur ?", "CauIdron Hunt");
                break;
            }
            case EffetType.WITCH:{
                setTitle("CauIdron Witch");
                JTextArea confirmMessage = new JTextArea();
                confirmMessage.setLineWrap(true);
                confirmMessage.setEditable(false);
                confirmMessage.setText("Le joueur précédent va jeter une carte et vous allez prendre le tour \nprochain, vous êtes sur ?");
                confirmMessage.setBounds(127, 216, 500, 55);
                confirmMessage.setFont(WindowInputUI.FONT_DEFAULT);
                confirmMessage.setBackground(null);
                add(confirmMessage);
                break;
            }
        }
    }
}
