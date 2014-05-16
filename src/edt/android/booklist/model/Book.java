package edt.android.booklist.model;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isbn;
	private String title;
	private String summary;
	
	public Book(String isbn, String title, String summary) {
		this.isbn = isbn;
		this.title = title;
		this.summary = summary;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}
	
	@Override
	public String toString() {
		return isbn + " - " + title;
	}
}
