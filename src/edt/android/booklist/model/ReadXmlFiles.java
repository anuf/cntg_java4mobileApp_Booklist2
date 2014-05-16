package edt.android.booklist.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import edt.android.booklist.R;

class ReadXmlFiles {
	
	private static final String
			BOOK_NODE				= "book",
			ISBN_ATTR				= "isbn",
			TITLE_NODE				= "title";

	private Context context;
	
	public ReadXmlFiles(Context context) {
		this.context = context;
	}
	
	private Document parseXMLFile() {
		return parseXMLResourceFile();
	}
	
	private Document parseXMLResourceFile() {
		try {
    		InputStream in = context.getResources().openRawResource(
    				R.raw.books);
    		DocumentBuilder builder = DocumentBuilderFactory.newInstance().
    				newDocumentBuilder();
    		return builder.parse(in);
		} catch (NotFoundException nfe) {
			Log.e("Parsing XML File", "Error: " + nfe.getLocalizedMessage());
		} catch (ParserConfigurationException pce) {
			Log.e("Parsing XML File", "Error: " + pce.getLocalizedMessage());
		} catch (IOException ioe) {
			Log.e("Parsing XML File", "Error: " + ioe.getLocalizedMessage());
		} catch (SAXException saxe) {
			Log.e("Parsing XML File", "Error: " + saxe.getLocalizedMessage());
		}
		return null;
	}

	List<Book> getBooks() {
		Document docProducts = parseXMLFile();
		List<Book> books = new ArrayList<Book>();
		NodeList bookNodes = docProducts.getElementsByTagName(BOOK_NODE);
		for (int i = 0; i < bookNodes.getLength(); i++) {
			Node node = bookNodes.item(i);
			String isbn = node.getAttributes().getNamedItem(ISBN_ATTR).
					getNodeValue();
			String title = null;
			String summary = null;
//			Book book = new Book(isbn, categoryName);
			NodeList nodes = node.getChildNodes();
			for (int j = 0; j < nodes.getLength(); j++) {
				Node n = nodes.item(j);
				if (n.getNodeName().equals(TITLE_NODE)) {
					title = n.getTextContent();
				} else {
					summary = n.getTextContent();
				}
			}
			books.add(new Book(isbn, title, summary));
		}
		return books;
	}

}
