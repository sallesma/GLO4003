package test.unit.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import model.BilletAdmissionGenerale;
import model.BilletCategory;
import model.BilletFactory;
import model.BilletSiegeReserve;

import org.junit.Test;
import org.mockito.Mockito;

import config.ConfigManager;

public class BilletFactoryTest {

	@Test
	public void testGetSeatedBillet() {
		 BilletCategory billet = BilletFactory.getBillet(ConfigManager.BILLET_RESERVE, Mockito.anyString(), anyInt(), anyInt(), anyFloat());
		 assertTrue(billet.getClass() == BilletSiegeReserve.class);
	}

	@Test
	public void testGetFreeBillet() {
		BilletCategory billet = BilletFactory.getBillet(ConfigManager.BILLET_LIBRE,Mockito.anyString(), anyInt(), anyInt(), anyFloat());
		assertTrue(billet.getClass() == BilletAdmissionGenerale.class);
	}
}
