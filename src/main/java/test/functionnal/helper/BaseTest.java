package test.functionnal.helper;

import static org.mockito.Mockito.spy;
import database.DbHelper;

public abstract class BaseTest {
	protected DbHelper helper;	
	
	protected void initialize() {		
		helper = spy(DbHelper.getNewDb());
		helper.dropAndRepopulate();
		DbHelper.replaceDb(helper);
	}
}
