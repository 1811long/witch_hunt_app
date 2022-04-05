package view.guiView;

import model.joueur.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe représentant un joueur dans l'interface graphique
 * @author Tran Le Vu Long
 */
public class JoueurUI extends JPanel implements Observer {

    /**
     * Représente le joueur
     */
    private Joueur joueur;

    /**
     * Représente l'image du joueur
     */
    private JLabel image = new JLabel();

    /**
     * Représente le pseudo du joueur
     */
    private JLabel pseudo = new JLabel();

    /**
     * Représente la location du joueur sur l'interface graphique
     */
    int[][] location;

    /**
     * Constructor de joueur GUI
     * @param joueur joueur à ajouté dans l'interface graphique
     * @param nbJoueur nombre total de joueur
     */
    public JoueurUI(Joueur joueur, int nbJoueur){
        this.joueur = joueur;
        setLayout(null);

        setBackground(new Color(162, 161, 149));
        String url = "/images/" +"Joueur"+joueur.getIdJoueur()+".png";

        image.setOpaque(false);
        image.setIcon(new ImageIcon(getClass().getResource(url)));
        image.setBounds(20,0,110,90);
        add(image);

        pseudo.setOpaque(false);
        pseudo.setFont(new Font("arial",Font.BOLD,13));
        if (!joueur.estVirtuel()){
            pseudo.setText("Joueur " + joueur.getIdJoueur() + ": Réel");
        }else{
            pseudo.setText("Joueur " + joueur.getIdJoueur() + ": Virtuel");
        }
        pseudo.setHorizontalAlignment(JLabel.CENTER);
        pseudo.setBounds(0, 90, 115, 25);
        add(pseudo);

        setLocation(nbJoueur);

        setBounds(location[joueur.getIdJoueur()-1][0],location[joueur.getIdJoueur()-1][1],110,110);
    }

    /**
     * Methode permettant de placer dynamiquement les joueurs sur la table de jeu
     * en fonction du nombre totale de joueurs de la partie
     * @param nbJoueur nombre de joueur de la partie
     * @return void
     */
    public void setLocation(int nbJoueur){
        switch (nbJoueur){
            case 3 :
                location = new int[][]{{570,60},{1042,343},{570,606}};
                break;
            case 4:
                location = new int[][]{{570,60},{1042,343},{570,606},{146,343}};
                break;
            case 5:
                location = new int[][]{{570,60},{1042,343},{698,606},{445,606},{146,343}};
                break;
            case 6:
                location = new int[][]{{445,60},{698,59},{1042,343},{698,606},{445,606},{146,343}};
                break;
        }
    }

    /**
     * La méthode permet de mettre à jour le joueur GUI quand il y a un changement
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
