package view.carteInputGUI;

import model.cartes.EffetType;

/**
 * Cette classe représente une fenêtre qui permet au joueur de saisir les inputs
 * demandés par la carte Toad
 * @author Tran Le Vu Long
 */

public class ToadUI extends WindowInputUI{
    /**
     * Constructor de la fenêtre ToadUI
     * @param effet
     */
    public ToadUI(String effet){
        super(effet);
        setLocationRelativeTo(null);
        generateNewFrame(effet);
    }

    /**
     * Méthode qui génère le formulaire pour le joueur en cours de saisir les inputs demandés par la carte
     * @param effet
     */
    public void generateNewFrame(String effet){
        switch (effet){
            case EffetType.HUNT:{
                setDialogMessage("Vous allez reveler votre identité vous êtes sur ?", "Message Toad");
                break;
            }
            case EffetType.WITCH:{
                setDialogMessage("Vous allez prendre le tour prochain", "Message Toad");
                break;
            }
        }
    }
}
