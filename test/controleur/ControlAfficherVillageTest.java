package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private Village village;
	private Chef abraracourcix;
	private Gaulois asterix;
	private Gaulois obelix;
	private Druide panoramix;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		asterix = new Gaulois("Asterix", 5);
		obelix = new Gaulois("Obelix", 10);
		panoramix = new Druide("Panoramix",1,1,10);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		village.ajouterHabitant(panoramix);
		
	}

	@Test
	void testControlAfficherVillage() {
		ControlAfficherVillage controleAfficherVillage = new ControlAfficherVillage(village);
		assertNotNull(controleAfficherVillage, "Constructeur renvoie pas null");
	}

	@Test
	void testDonnerNomsVillageois() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		String[] donnees = controlAfficherVillage.donnerNomsVillageois();
		assertNotNull(controlAfficherVillage.donnerNomsVillageois(), "pas null");
		String[] tableauRetour = new String[4];
		tableauRetour[0] = "Abraracourcix";
		tableauRetour[1] = "Asterix";
		tableauRetour[2] = "Obelix";
		tableauRetour[3] = "le druide Panoramix";
		for (int i = 0; i < donnees.length; i++) {
			assertEquals(donnees[i], tableauRetour[i]);
		}
	}

	@Test
	void testDonnerNomVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertEquals(controlAfficherVillage.donnerNomVillage(), "le village des irréductibles");
	}

	@Test
	void testDonnerNbEtals() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertEquals(controlAfficherVillage.donnerNbEtals(), 5);
		village.installerVendeur(asterix, "fleurs", 10);
		assertEquals(controlAfficherVillage.donnerNbEtals(), 5);
	}

}
