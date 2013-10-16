package database;

import java.io.IOException;

import model.ModelInterface;
import model.UserModel;
import nu.xom.Builder;
import nu.xom.Element;
import nu.xom.ParsingException;

import com.thoughtworks.xstream.XStream;

import exceptions.ConvertException;

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
			throws ConvertException {
		xstream.autodetectAnnotations(true);
		nu.xom.Document doc;

		try {
			doc = new Builder().build(xstream.toXML(model), null);
		} catch (ParsingException | IOException e) {
			throw new ConvertException(e.getMessage());
		}

		Element elem = doc.getRootElement();
		elem.setLocalName(model.getClass().getSimpleName());

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
