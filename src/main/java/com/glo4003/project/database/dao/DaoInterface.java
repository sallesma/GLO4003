package com.glo4003.project.database.dao;

import java.util.List;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;

public interface DaoInterface<T> {
	
	public void save(T model) throws PersistException, ConvertException ;
	
	public T getById(Long id) throws PersistException, ConvertException;
	
	public void delete(T model) throws PersistException, ConvertException;
	
	public void deleteById(Long id) throws PersistException, ConvertException;	
	
	public List<T> getAll() throws PersistException;	
}
