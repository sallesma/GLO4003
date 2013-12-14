package com.glo4003.project.database.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nu.xom.Element;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.filesaccess.FileAccess;
import com.glo4003.project.global.ModelInterface;
import com.glo4003.project.injection.Resolver;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public final class XmlArrayListConverter implements Converter {
	
	private XmlModelConverter dto = Resolver.getInjectedInstance(XmlModelConverter.class);

	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {		
		List<ModelInterface> models = (List<ModelInterface>) source;
		StringBuilder value = new StringBuilder();
		List<Long> ids = new ArrayList<Long>(models.size());
		
		for (ModelInterface model : models) {
	    	String name = model.getClass().getSimpleName();
	    	if (model.getId() == 0L) {
	    		try {
	    			Long id = FileAccess.getInstance().getNewId(name);
					model.setId(id);					
					
				} catch (PersistException e) {
					throw new ConversionException(e.getMessage());
				}    		
	    	} 
	    	try {
				FileAccess.getInstance().save(dto.toElement(model));
			} catch (PersistException | ConvertException e) {
				throw new ConversionException(e.getMessage());
			}
	    	value.append(model.getId() + ":" + name + ",");
	    	ids.add(model.getId());
		}
    	
        writer.setValue(value.toString());
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		List<ModelInterface> models = new ArrayList<ModelInterface>();
		String value = reader.getValue();
		
		if (!value.isEmpty()) {
			List<String> ids = Arrays.asList(value.split(","));
			for(String id : ids) {
				List<String> idAndClass = Arrays.asList(id.split(":"));
				ModelInterface model = getModel(idAndClass.get(0),idAndClass.get(1));
				models.add(model);
			}
		}
		
		return models;
	}
	
	private ModelInterface getModel(String id, String classname) {		
    	Element elem;
		try {						
			Long myId = Long.parseLong(id);
			elem = FileAccess.getInstance().getByID(myId, classname);
		} catch (PersistException e) {
			throw new ConversionException(e.getMessage());
		}  	
		ModelInterface myint = dto.toObject(elem);		
    	return myint;
	}

	public boolean canConvert(Class type) {
		return type == ArrayList.class;
	}
}