package view.carteInputGUI;

import model.cartes.EffetType;

/**
 * Cette classe repr�sente une fen�tre qui permet au joueur de saisir les inputs
 * demand�s par la carte Toad
 * @author Tran Le Vu Long
 */

public class ToadUI extends WindowInputUI{
    /**
     * Constructor de la fen�tre ToadUI
     * @param effet
     */
    public ToadUI(String effet){
        super(effet);
        setLocationRelativeTo(null);
        generateNewFrame(effet);
    }

    /**
     * M�thode qui g�n�re le formulaire pour le joueur en cours de saisir les inputs demand�s par la carte
     * @param effet
     */
    public void generateNewFrame(String effet){
        switch (effet){
            case EffetType.HUNT:{
                setDialogMessage("Vous allez reveler votre identit� vous �tes sur ?", "Message Toad");
                break;
            }
            case EffetType.WITCH:{
                setDialogMessage("Vous allez prendre le tour prochain", "Message Toad");
                break;
            }
        }
    }
}
