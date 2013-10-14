package database;

import model.ModelInterface;
import model.UserModel;
import nu.xom.Builder;
import nu.xom.Element;

import com.thoughtworks.xstream.XStream;

public class Dto {
	
	private XStream xstream;
	
	public Dto() {
		xstream = new XStream();
		bootstrap();
	}
	
	public Dto(XStream xstream) {
		this.xstream = xstream;
		bootstrap();		
	}	
	
	public nu.xom.Element toElement(ModelInterface model) throws Exception {		
		xstream.autodetectAnnotations(true);				
		nu.xom.Document doc = new Builder().build(xstream.toXML(model), null);
		Element elem = doc.getRootElement();
		elem.setLocalName(model.getClass().getSimpleName());
		
		return doc.getRootElement();
	}
	
	public ModelInterface toObject(nu.xom.Element elem) {		
		
		return (ModelInterface) xstream.fromXML(elem.toXML());
	}
	
	private void bootstrap() {
		registerModel(UserModel.class);		
	}
	
	public void registerModel(Class clazz) {
		xstream.alias(clazz.getSimpleName(), clazz);
	}
}
