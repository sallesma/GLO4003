package database;


import java.io.IOException;
import java.util.Hashtable;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;



public class FileAccess implements AbstractFileAccess {
	
	private Hashtable<String, String> fileName = new Hashtable<String, String>();


	public FileAccess() {
		fileName.put("User", "user.xml");
		fileName.put("Match", "match.xml");
		fileName.put("", "association.xml");
	}

	public void save(Element elem, String objectName) throws IOException, ValidityException, ParsingException
	{
	/*	
		Path p = Paths.get(fileName.get(objectName));
		
		Element elementId = elem.getFirstChildElement("ID");
		
		Builder parser = new Builder();
		Document doc;
		if(Files.exists(p))
		{
			doc = parser.build(p.toFile());
			Elements nodes = doc.getRootElement().getChildElements();
			
			int i = 0;
			while (i < nodes.size())
			{
				if(nodes.get(i).getChild(1).getValue().equals(elementId.getValue()))
				{
					nodes.get(i).detach();
					break;
				}
				i++;
			}
			doc.getRootElement().appendChild(elem);
			
			Files.delete(p);
		}
		else
		{
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
		*/
	}

	@Override
	public Element getByID(long id, String objectName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	public Element load(String objectName, int id)
	{
		Element elem = new Element();
		Path p = Paths.get(fileName.get(objectName));
		
		return elem;
	}//*/
}
