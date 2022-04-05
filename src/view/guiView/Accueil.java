package view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente la fenêtre d'accueil du jeu
 * @author SOYLEMEZ Mehmet
 */
public class Accueil extends JFrame {

	/**
	 * Représente le bouton "Start" de la fenêtre, afficher pour commencer le jeu
	 */
    private JButton startButton = new JButton("Start");

    /**
     * Représente le bouton "Help" de la fenêtre, pas de fonctionalité actuellement
     */
    private JButton helpButton = new JButton("Help");

    public static void main(String[] args) {
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }

    /**
     * Constructor de Accueil
     */
    public Accueil(){
        setTitle("Witch Hunt Jeu");
        setSize(800,500);
        getContentPane().setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Witch Hunt Jeu");
        titleLabel.setFont(new Font("Building",0,30));
        titleLabel.setBounds(270,50,250,50);
        getContentPane().add(titleLabel);

        startButton.setBounds(300, 150, 157, 74);
        getContentPane().add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitialisationJeu initialisationJeu = new InitialisationJeu();
                initialisationJeu.setVisible(true);
            }
        });

        helpButton.setBounds(300,297,157,74);
        getContentPane().add(helpButton);
    }
}
