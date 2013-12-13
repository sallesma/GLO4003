package test.unit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.glo4003.project.global.HasWarning;

public class HasWarningTest {

	@Test
	public void NewDontHaveWarnings() {
	
		ObjectHasWarning obj = getNewHasWarning();
		
		ArrayList<String> list = obj.getWarning();
		
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void PopulatedHaveWarnings() {
		
		ObjectHasWarning obj = getPopulatedHasWarning();
	
		ArrayList<String> list = obj.getWarning();
	
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
