package transformation;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.common.SolrInputDocument;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlToSolrTransformer {
	
	private String filepath;

	public XmlToSolrTransformer (String filepath){
		this.filepath = filepath;
	}
	
	public SolrInputDocument createSolrDocument() throws ParserConfigurationException, SAXException, IOException{
		File xmlFile = new File(filepath);
		Document xmlDoc = createXmlDocument(xmlFile);
		
		return null;
	}
	
	public Document createXmlDocument(File xmlFile) throws ParserConfigurationException, SAXException, IOException{
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document xmlDoc = dBuilder.parse(xmlFile);
		return xmlDoc;
	}
}
