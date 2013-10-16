package database.dto;

import java.io.IOException;
import java.util.List;

import exceptions.PersistException;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public interface DtoInterface {

	public void save(Element elem) throws PersistException;
	
	public Element getByID(Long id, String objectName) throws PersistException;
	
	public void delete(Element elem) throws PersistException;
	
	public void delete(Long id, String objectName) throws PersistException;
	
	public List<Element> getAll(String objectName) throws PersistException;
	
	public Long getNewId(String objectName) throws PersistException;
}
