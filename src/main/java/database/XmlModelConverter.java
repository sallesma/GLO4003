package database;

import java.io.IOException;

import model.ModelInterface;
import model.UserModel;
import nu.xom.Builder;
import nu.xom.Element;
import nu.xom.ParsingException;

import com.thoughtworks.xstream.XStream;

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
		String className = "model." + elem.getLocalName();

		try {
			Class<?> clazz = Class.forName(className);
			registerModel(clazz);
		} catch (Exception e) {
			// TODO
		}

		return (ModelInterface) xstream.fromXML(elem.toXML());
	}

	private void bootstrap() {
		registerModel(UserModel.class);
	}

	public void registerModel(Class<?> clazz) {
		xstream.alias(clazz.getSimpleName(), clazz);
	}
}
