package database.dto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import exceptions.PersistException;

public class FileAccess implements DtoInterface {

	private Hashtable<String, String> fileName = new Hashtable<String, String>();

	public FileAccess() {
		fileName.put("User", "user.xml");
		fileName.put("Match", "match.xml");
		fileName.put("", "association.xml");
	}

	public void save(Element elem, String objectName) throws PersistException {
		try {
			Path p = Paths.get(fileName.get(objectName));

			Element elementId = elem.getFirstChildElement("ID");

			Builder parser = new Builder();
			Document doc = null;
			if (Files.exists(p)) {

				doc = parser.build(p.toFile());

				Elements nodes = doc.getRootElement().getChildElements();

				int i = 0;
				while (i < nodes.size()) {
					if (nodes.get(i).getChild(1).getValue()
							.equals(elementId.getValue())) {
						nodes.get(i).detach();
						break;
					}
					i++;
				}
				doc.getRootElement().appendChild(elem);

				Files.delete(p);

			} else {
				Element root = new Element(objectName + "s");
				doc = new Document(root);
				root.appendChild(elem);
			}

			Files.createFile(p);

			OutputStream out = Files.newOutputStream(p);

			Serializer serializer = new Serializer(out, "ISO-8859-1");
			serializer.setIndent(4);
			serializer.setMaxLength(64);
			serializer.write(doc);
		} catch (Exception e) {
			throw new PersistException(e.getMessage());
		}
	}

	@Override
	public Element getByID(Long id, String objectName) throws PersistException {
		Path p = Paths.get(fileName.get(objectName));
		Builder parser = new Builder();
		Document doc;
		try {
			doc = parser.build(p.toFile());
		} catch (ParsingException | IOException e) {
			throw new PersistException(e.getMessage());
		}
		Nodes recherche = doc.query("//ID/" + String.valueOf(id));
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
			Path p = Paths.get(fileName.get(elem.getLocalName()));

			Element elementId = elem.getFirstChildElement("ID");

			Builder parser = new Builder();
			Document doc = parser.build(p.toFile());

				Elements nodes = doc.getRootElement().getChildElements();

				int i = 0;
				while (i < nodes.size()) {
					if (nodes.get(i).getChild(1).getValue()
							.equals(elementId.getValue())) {
						nodes.get(i).detach();
						break;
					}
					i++;
				}
				Files.delete(p);


			Files.createFile(p);

			OutputStream out = Files.newOutputStream(p);

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
		
		Path p = Paths.get(fileName.get(objectName));

		Builder parser = new Builder();
		Document doc;
		try {
			doc = parser.build(p.toFile());
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
		Path p = Paths.get(fileName.get(objectName));
		Builder parser = new Builder();
		Document doc;
		Long id = 0L;
		if(Files.exists(p))
		{
			try {
				doc = parser.build(p.toFile());
			} catch (ParsingException | IOException e) {
				throw new PersistException(e.getMessage());
			}
			Elements nodes = doc.getRootElement().getChildElements();
			
			while (id < nodes.size() && nodes.get(id.intValue()).getChild(1).getValue().equals(id))
			{
				id++;				
			}
		}
		
		return id;
	}
}
