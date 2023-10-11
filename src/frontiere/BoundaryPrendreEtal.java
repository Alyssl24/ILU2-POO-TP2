package frontiere;

import java.util.Scanner;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;
	private Scanner scan = new Scanner(System.in);

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		boolean nomVendeurConnu = controlPrendreEtal.verifierIdentite(nomVendeur);
		if (!nomVendeurConnu) {
			System.out.println("Je suis désolée " + nomVendeur + " mais il faut être un habitant de notre village pour commercer ici.\n");
		} else {
			System.out.println("Bonjour " + nomVendeur + ", je vais regarder si je peux vous trouver un étal.\n");
			boolean etalDisponible = controlPrendreEtal.resteEtals();
			if (!etalDisponible) {
				System.out.println("Désolée " + nomVendeur + " je n'ai plus d'étal qui ne soit pas déjà occupé.\n");
			} else {
				installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("C'est parfait, il me reste un étal pour vous!\n");
		chaine.append("Il me faudrait quelques renseignements:\n");
		chaine.append("Quel produit souhaitez-vous vendre?\n");
		System.out.println(chaine.toString());
		String produit = scan.nextLine();
		int nbProduit = Clavier.entrerEntier("Combien souhaitez-vous en vendre");
		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);
		if (numeroEtal != -1) {
			System.out.println("Le vendeur " + nomVendeur + " s'est installé à l'étal n°" + numeroEtal + ".\n");
		}
	}
}
