package com.example.a_wifi_manager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class MessageDisplay {

	public static Context context;
	
	public static void AlertDialogWindow(String info)
	{
		 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		 dialog.setCancelable(false);
		 dialog.setTitle("系统提示");
		 dialog.setMessage(info);
		 dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
		 
		 dialog.show();
	}
}
