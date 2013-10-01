/*
 *  project SongList
 * 
 * package com.agvinsant.lib
 * 
 * @author Adam Vinsant
 * 
 * date Oct 1, 2013
 * 
 * WebClass file
 * this form is used to find and link connections as well as bringing the response from the web api
 * 
 */
	package com.agvinsant.lib;
	
	import java.io.BufferedInputStream;
	import java.net.URL;
	import java.net.URLConnection;
	
	import android.content.Context;
	import android.net.ConnectivityManager;
	import android.net.NetworkInfo;
	import android.util.Log;
	
	public class WebClass {
		
		static Boolean connection = false;
		static String connectionType = "Unavailable";
		
		public static String getConnectionType(Context context){
			
			webInfo(context);
			return connectionType;
		}
		
		public static Boolean getConnectionStatus(Context context){
			
			webInfo(context);
			return connection;
		}
		
		private static void webInfo(Context context){
			
			
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();
			if(ni != null){
				
				if(ni.isConnected()){
					connectionType = ni.getTypeName();
					connection = true;
					
				}
			}
		}
		
		public static String getURLStringResponse(URL url){
			String response = "";
			Log.i("testing getURLString", "getURLStringResponse");
			try{
				
				URLConnection conn = url.openConnection();
				BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
				
				byte[] contentBytes = new byte[1024];
				int bytesRead = 0;
				StringBuffer responseBuffer = new StringBuffer();
				
				while((bytesRead = bin.read(contentBytes)) != -1){
					
					response = new String(contentBytes,0,bytesRead);
					responseBuffer.append(response);
				}
				Log.i("responseBuffertoString", responseBuffer.toString());
				return responseBuffer.toString();
			}
			catch(Exception e){
				
				Log.e("URL RESPONSE ERROR", "getURLStringResponse");
			}
			
			return response;
		}
	}
