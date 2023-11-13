package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	private Village village;
	private Chef abraracourcix;
	private Gaulois asterix;
	private Gaulois obelix;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 2);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		
		asterix = new Gaulois("Astérix", 5);
		village.ajouterHabitant(asterix);

		obelix = new Gaulois("Obélix", 10);
		village.ajouterHabitant(obelix);
	}
	
	
	@Test
	void testControlPrendreEtal() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controleControlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertNotNull(controleControlPrendreEtal, "pas null");
	}

	@Test
	void testResteEtals() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.resteEtals());
		
		village.installerVendeur(asterix, "fleur", 1);
		assertTrue(controlPrendreEtal.resteEtals());
		
		village.installerVendeur(obelix, "ménir", 2);
		assertFalse(controlPrendreEtal.resteEtals());
	}

	@Test
	void testPrendreEtal() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertEquals(controlPrendreEtal.prendreEtal(obelix.getNom(), "fleur", 2), 1);
		assertEquals(controlPrendreEtal.prendreEtal(obelix.getNom(), "ménir", 2), 2);
		
		Gaulois testP = new Gaulois("testP", 2);
		village.ajouterHabitant(testP);
		assertEquals(controlPrendreEtal.prendreEtal(testP.getNom(), "rien", 3), 0);
	}

	@Test
	void testVerifierIdentite() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.verifierIdentite(asterix.getNom()));
		assertTrue(controlPrendreEtal.verifierIdentite(abraracourcix.getNom()));
		assertFalse(controlPrendreEtal.verifierIdentite("Intrus"));
	}

}
