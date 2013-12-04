package com.glo4003.project.database.dto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.Serializer;

import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.injection.Resolver;

public class FileAccess implements DtoInterface {	
	
	private static FileAccess fileAccess = Resolver.getInjectedInstance(FileAccess.class);	
	
	private final String path = "src/main/java/com/glo4003/project/database/files/";

	public static FileAccess getInstance() {
		return fileAccess;
	}
	
	public static void replace(FileAccess myFileAccess) {
		fileAccess = myFileAccess;
	}

	public void save(Element elem, String objectName) throws PersistException {
		try {			
			File p = new File(path + objectName + ".xml");
			
			Element elemToSave = new Element(elem);

			Element elementId = elemToSave.getFirstChildElement("id");

			Builder parser = new Builder();
			Document doc = null;
			if (p.exists()) {

				doc = parser.build(p);

				Elements nodes = doc.getRootElement().getChildElements();

				int i = 0;
				while (i < nodes.size()) {
					if (nodes.get(i).getFirstChildElement("id").getValue().equals(elementId.getValue())) {
						nodes.get(i).detach();
						break;
					}
					i++;
				}
				doc.getRootElement().appendChild(elemToSave);

				p.delete();

			} else {
				Element root = new Element(objectName + "s");
				doc = new Document(root);
				root.appendChild(elemToSave);
			}

			p.createNewFile();

			OutputStream out = new FileOutputStream(p);

			Serializer serializer = new Serializer(out, "UTF-8");
			serializer.setIndent(4);
			serializer.setMaxLength(64);
			serializer.write(doc);
		} catch (Exception e) {
			throw new PersistException(e.getMessage());
		}
	}

	@Override
	public Element getByID(Long id, String objectName) throws PersistException {
		File p = new File(path + objectName + ".xml");
		Builder parser = new Builder();
		Document doc;
		try {
			doc = parser.build(p);
		} catch (ParsingException | IOException e) {
			throw new PersistException(e.getMessage());
		}
		Nodes recherche = doc.query("//" + objectName + "[id =" + id.toString() + "]");
		Element elem =  (Element) recherche.get(0);
		
		return elem;
	}

	@Override
	public void save(Element elem) throws PersistException {
		save(elem, elem.getLocalName());
	}

	@Override
	public void delete(Element elem) throws PersistException {
		try {
			File p = new File(path + elem.getLocalName() + ".xml");

			
			Element elementId = elem.getFirstChildElement("id");

			Builder parser = new Builder();
			Document doc = parser.build(p);

				Elements nodes = doc.getRootElement().getChildElements();

				int i = 0;
				while (i < nodes.size()) {
					if (nodes.get(i).getFirstChildElement("id").getValue().equals(elementId.getValue())) {
						nodes.get(i).detach();
						break;
					}
					i++;
				}
				p.delete();


			p.createNewFile();

			OutputStream out = new FileOutputStream(p);

			Serializer serializer = new Serializer(out, "ISO-8859-1");
			serializer.setIndent(4);
			serializer.setMaxLength(64);
			serializer.write(doc);
		} catch (Exception e) {
			throw new PersistException(e.getMessage());
		}

	}

	@Override
	public void delete(Long id, String objectName) throws PersistException {
		Element elem = getByID(id, objectName);
		delete(elem);
	}

	@Override
	public List<Element> getAll(String objectName) throws PersistException {
		
		File p = new File( path + objectName + ".xml");

		Builder parser = new Builder();
		Document doc;
		try {
			doc = parser.build(p);
		} catch (ParsingException | IOException e) {
			throw new PersistException(e.getMessage());
		}

		Elements nodes = doc.getRootElement().getChildElements();
		
		
		List<Element> elements = new ArrayList<Element>(nodes.size());
		for(int i = 0; i < nodes.size(); i++) {
			elements.add(nodes.get(i));
		}
		
		return elements;
	}

	public Long getNewId(String objectName) throws PersistException {
		File p = new File(path + objectName + ".xml");
		Builder parser = new Builder();
		Document doc;
		Long max = 0L;
		if(p.exists())
		{
			try {
				doc = parser.build(p);
			} catch (ParsingException | IOException e) {
				throw new PersistException(e.getMessage());
			}
			
			
			Nodes nodes = doc.query("//id");
			
			
			if(nodes.size() == 0) {
				return ++max;
			}
			
			for(int i = 0; i < nodes.size(); i++)
			{
				if(Long.valueOf(nodes.get(i).getValue()) > max )
				{
					
					max = Long.valueOf(nodes.get(i).getValue());
				}
			}
		}
		
		return ++max;
	}
}