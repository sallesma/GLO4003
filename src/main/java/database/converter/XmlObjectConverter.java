package database.converter;

import model.ModelInterface;
import nu.xom.Element;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import database.XmlModelConverter;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

public class XmlObjectConverter implements Converter {
	
	private XmlModelConverter dto = new XmlModelConverter();

    public void marshal(Object source, HierarchicalStreamWriter writer,
            MarshallingContext context) {    	
    	ModelInterface model = (ModelInterface) source;
    	String name = source.getClass().getSimpleName();
    	if (model.getId() == 0L) {
    		try {
    			Long id = FileAccess.getInstance().getNewId(name);
				model.setId(id);			
				
				FileAccess.getInstance().save(dto.toElement(model));
			} catch (PersistException | ConvertException e) {
				throw new ConversionException(e.getMessage());
			}
    	}    	
    	
        writer.setValue(model.getId().toString());
    }

    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {   	    	
    	Long id = Long.valueOf(reader.getValue());
    	Element elem;
		try {
			elem = FileAccess.getInstance().getByID(id, reader.getNodeName());
		} catch (PersistException e) {
			throw new ConversionException(e.getMessage());
		}  	
    	ModelInterface rr = dto.toObject(elem);
        return dto.toObject(elem);
    }

    @SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
    	Boolean canConvert = ModelInterface.class.isAssignableFrom(type);
        return canConvert;
    }
}
