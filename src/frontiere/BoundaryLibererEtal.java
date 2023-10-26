package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		boolean vendeurReconnu = controlLibererEtal.isVendeur(nomVendeur);
		if (!vendeurReconnu) {
			System.out.println("Mais vous n'êtes pas inscrit sur notre marché aujourd'hui!\n");
		} else {
			String[] donneesEtals = controlLibererEtal.libererEtal(nomVendeur);
			if (Boolean.parseBoolean(donneesEtals[0])) {
				System.out.println("Vous avez vendu " + donneesEtals[4] + " sur " + donneesEtals[3] + " " + donneesEtals[2] + ".\n");
				System.out.println("Au revoir " + nomVendeur + ", passez une bonne journée.\n");
			}
		}
	}

}