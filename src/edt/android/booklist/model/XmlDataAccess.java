package edt.android.booklist.model;

import java.util.List;

import android.content.Context;
import android.util.Log;

public class XmlDataAccess implements DataAccess {
	
	private List<Book> books;
	private ReadXmlFiles reader;
	
	XmlDataAccess(Context context) {
		reader = new ReadXmlFiles(context);
		if (books == null)
			books = reader.getBooks();
	}
	
	@Override
	public List<Book> getBooks() {
		Log.d("XmlDataAccess", "getCategories()");
		if (books == null) {
			Log.d("XmlDataAccess", "Parsing XML file for categories");
			books = reader.getBooks();
		}
		return books;
	}
}
