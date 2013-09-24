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
		ObjectHasWarning obj = getNewHasWarning();
		
		assertTrue(obj.getWarning().isEmpty());
	}
	
	@Test
	public void PopulatedHaveWarnings() {
		ObjectHasWarning obj = getPopulatedHasWarning();
		
		assertFalse(obj.getWarning().isEmpty());
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
