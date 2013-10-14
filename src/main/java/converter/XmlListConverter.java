package converter;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;
import model.ModelInterface;
import model.UserModel;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import database.Dto;
import database.FileAccess;

public class XmlListConverter implements Converter {
	
	private FileAccess fileAccess = new FileAccess();	

    public void marshal(Object source, HierarchicalStreamWriter writer,
            MarshallingContext context) {
    	List<ModelInterface> models = (List<ModelInterface>) source;    	
    	
    	for(ModelInterface model: models) {
    		writer.startNode("object");
    		writer.setValue(model.getId().toString());
    		writer.endNode();
    	}        
    }

    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) { 
    	List<ModelInterface> models = new ArrayList<ModelInterface>();
    	String nodeName = reader.getNodeName();
    	String result = nodeName.substring(nodeName.lastIndexOf("<"), nodeName.lastIndexOf("<"));
    	while(reader.hasMoreChildren()) {
    		reader.moveDown();
    		Element elem = fileAccess.getByID(Long.valueOf(reader.getValue()), result);
    		Dto
    		models.add();
    	}
    	
    	long id = Long.valueOf(reader.getValue());
    	
        return fileAccess.getByID(id, reader.getNodeName());
    }

    @SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
    	Boolean canConvert = List.class.isAssignableFrom(type);
        return canConvert;
    }
}
