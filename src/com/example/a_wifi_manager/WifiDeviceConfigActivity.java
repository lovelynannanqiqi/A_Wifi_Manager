package com.example.a_wifi_manager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class WifiDeviceConfigActivity extends Activity {

	EditText etWifiTitle;
	NumberPicker numberPicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifidevice_config);
		init();
		
		etWifiTitle.setText(getIntent().getStringExtra("wifiTitle"));
		numberPicker = (NumberPicker) this.findViewById(R.id.io_num);
		numberPicker.setMaxValue(23);
		numberPicker.setMinValue(0);
		numberPicker.setValue(10);

	}

	private void init() {
		etWifiTitle = (EditText) findViewById(R.id.et_wifiTitle);
	}

	public void btn_ok(View view) {

		if (etWifiTitle.getText().toString().equals("")) {

			MessageDisplay.context = this;
			MessageDisplay.AlertDialogWindow("设备名称您还没设置，设备设置程序无法完成。");
		} else {
			Message msg = MainActivity.handler.obtainMessage(1, 1, 1,
					etWifiTitle.getText().toString());
			MainActivity.handler.sendMessage(msg);
			this.finish();
		}
	}

}
