package com.ravi.common;

import android.app.Activity;
import android.widget.Toast;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest.ErrorCode;

public class AdListner implements AdListener {
	
	Activity mainActivity;
	
	public AdListner(Activity mainActivity)
	{
		this.mainActivity = mainActivity;
	}

	@Override
	public void onDismissScreen(Ad arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailedToReceiveAd(Ad ad, ErrorCode errorCode) {
		Toast toast = Toast.makeText(mainActivity, "unable to receive ads", Toast.LENGTH_LONG);
		toast.show();
	}

	@Override
	public void onLeaveApplication(Ad arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPresentScreen(Ad arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceiveAd(Ad arg0) {
		// TODO Auto-generated method stub

	}

}
