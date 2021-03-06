package com.glo4003.project.database.converter;

import nu.xom.Element;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.filesaccess.FileAccess;
import com.glo4003.project.global.ModelInterface;
import com.google.inject.Inject;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class XmlObjectConverter implements Converter {
	
	@Inject
	private XmlModelConverter dto;

    public void marshal(Object source, HierarchicalStreamWriter writer,
            MarshallingContext context) {    	
    	ModelInterface model = (ModelInterface) source;
    	String name = source.getClass().getSimpleName();
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
    	
        writer.setValue(model.getId().toString());
    }

    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {   	    	
    	Long id = Long.valueOf(reader.getValue());
    	Element elem;
		try {			
			String classname = getClassname(reader);
			elem = FileAccess.getInstance().getByID(id, classname);
		} catch (PersistException e) {
			throw new ConversionException(e.getMessage());
		}  	    	
        return dto.toObject(elem);
    }
    
    private String getClassname(HierarchicalStreamReader reader) {
    	String attribute = "";
    	try {
    		attribute = reader.getAttribute("class");
    	} catch (IndexOutOfBoundsException e) {    		
    	}
		if (attribute != null && !attribute.isEmpty()) {
			return "model." + attribute;
		} else {
			return attribute;
		}
	}

    @SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
    	Boolean canConvert = ModelInterface.class.isAssignableFrom(type);
        return canConvert;
    }
}
