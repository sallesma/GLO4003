package database;

import java.io.IOException;

import model.GeneralAdmissionTicketCategory;
import model.ModelInterface;
import model.ReservedTicketCategory;
import model.UserModel;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Element;
import nu.xom.ParsingException;

import com.thoughtworks.xstream.XStream;

import database.converter.XmlObjectConverter;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

public class XmlModelConverter {

	private XStream xstream;

	public XmlModelConverter() {
		xstream = new XStream();		
		bootstrap();
	}

	public XmlModelConverter(XStream xstream) {
		this.xstream = xstream;
		bootstrap();
	}

	public nu.xom.Element toElement(ModelInterface model)
			throws ConvertException, PersistException {
		String modelName = model.getClass().getSimpleName();
		if(model.getId() == 0L) {
			Long id = FileAccess.getInstance().getNewId(modelName);
			model.setId(id);
		}
		xstream.autodetectAnnotations(true);
		nu.xom.Document doc;
		try {
			doc = new Builder().build(xstream.toXML(model), null);
		} catch (ParsingException | IOException e) {
			throw new ConvertException(e.getMessage());
		}

		Element elem = doc.getRootElement();		
		elem.setLocalName(modelName);

		return doc.getRootElement();
	}

	public ModelInterface toObject(nu.xom.Element elem) {		
		String className = getClassname(elem);
		try {
			Class<?> clazz = Class.forName(className);
			registerModel(clazz);
		} catch (Exception e) {
			// TODO
		}
		String xml = elem.toXML();
		ModelInterface myModel = (ModelInterface) xstream.fromXML(elem.toXML());

		return myModel;
	}
	
	private String getClassname(Element elem) {		
		Attribute attribute = elem.getAttribute("class");
		if (attribute != null) {
			return "model." + attribute.getLocalName();
		} else {
			return "model." + elem.getLocalName();
		}
	}

	private void bootstrap() {
		registerModel(UserModel.class);
		//xstream.registerConverter(new XmlObjectConverter());
		//xstream.registerLocalConverter(ReservedTicketCategory.class, "AbstractTicketCategory", new XmlObjectConverter());
		//xstream.registerLocalConverter(GeneralAdmissionTicketCategory.class, "AbstractTicketCategory", new XmlObjectConverter());
	}

	public void registerModel(Class<?> clazz) {
		xstream.alias(clazz.getSimpleName(), clazz);
	}
}
