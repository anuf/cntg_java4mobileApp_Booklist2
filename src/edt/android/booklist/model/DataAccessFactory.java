package edt.android.booklist.model;

import android.content.Context;

public class DataAccessFactory {
	
	private static DataAccess dataAccess = null;
	
	public static DataAccess getInstance(Context context) {
		if (dataAccess == null)
			dataAccess = createXMLInstance(context);
		return dataAccess;
	}
	
	public static DataAccess createXMLInstance(Context context) {
		return new XmlDataAccess(context);
	}
}
