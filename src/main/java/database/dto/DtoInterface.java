package database.dto;

import java.io.IOException;
import java.util.List;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public interface DtoInterface {

	public void save(Element elem) throws IOException, ValidityException, ParsingException;
	
	public Element getByID(long id, String objectName);
	
	public void delete(Element elem);
	
	public void delete(Long id, String objectName);
	
	public List<Element> getAll(String objectName);
	
	public Long getNewId();
}
