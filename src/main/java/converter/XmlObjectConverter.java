package converter;

import model.ModelInterface;
import nu.xom.Element;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import database.Dto;
import database.FileAccess;

public class XmlObjectConverter implements Converter {
	
	private FileAccess fileAccess = new FileAccess();	
	Dto dto = new Dto();

    public void marshal(Object source, HierarchicalStreamWriter writer,
            MarshallingContext context) {    	
    	ModelInterface model = (ModelInterface) source;
    	
        writer.setValue(model.getId().toString());
    }

    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {   	    	
    	long id = Long.valueOf(reader.getValue());
    	Element elem = fileAccess.getByID(id, reader.getNodeName());   	
    	
        return dto.toObject(elem);
    }

    @SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
    	Boolean canConvert = ModelInterface.class.isAssignableFrom(type);
        return canConvert;
    }
}
