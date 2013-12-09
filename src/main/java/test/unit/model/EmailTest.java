package test.unit.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.glo4003.project.shoppingkart.mail.Email;

public class EmailTest {
	@Test
	public void NewEmptyEmailIsEmpty() {
		//When
		Email mail = new Email("");

		//Then
		assertTrue(mail.getContent()== null);
		assertTrue(mail.getRecipient().isEmpty());
	}
	
	@Test
	public void NewEmailIsCorrect(){
		//Before
		Email mail = new Email("");
		
		//When
		mail.setContent("tost");
		mail.setRecipient("tast");
		
		//Then
		assertTrue(mail.getContent().equalsIgnoreCase("tost"));
		assertTrue(mail.getRecipient().equalsIgnoreCase("tast"));		
	}
}
