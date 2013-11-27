package com.glo4003.project.database.dto;

import java.util.List;

import com.glo4003.project.database.exception.PersistException;

import nu.xom.Element;

public interface DtoInterface {

	public void save(Element elem) throws PersistException;
	
	public Element getByID(Long id, String objectName) throws PersistException;
	
	public void delete(Element elem) throws PersistException;
	
	public void delete(Long id, String objectName) throws PersistException;
	
	public List<Element> getAll(String objectName) throws PersistException;
	
	public Long getNewId(String objectName) throws PersistException;
}
