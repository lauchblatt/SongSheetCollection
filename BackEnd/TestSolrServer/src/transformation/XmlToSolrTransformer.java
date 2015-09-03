package transformation;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.common.SolrInputDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlToSolrTransformer {
	
	private String filepath;

	public XmlToSolrTransformer (String filepath){
		this.filepath = filepath;
	}
	
	public SolrInputDocument createSolrDocument() throws ParserConfigurationException, SAXException, IOException{
		File xmlFile = new File(filepath);
		String text = "";
		
		Document xmlDoc = createXmlDocument(xmlFile);
		NodeList textBlockList = xmlDoc.getElementsByTagName("TextBlock");
		
    	for(int i = 0; i < textBlockList.getLength(); i++){
    		Element textBlock = (Element) textBlockList.item(i);
    		
    		NodeList textLinesList = textBlock.getElementsByTagName("TextLine");
    		
    		for(int j = 0; j < textLinesList.getLength(); j++){
    			Element textLine = (Element) textLinesList.item(j);
    			
    			NodeList textStringsList = textLine.getElementsByTagName("String");
    			
    			for(int k = 0; k < textStringsList.getLength(); k++){
    				Element textString = (Element) textStringsList.item(k);
    				text = text + textString.getAttribute("CONTENT");
    			}
    			//Adding a new Line after every TextLine
    			text = text + "\n";
    		}
    		text = text + "\n";
    	}
    	
    	System.out.println(text);
		
		return null;
	}
	
	public Document createXmlDocument(File xmlFile) throws ParserConfigurationException, SAXException, IOException{
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document xmlDoc = dBuilder.parse(xmlFile);
		return xmlDoc;
	}
}
