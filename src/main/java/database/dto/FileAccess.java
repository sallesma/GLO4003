package database.dto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;

import exceptions.PersistException;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import nu.xom.ValidityException;

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
	public Element getByID(long id, String objectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Element elem) throws IOException, ValidityException,
			ParsingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Element elem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id, String objectName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Element> getAll(String objectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNewId() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public Element load(String objectName, int id) { Element elem = new
	 * Element(); Path p = Paths.get(fileName.get(objectName));
	 * 
	 * return elem; }//
	 */
}
