package test.unit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import model.HasWarning;

import org.junit.Test;

public class HasWarningTest {

	@Test
	public void NewDontHaveWarnings() {
		//Before
		ObjectHasWarning obj = getNewHasWarning();
		
		//When
		ArrayList<String> list = obj.getWarning();
		
		//Then
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void PopulatedHaveWarnings() {
		//Before
		ObjectHasWarning obj = getPopulatedHasWarning();
		
		//When
		ArrayList<String> list = obj.getWarning();
		
		//Then
		assertFalse(list.isEmpty());
	}
	
	private ObjectHasWarning getPopulatedHasWarning() {
		ObjectHasWarning model = new ObjectHasWarning();
		List<String> list = new ArrayList<String>();
		list.add("test");
    	model.addWarning(list);
    	
    	return model;
    }
    
    private ObjectHasWarning getNewHasWarning() {
    	
    	return new ObjectHasWarning();    	
    }
    
    private class ObjectHasWarning extends HasWarning {    	
    }
}
