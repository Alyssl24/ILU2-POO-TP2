package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		System.out.println("Quel produit voulez-vous acheter?");
		String produit = scan.next();
		Gaulois[] gauloisVendeur = controlAcheterProduit.donnerInfosVendeur(produit);
		if (gauloisVendeur == null) {
			System.out.println("Désolé, personne ne vend ce produit au marché.\n");
		} else {
			afficherListeVendeur(gauloisVendeur,produit);
			String choixVendeur = scan.next();
			String nomVendeur = gauloisVendeur[Integer.parseInt(choixVendeur)-1].getNom();
			
			StringBuilder chaine = new StringBuilder();
			chaine.append(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur + ".\n");
			chaine.append("Bonjour " + nomAcheteur + ".\n");
			chaine.append("Combien de fleurs voulez-vous acheter?\n");
			System.out.println(chaine.toString());
			String nbrAchat = scan.next();
			
			int nbrProduitDisponible = controlAcheterProduit.nbrProduitDisponible(nomVendeur);
			affichageAchatProduit(nbrProduitDisponible, Integer.valueOf(nbrAchat), produit, nomVendeur, nomAcheteur);
		}
	}
	
	private void afficherListeVendeur(Gaulois[] gauloisVendeur,String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Chez quel commerçant voulez-vous acheter des " + produit + "? \n");
		if (gauloisVendeur != null) {
			for (int i = 0; i < gauloisVendeur.length; i++) {
				chaine.append((i+1) + " - " + gauloisVendeur[i].getNom() + "\n");
			}
			System.out.println(chaine.toString());
		}
	}
	
	private void affichageAchatProduit(int nbrProduitDisponible, int nbrAchat, String produit, String nomVendeur, String nomAcheteur) {
		StringBuilder chaine = new StringBuilder();
		if (nbrProduitDisponible == 0) {
			chaine.append(nomAcheteur + " veut acheter " + nbrAchat + " " + produit + ", malheureusement il n'y en a plus!\n");
		} else if (Integer.valueOf(nbrAchat) > nbrProduitDisponible) {
			chaine.append(nomAcheteur + " veut acheter " + nbrAchat + " " + produit +  ", malheureusement " + nomVendeur 
					+ " n'en a plus que " + nbrProduitDisponible + ".\n");
			chaine.append(nomAcheteur + " achète tout le stock de " + nomVendeur + ".\n");
			int nbrProduitAcheter = controlAcheterProduit.acheterProduit(nomVendeur, Integer.valueOf(nbrAchat));
		} else {
			int nbrProduitAcheter = controlAcheterProduit.acheterProduit(nomVendeur, Integer.valueOf(nbrAchat));
			chaine.append(nomAcheteur + " achète " + nbrProduitAcheter + " " + produit + " à " + nomVendeur);
		}
		System.out.println(chaine.toString());
	}
}
