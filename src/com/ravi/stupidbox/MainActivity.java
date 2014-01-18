package com.ravi.stupidbox;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.ravi.adapters.TvAdapter;
import com.ravi.common.AdListner;
import com.ravi.common.TVLinks;
import com.ravi.task.UpdateTask;

public class MainActivity extends Activity implements TextWatcher{
	
	TvAdapter tvAdaptor;
	EditText search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Create the adaptor
		ListView list = (ListView)findViewById(R.id.contentList);
		tvAdaptor = new TvAdapter(TVLinks.tvLinks, this);
		
		search = (EditText)findViewById(R.id.search_edt);
		search.addTextChangedListener(this);
		
		list.setAdapter(tvAdaptor);
		
		new UpdateTask(this).execute(tvAdaptor);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	    View v = inflater.inflate(R.layout.activity_main, container, false);
	    AdView mAdView = (AdView) v.findViewById(R.id.ad);
	    AdRequest adRequest = new AdRequest();
	    mAdView.loadAd(adRequest);
	    mAdView.loadAd(new AdRequest());
	    mAdView.setAdListener(new AdListner(this));
	    return v;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void afterTextChanged(Editable s) {
		String txt = search.getText().toString().toLowerCase();
		tvAdaptor.filter(txt);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

}
