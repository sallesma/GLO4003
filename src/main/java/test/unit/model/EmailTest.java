package test.unit.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.glo4003.project.shoppingkart.mail.Email;

public class EmailTest {
	@Test
	public void NewEmptyEmailIsEmpty() {
		
		Email mail = new Email("");

		assertTrue(mail.getContent()== null);
		assertTrue(mail.getRecipient().isEmpty());
	}
	
	@Test
	public void NewEmailIsCorrect(){
	
		Email mail = new Email("");
		
		mail.setContent("tost");
		mail.setRecipient("tast");
		
		assertTrue(mail.getContent().equalsIgnoreCase("tost"));
		assertTrue(mail.getRecipient().equalsIgnoreCase("tast"));		
	}
	
	@Test
	public void noExceptionSendingCorrectEmail(){
		
		Email mail = mock(Email.class);
		mail.setRecipient("nicolas.delaunay.1@ulaval.ca");
		
		mail.sendFromGMail();
	}
}
