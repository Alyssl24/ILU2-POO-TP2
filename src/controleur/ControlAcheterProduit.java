package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public Gaulois[] donnerInfosVendeur(String produit) {
		return village.rechercherVendeursProduit(produit);
	}
	
	public int nbrProduitDisponible(String nomVendeur) {
		Etal etal = village.rechercherEtal(village.trouverHabitant(nomVendeur));
		return etal.getQuantite();
	}
	
	public int acheterProduit(String nomVendeur, int nbrAchat) {
		Etal etal = village.rechercherEtal(village.trouverHabitant(nomVendeur));
		return etal.acheterProduit(nbrAchat);
	}
	
	public boolean verifierHabitant(String nom) {
		Gaulois gaulois = village.trouverHabitant(nom);
		return gaulois != null;
	}
	
}
