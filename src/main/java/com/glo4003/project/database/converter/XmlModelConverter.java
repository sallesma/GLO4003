package com.glo4003.project.database.converter;

import java.io.IOException;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Element;
import nu.xom.ParsingException;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.filesaccess.FileAccess;
import com.glo4003.project.global.ModelInterface;
import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;

public class XmlModelConverter {	
	private XStream xstream;
	
	private final String path = "com.glo4003.project.database.dto.";

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
		}
		ModelInterface myModel = (ModelInterface) xstream.fromXML(elem.toXML());

		return myModel;
	}
	
	private String getClassname(Element elem) {		
		Attribute attribute = elem.getAttribute("class");
		if (attribute != null) {
			return path + attribute.getLocalName();
		} else {
			return path + elem.getLocalName();
		}
	}

	private void bootstrap() {
		xstream.autodetectAnnotations(true);		
	}

	public void registerModel(Class<?> clazz) {
		xstream.alias(clazz.getSimpleName(), clazz);
	}
}
