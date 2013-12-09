package test.unit.model;

import static org.junit.Assert.assertTrue;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;
import org.mockito.Mock;

import com.glo4003.project.database.exception.SaveException;
import com.glo4003.project.shoppingkart.mail.Email;

import static org.mockito.Mockito.mock;

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
	
	@Test
	public void noExceptionSendingCorrectEmail(){
		//Before
		Email mail = mock(Email.class);
		mail.setRecipient("nicolas.delaunay.1@ulaval.ca");
		
		//When
		mail.sendFromGMail();
		
		//Then
		//No exception found
	}
}
