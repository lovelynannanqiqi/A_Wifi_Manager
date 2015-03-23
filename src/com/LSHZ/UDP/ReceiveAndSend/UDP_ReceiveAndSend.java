package com.LSHZ.UDP.ReceiveAndSend;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


import android.os.AsyncTask;


public class UDP_ReceiveAndSend {
	DatagramSocket socket;
	DatagramPacket  package1;
	String udpDeviceIP;
	public UDP_ReceiveAndSend()
	{
		try {
			socket=new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	
	class sendudp extends AsyncTask<byte[],Void,Void>
    {

		@Override
		protected Void doInBackground(byte[]... arg0) {

			InetAddress broadcastAddr;
			try {
				broadcastAddr = InetAddress.getByName("192.168.4.1");
				package1=new DatagramPacket(arg0[0],arg0[0].length,broadcastAddr,8899);
	            try {
					socket.send(package1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            
  
              
			return null;
		}
    	
    }
	
	public void Send(String info)
	{
		//CharSequence str ="hello lovely nancy and qiqi!!";
        byte out[] = info.toString().getBytes();  
        
    	sendudp c=new sendudp();
    	c.execute(out);
	}
	
	public void CloseUDP()
	{
		socket.close();
	}
}
