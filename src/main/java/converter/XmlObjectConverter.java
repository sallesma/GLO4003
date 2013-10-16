package converter;

import model.ModelInterface;
import nu.xom.Element;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import database.XmlModelConverter;
import database.dto.FileAccess;

public class XmlObjectConverter implements Converter {
	
	private FileAccess fileAccess = new FileAccess();	
	private XmlModelConverter dto = new XmlModelConverter();

    public void marshal(Object source, HierarchicalStreamWriter writer,
            MarshallingContext context) {    	
    	ModelInterface model = (ModelInterface) source;
    	if (model.getId() == null) {
    		model.setId(fileAccess.getNewId());
    	}
    	
        writer.setValue(model.getId().toString());
    }

    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {   	    	
    	Long id = Long.valueOf(reader.getValue());
    	Element elem = fileAccess.getByID(id, reader.getNodeName());  	
    	
        return dto.toObject(elem);
    }

    @SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
    	Boolean canConvert = ModelInterface.class.isAssignableFrom(type);
        return canConvert;
    }
}
