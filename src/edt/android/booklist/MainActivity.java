package edt.android.booklist;

import edt.android.booklist.model.Book;
import edt.android.booklist.model.DataAccessFactory;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private Book selectedBook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(
					R.id.container, new BookListFragment()).commit();
		} else {
			selectedBook = (Book)savedInstanceState.getSerializable("book");
			Fragment f = getFragmentManager().findFragmentById(R.id.summaryContainer);
			if (f != null) {
				BookListFragment blf = (BookListFragment)getFragmentManager().findFragmentById(R.id.container);
				blf.mostrarResumen(selectedBook);
			}
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (selectedBook != null && getResources().getConfiguration().orientation ==
				Configuration.ORIENTATION_LANDSCAPE) {
			BookListFragment blf = (BookListFragment)getFragmentManager().findFragmentById(R.id.container);
			blf.mostrarResumen(selectedBook);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("book", selectedBook);
	}

	public static class BookListFragment extends ListFragment {
		
		private View view;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.booklist_fragment, container, false);
			ListAdapter adapter = new ArrayAdapter<Book>(
					getActivity(),
					android.R.layout.simple_list_item_1,
					DataAccessFactory.getInstance(getActivity()).getBooks());
			setListAdapter(adapter);
			return view;
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			mostrarResumen(position);
		}
		
		private void mostrarResumen(int position) {
			ListAdapter adapter = getListAdapter();
			Book book = (Book)adapter.getItem(position);
			mostrarResumen(book);
		}
		
		private void mostrarResumen(Book book) {
			((MainActivity)getActivity()).selectedBook = book;
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				Fragment f = SummaryActivity.SummaryFragment.newInstance(book);
				getFragmentManager().beginTransaction().replace(R.id.summaryContainer,
						f).commit();
			} else {
				Intent intent = new Intent(getActivity(), SummaryActivity.class);
				intent.putExtra("selected-book", book);
				startActivity(intent);
			}
		}
		
	}
}
