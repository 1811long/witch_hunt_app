package model.strategy;

import model.cartes.*;
import model.joueur.Identite;
import model.joueur.Joueur;
import model.joueur.JoueurVirtuel;
import model.joueur.ListeJoueur;
import model.partie.Partie;
import model.partie.Tour;

import java.util.*;

/**
 * Cette classe représente le niveau de difficulté difficile pour le joueur
 * virtuel
 * 
 * @author SOYLEMEZ Mehmet
 *
 */
public class HardMode implements Strategy {
	/**
	 * Représente un joueur virtuel qui utilise la stratégie difficile
	 */
	private Joueur joueurVirtuel;

	/**
	 * Constructeur de la classe HardMode
	 * @param joueurVirtuel Le joueurVirtuel qui utilise cette stratégie
	 */
	public HardMode(JoueurVirtuel joueurVirtuel) {
		this.joueurVirtuel = joueurVirtuel;
	}


	/**
	 * Cette méthode décrit la stratégie difficile du joueur virtuel, elle consiste
	 * à accuser les autres joueurs quand c'est à son tour, et jouer une carte aléatoire à chaque fois il est accusé, s'il n'a
	 * plus de cartes, il va révéler automatiquement son identité
	 */
	@Override
	public void jouer() {
		Tour tour = Partie.getInstance().getTourEnCours();

		if (tour.estDuckingStool()) {
			ArrayList<RumourCarte> listeCartes = tour.getJoueurEnCours().getMain().getListeCartes();
			int nbCartes = listeCartes.size();
			if (nbCartes == 0) {
				tour.releverID(joueurVirtuel);
			} else {
				joueurVirtuel.jeterCarte(listeCartes.get(new Random().nextInt(nbCartes)));
				tour.setEstDuckingStool(false);
				tour.setJoueurEnCours(joueurVirtuel);
			}
			Partie.getInstance().getTourEnCours().passerTourSuivant();
			return;
		}

		if (tour.joueurEnCoursEstAccuse()) {
			Random rand = new Random();
			ArrayList<RumourCarte> cartes = joueurVirtuel.getMain().getListeCartes();

			if (cartes.size() == 0) {
				tour.releverID(joueurVirtuel);
			} else {
				int nbCartes = cartes.size();
				List<Integer> list = new ArrayList<>();
				for (int i = 0; i <= nbCartes - 1; i++) {
					list.add(i);
				}
				Collections.shuffle(list);
				if (joueurVirtuel.getIdentite() == Identite.Witch) {
					boolean pouvoirJouerCarte = false;
					for (int i = 0; i <= nbCartes - 1; i++) {
						int idCarte = list.get(i);
						RumourCarte carte = cartes.get(idCarte);
						if (carte.witchEffetJouable(joueurVirtuel)) {
							pouvoirJouerCarte = true;
							jouerCarte(carte);
							break;
						}
					}
					if (!pouvoirJouerCarte)
						tour.releverID(joueurVirtuel);
				} else {
					tour.releverID(joueurVirtuel);
				}
			}
		} else {
			ListeJoueur listeJoueur = tour.getJoueurAccusable();

			int nbJoueurs = listeJoueur.getNbJoueur();
			Random rand = new Random();
			int idJoueurAccuse = rand.nextInt(nbJoueurs);
			Joueur joueurAccuse = listeJoueur.getListeJoueur().get(idJoueurAccuse);

			tour.accuserJoueur(joueurAccuse);
		}
		Partie.getInstance().getTourEnCours().passerTourSuivant();
	}
	/**
	 * Cette méthode permet de jouer une carte selon ses effets
	 * @param La carte à jouer
	 */
	private void jouerCarte(RumourCarte carte) {
		switch (carte.getNom()) {
		case "Angry Mob": {
			AngryMob angryMobCarte = (AngryMob) carte;
			angryMobCarte.appliquerWitchEffet(joueurVirtuel);
			break;
		}
		case "Inquisition": {
			Inquisition inquisitionCarte = (Inquisition) carte;
			inquisitionCarte.appliquerWitchEffet(joueurVirtuel, inquisitionCarte);
			break;
		}
		case "Pointed Hat": {
			PointedHat pointedHatCarte = (PointedHat) carte;
			ArrayList<RumourCarte> listeCartes = Partie.getInstance().getTable().getCartesRevelees(joueurVirtuel);

			int nbCartes = listeCartes.size();
			Random rand = new Random();
			RumourCarte carteChoisie = listeCartes.get(rand.nextInt(nbCartes));

			pointedHatCarte.appliquerWitchEffet(joueurVirtuel, carteChoisie);
			break;
		}
		case "Hooked Nose": {
			HookedNose hookedNoseCarte = (HookedNose) carte;
			ArrayList<RumourCarte> listeCartes = Partie.getInstance().getTourEnCours().getJoueurPrecedent().getMain()
					.getListeCartes();
			int nbCartes = listeCartes.size();

			if (nbCartes == 0) {
				hookedNoseCarte.appliquerWitchEffet(joueurVirtuel, null);
			} else {
				hookedNoseCarte.appliquerWitchEffet(joueurVirtuel, listeCartes.get(0));
			}

			break;
		}
		case "Broomstick": {
			BroomStick broomStickCarte = (BroomStick) carte;
			broomStickCarte.appliquerWitchEffet(joueurVirtuel);
			break;
		}
		case "Wart": {
			Wart wartCarte = (Wart) carte;
			wartCarte.appliquerWitchEffet(joueurVirtuel);
			break;
		}

		case "Ducking Stool": {
			DuckingStool duckingStoolCarte = (DuckingStool) carte;
			ArrayList<Joueur> list = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
			for (Joueur joueur : list) {
				if (joueur.getIdJoueur() != joueurVirtuel.getIdJoueur()) {
					duckingStoolCarte.appliquerWitchEffet(joueurVirtuel, joueur);
					break;
				}
			}
			break;
		}

		case "CauIdron": {
			CauIdron cauIdronCarte = (CauIdron) carte;
			cauIdronCarte.appliquerWitchEffet(joueurVirtuel);
			break;
		}
		case "Evil Eye": {
			EvilEye evilEyeCarte = (EvilEye) carte;
			ArrayList<Joueur> list = Partie.getInstance().getTourEnCours().getListeJoueurEnVie().getListeJoueur();
			for (Joueur joueur : list) {
				if (joueur.getIdJoueur() != joueurVirtuel.getIdJoueur()) {
					evilEyeCarte.appliquerWitchEffet(joueurVirtuel, joueur);
					break;
				}
			}
			break;
		}
		case "Toad": {
			Toad toadCarte = (Toad) carte;
			toadCarte.appliquerWitchEffet(joueurVirtuel);
			break;
		}
		case "Black Cat": {
			BlackCat blackCatCarte = (BlackCat) carte;
			blackCatCarte.appliquerWitchEffet(joueurVirtuel);
			break;
		}
		case "Pet Newt": {
			PetNewt petNewtCarte = (PetNewt) carte;
			petNewtCarte.appliquerWitchEffet(joueurVirtuel);
			break;
		}
		}
	}
}
