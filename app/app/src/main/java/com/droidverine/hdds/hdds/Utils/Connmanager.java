package com.droidverine.hdds.hdds.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by aditya on 9/8/17.
 */

public class Connmanager
{

	Context context;

	public Connmanager(Context context)
	{
		this.context = context;
	}
	
	public boolean checkNetworkConnection()
	{
		final ConnectivityManager ComMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo nwInfo = ComMgr.getActiveNetworkInfo();
		return nwInfo != null && nwInfo.isConnected();
	}
	
}
