package com.example.a_wifi_manager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.LSHZ.DataStruct.ListAdapter.CustomArrayAdapter;
import com.LSHZ.DataStruct.Wifi.WifiDevice;
import com.LSHZ.UDP.ReceiveAndSend.UDP_ReceiveAndSend;
import com.LSHZ.Wifi.Control.WifiAdmin;

public class MainActivity extends Activity {

	static TextView wifiTitle;
	TextView SSID;
	ListView wifiListView;
	WifiAdmin wa;
	UDP_ReceiveAndSend udp=new UDP_ReceiveAndSend();
	
	public static Handler handler = new Handler()

	{

		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);

			switch (msg.what) {

			case 1:
				wifiTitle.setText((String) msg.obj);
				break;

			}

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wifiListView = (ListView) findViewById(R.id.wifiListView);
		

		wa = new WifiAdmin(this);
		wa.openWifi();
		displayWifiDevice();
		
		wifiListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				wifiTitle = (TextView) arg1.findViewById(R.id.wifiTitle);
				SSID = (TextView) arg1.findViewById(R.id.BSSID);
				intent_wifiConfig();
				
			
				 
			}

		});

	}

	
	private void displayWifiDevice()
	{
		List<WifiDevice> list = new ArrayList<WifiDevice>();
		wa.startScan();

		for (WifiDevice wd : wa.GetWifiDeviceList("AI-THINKER")) {
			list.add(new WifiDevice(wd.SSID, wd.WifiDeviceID));
		}

		WifiDevice[] arr = (WifiDevice[]) list.toArray(new WifiDevice[list
				.size()]);
		CustomArrayAdapter ca = new CustomArrayAdapter(this, arr);
		wifiListView.setAdapter(ca);

	}
	
	public void intent_wifiConfig() {
		//Intent i = new Intent(this, WifiDeviceConfigActivity.class);
		//if (!wifiTitle.getText().toString().equals("未设定位置"))
		//	i.putExtra("wifiTitle", wifiTitle.getText().toString());
		//startActivity(i);
		
		//for(WifiConfiguration wcf:wa.getConfiguration())
		//{
			
		//Toast.makeText(this,wcf.SSID, 2000).show();
		//}
		
		
		String ssid=SSID.getText().toString();
		WifiConfiguration wcf= wa.IsExsits(ssid);
		if(wcf!=null)
		{
			int wifi_id=wa.isWifiContected(this);
			if(wifi_id>-1)
			{
				wa.disconnectWifi(wifi_id);
			}
			wa.addNetwork(wcf);
		}
		//wa.addNetwork(SSID.getText().toString(),"", WifiAdmin.TYPE_NO_PASSWD);
		//wa.addNetwork(wa.createWifiInfo(SSID.getText().toString(),"",WifiAdmin.TYPE_NO_PASSWD));
		//udp.Send("this is android");
		
	}

	public void btnClick(View view) {

		for (WifiDevice wd : wa.GetWifiDeviceList()) {
			// et.append(wd.ssid+" "+wd.deviceid+"\n");
		}

		// et.setText("");
		// wa.startScan();
		// et.setText(wa.lookUpScan());

		// wa.addNetwork(wa.createWifiInfo("LSHZ_APP",
		// "com.lshz.junqi.123456",WifiAdmin.TYPE_WPA));

		// WifiApAdmin wifiAp = new WifiApAdmin(this);
		// wifiAp.startWifiAp("LSHZ_APP", "com.lshz.junqi.1234565");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		menu.add(Menu.NONE, Menu.FIRST + 1, 0, "扫描WIFI设备").setIcon(R.drawable.ic_launcher);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			displayWifiDevice();
			break;
		}
		return true;
	}

}
