package edt.android.booklist;

import edt.android.booklist.model.Book;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
		} else {
			setContentView(R.layout.summary_activity);
			
			if (savedInstanceState == null) {
				Intent intent = getIntent();
				Book book = (Book)intent.getSerializableExtra("selected-book");
				Fragment f = SummaryFragment.newInstance(book);
				getFragmentManager().beginTransaction().add(
						R.id.container, f).commit();
			}
		}
	}

	public static class SummaryFragment extends Fragment {
		
		public static SummaryFragment newInstance(Book book) {
			SummaryFragment f = new SummaryActivity.SummaryFragment();
			Bundle args = new Bundle();
			args.putSerializable("book", book);
			f.setArguments(args);
			return f;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.summary_fragment, container, false);
			Book book = (Book)getArguments().getSerializable("book");
			TextView txtSummary = (TextView)view.findViewById(R.id.txtSummary);
			txtSummary.setText(book.getSummary());
			return view;
		}
		
	}
}
