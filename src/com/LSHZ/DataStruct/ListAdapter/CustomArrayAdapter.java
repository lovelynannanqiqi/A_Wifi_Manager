package com.LSHZ.DataStruct.ListAdapter;

import com.LSHZ.DataStruct.Wifi.WifiDevice;
import com.example.a_wifi_manager.R;
import com.example.a_wifi_manager.R.id;
import com.example.a_wifi_manager.R.layout;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter {

	private final Activity context;
	private final WifiDevice[] wifiList;

	// private final Integer[] imageIds;

	public CustomArrayAdapter(Activity context, WifiDevice[] wifilist) {
		super(context, R.layout.wifilist_layout, wifilist);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.wifiList = wifilist;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// ---print the index of the row to examine---
		Log.d("CustomArrayAdapter", String.valueOf(position));
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.wifilist_layout, null, true);

		// ---get a reference to all the views on the xml layout---
		TextView txtBSSID = (TextView) rowView.findViewById(R.id.BSSID);
		TextView txtSSID = (TextView) rowView.findViewById(R.id.SSID);
		
		txtSSID.setText(wifiList[position].SSID);
		txtBSSID.setText(wifiList[position].WifiDeviceID);
		return rowView;
	}
}
