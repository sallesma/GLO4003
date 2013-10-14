package database;

import java.io.IOException;

import model.ModelInterface;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public interface AbstractFileAccess {

	public void save(Element elem, String objectName) throws IOException, ValidityException, ParsingException;
	
	public Element getByID(long id, String objectName);
}
