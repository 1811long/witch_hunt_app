package view.carteInputGUI;

import model.cartes.EffetType;
import model.joueur.Joueur;

import javax.swing.*;

/**
 * Cette classe repr�sente une fen�tre qui permet au joueur de saisir les inputs
 * demand�s par la carte CauIdron
 * @author Tran Le Vu Long
 */
public class  CauIdronUI extends WindowInputUI{
    private Joueur joueurChoisi = null;

    /**
     * Constructor de la fen�tre CauIdron UI
     * @param effet
     */
    public CauIdronUI(String effet){
        super(effet);
        setLocationRelativeTo(null);

        generateNewFrame(effet);
    }
    /**
     * Getter du joueur choisi � accuser
     * @return joueurChoisi
     */
    public Joueur getJoueurChoisi(){
        return joueurChoisi;
    }

    /**
     * M�thode qui g�n�re le formulaire pour le joueur en cours de saisir les inputs demand�s par la carte
     */
    public void generateNewFrame(String effet){
        switch (effet){
            case EffetType.HUNT:{
                setDialogMessage("Vous allez r�v�ler votre identit�, vous �tes sur ?", "CauIdron Hunt");
                break;
            }
            case EffetType.WITCH:{
                setTitle("CauIdron Witch");
                JTextArea confirmMessage = new JTextArea();
                confirmMessage.setLineWrap(true);
                confirmMessage.setEditable(false);
                confirmMessage.setText("Le joueur pr�c�dent va jeter une carte et vous allez prendre le tour \nprochain, vous �tes sur ?");
                confirmMessage.setBounds(127, 216, 500, 55);
                confirmMessage.setFont(WindowInputUI.FONT_DEFAULT);
                confirmMessage.setBackground(null);
                add(confirmMessage);
                break;
            }
        }
    }
}
