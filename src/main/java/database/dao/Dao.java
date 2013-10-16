package database.dao;

import java.util.ArrayList;
import java.util.List;

import model.ModelInterface;
import nu.xom.Element;
import database.XmlModelConverter;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

public abstract class Dao<T extends ModelInterface> implements DaoInterface<T> {

	protected final String className;

	protected XmlModelConverter converter;
	protected FileAccess fileAccess;

	public Dao(Class<? extends ModelInterface> clazz) {
		className = clazz.getSimpleName();
		converter = new XmlModelConverter();
		fileAccess = new FileAccess();
	}

	public Dao(Class<? extends ModelInterface> clazz, XmlModelConverter dto, 
			FileAccess fileAccess) {
		className = clazz.getSimpleName();
		this.converter = dto;
		this.fileAccess = fileAccess;
	}

	@Override
	public void save(T model) throws PersistException, ConvertException {
		fileAccess.save(converter.toElement(model), className);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		Element model = fileAccess.getByID(id, className);

		return (T) converter.toObject(model);
	}

	@Override
	public void delete(T model) throws ConvertException {
		Element elem = converter.toElement(model);
		fileAccess.delete(elem);
	}

	@Override
	public void deleteById(Long id) {
		fileAccess.delete(id, className);
	}
	
	@Override	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		List<T> models = new ArrayList<T>();		
		for (Element elem : fileAccess.getAll(className)) {
			models.add((T) converter.toObject(elem));
		}
		
		return models;
	}
}