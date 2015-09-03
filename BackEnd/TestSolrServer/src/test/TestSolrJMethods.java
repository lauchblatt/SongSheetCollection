package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.xml.sax.SAXException;

import transformation.XmlToSolrTransformer;

public class TestSolrJMethods {	
	
	private static SolrClient searchableDocs;

	public static void main(String[] args) throws SolrServerException, IOException, ParserConfigurationException, SAXException {
		
		XmlToSolrTransformer toSolrTransformer = new XmlToSolrTransformer("assets/abby-xml/ubr16444_0295.xml");
		SolrInputDocument solrDoc = toSolrTransformer.createSolrDocument();
		System.out.println("end");
		
	}
	
	public static void initClient(){
		String url = "http://localhost:8983/solr/searchableDocs";
		searchableDocs = new HttpSolrClient(url);
	}
	
	public static void queryDocs() throws SolrServerException, IOException{
		SolrQuery query = new SolrQuery();
		query.setQuery("oben");
		query.setFields("textblock_1");
		query.setStart(0);
		
		QueryResponse response = searchableDocs.query(query);
	    SolrDocumentList results = response.getResults();
	    for (int i = 0; i < results.size(); ++i) {
	      System.out.println(results.get(i));
	    }
	}
	
	public static void createDocs() throws SolrServerException, IOException{
		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("textblock_1", "Wie oben erwähnt, führt eine genauere, wissenschaftliche Betrachtung zu komplexeren Definitions- und Beschreibungsversuchen", 1.0f);
		doc1.addField("xPos_1", 0, 1.0f);
		doc1.addField("yPos_1", 55, 1.0f);
		doc1.addField("width_1", 3839, 1.0f);
		doc1.addField("height_1", 333, 1.0f);
		doc1.addField("textblock_2", "Kohäsion und Kohärenz gehören zu den am weitesten akzeptierten Textualitätskriterien, aber auch hier gibt es Abweichungen", 1.0f);
		doc1.addField("xPos_2", 100, 1.0f);
		doc1.addField("yPos_2", 44, 1.0f);
		doc1.addField("width_2", 339, 1.0f);
		doc1.addField("height_2", 4938, 1.0f);
		
		SolrInputDocument doc2 = new SolrInputDocument();
		doc2.addField("textblock_1", "Die einzelnen hier angeführten Textualitätskriterien sind in ihrer Interpretation durch de Beaugrande/Dressler zum Teil umstritten.", 1.0f);
		doc2.addField("xPos_1", 0, 1.0f);
		doc2.addField("yPos_1", 55, 1.0f);
		doc2.addField("width_1", 3839, 1.0f);
		doc2.addField("height_1", 333, 1.0f);
		doc2.addField("textblock_2", "Es gibt durchaus Texte, welche aus zusammenhanglosen Worten oder gar Lauten, zum Teil auch aus bis zu bloßen Geräuschen reduzierten Klangmalereien bestehen, und die, im Ganzen dennoch vielschichtig interpretierbar, eine eigene Art von Textualität erreichen (z. B. Dada-Gedichte)", 1.0f);
		doc2.addField("xPos_2", 100, 1.0f);
		doc2.addField("yPos_2", 44, 1.0f);
		doc2.addField("width_2", 339, 1.0f);
		doc2.addField("height_2", 4938, 1.0f);
		
		 Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		 docs.add( doc1 );
		 docs.add( doc2 );
		    
		 UpdateResponse response = searchableDocs.add(docs);
		    
		 searchableDocs.commit();
	}
}
