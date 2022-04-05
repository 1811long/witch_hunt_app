package model.inputSupport;

import model.inputSupport.InterruptableSysIn;
import model.cartes.RumourCarte;
import model.joueur.Joueur;
import model.partie.Partie;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe qui permet de faciliter la lecture des inputs de joueur
 * elle permet aussi de contr�ler l'input valide saisi par le joueur en cours
 * @author Tran Le Vu Long
 */

public class InputHandle {

    /**
     * M�thode qui permet de lire et retourner un joueur par son num�ro
     * @return le joueur choisi par l'utilisateur
     * @throws IOException
     */
    public Joueur getJoueurChoisi() throws IOException {
        int idJoueur = Integer.parseInt(lireChaine());
        while (Partie.getInstance().getListeJoueur().getJoueurParId(idJoueur) == null){
            System.out.println("Entrer un correct numero de joueur s'il vous plait !");
             idJoueur = Integer.parseInt(lireChaine());
        }
        return Partie.getInstance().getListeJoueur().getJoueurParId(idJoueur);
    }

    /**
     * M�thode qui permet de lire et retourner une carte par son num�ro dans la liste de carte de joueur
     * @return la carte choisie par l'utilisateur
     * @throws IOException
     */
    public RumourCarte getCarteChoisie(ArrayList<RumourCarte> listeCarte) throws IOException {
        if (listeCarte.size() == 0) return null;
        int idCarte = Integer.parseInt(lireChaine());
        while (idCarte < 1 || idCarte > listeCarte.size()){
            System.out.println("Merci d'entrer un numero de carte correcte !");
            idCarte = Integer.parseInt(lireChaine());
        }
        return listeCarte.get(idCarte-1);
    }

    /**
     * M�thode qui permet d'afficher les cartes
     * @param listeCartes
     * @return void
     */
    public void afficherCartes (ArrayList<RumourCarte> listeCartes){
        for (int i = 0; i < listeCartes.size(); i++){
            String nomCarte = listeCartes.get(i).getNom();
            String huntEffet = listeCartes.get(i).getHuntEffet();
            String witchEffet = listeCartes.get(i).getWitchEffet();
            int j = i + 1;
            System.out.println("Carte " + j + " : " + nomCarte);
            System.out.println("Hunt Effet : " + huntEffet);
            System.out.println("Witch Effet : "+ witchEffet);
        }
    }
    /**
     * M�thode qui permet d'afficher les joueurs
     * @param listeJoueurs
     * @return void
     */
    public void afficherJoueurs(ArrayList<Joueur> listeJoueurs){
        for (Joueur joueur : listeJoueurs){
            System.out.print("Joueur " + joueur.getIdJoueur() + " ");
        }
        System.out.println();
    }

    /**
     * M�thode qui permet de lire la prochaine ligne sur la console
     * @return
     * @throws IOException
     */
    public String lireChaine() throws IOException {
        String var = InterruptableSysIn.nextLineBlocking();
        return var;
    }
}
