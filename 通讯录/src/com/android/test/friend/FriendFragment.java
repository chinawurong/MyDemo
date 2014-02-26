package com.android.test.friend;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.android.test.friend.pinyin.PinYin;

public class FriendFragment extends Fragment {

	private ListView listView;
	private BaseAdapter adapter;
	private List<Friend> friends;
	private LoaderManager manager;
	private TextView overlay;
	private View thisView;
	private ProgressDialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		thisView = inflater.inflate(R.layout.fragment_friend_list, null);
		listView = (ListView) thisView.findViewById(R.id.list);

		dialog = ProgressDialog.show(getActivity(), null, "请稍后");

		manager = getActivity().getSupportLoaderManager();
		manager.initLoader(1001, null, callbacks);

		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, PixelFormat.TRANSLUCENT);
		WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
		return thisView;
	}

	private LoaderCallbacks<Cursor> callbacks = new LoaderCallbacks<Cursor>() {
		public void onLoaderReset(Loader<Cursor> loader) {
		}

		public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
			friends = new ArrayList<Friend>();
			while (cursor.moveToNext()) {
				String phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
				String contactName = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
				Friend friend = new Friend();
				friend.setName(contactName);
				friend.setSortKey(PinYin.getPinYin(contactName));
				friend.setMobileNo(phoneNumber);
				friends.add(friend);
			}
			adapter = new myAdapter(getActivity().getLayoutInflater());
			listView.setAdapter(adapter);
			SideBar indexBar = (SideBar) thisView.findViewById(R.id.side_bar);
			indexBar.setListView(listView);
			dialog.dismiss();
		}

		public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
			CursorLoader loader = new CursorLoader(getActivity());
			loader.setUri(Phone.CONTENT_URI);
			loader.setProjection(new String[] { Phone.DISPLAY_NAME, Phone.NUMBER });
			loader.setSortOrder("sort_key COLLATE LOCALIZED asc");
			return loader;
		}
	};

	private class myAdapter extends BaseAdapter implements SectionIndexer {
		private LayoutInflater inflater;
		private Handler handler;

		public myAdapter(LayoutInflater inflater) {
			this.inflater = inflater;
			handler = new Handler();
		}

		public int getCount() {
			return friends.size();
		}

		public Object getItem(int position) {
			return friends.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.friend_item, null);
			TextView name = (TextView) convertView.findViewById(R.id.name);
			name.setText(friends.get(position).getName());
			TextView mobile = (TextView) convertView.findViewById(R.id.mobile);
			mobile.setText(friends.get(position).getMobileNo());
			return convertView;
		}

		public int getPositionForSection(int section) {
			if (section == 35) {
				return 0;
			}
			for (int i = 0; i < friends.size(); i++) {
				String sortKey = friends.get(i).getSortKey();
				char firstChar = sortKey.toUpperCase().charAt(0);
				if (firstChar == section) {
					overlay.setText(String.valueOf(firstChar));
					overlay.setVisibility(View.VISIBLE);
					handler.removeCallbacks(ExitRun);
					handler.postDelayed(ExitRun, 300);
					return i;
				}
			}
			return -1;
		}

		public int getSectionForPosition(int position) {
			return 0;
		}

		public Object[] getSections() {
			return null;
		}

		private Runnable ExitRun = new Runnable() {
			public void run() {
				overlay.setVisibility(View.GONE);
			}
		};
	}

}
