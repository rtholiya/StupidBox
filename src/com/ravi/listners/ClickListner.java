package com.ravi.listners;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class ClickListner implements OnClickListener{
	
	String streamUrl;
	Context context;
	
	public ClickListner(String streamUrl,Context context)
	{
		this.streamUrl = streamUrl;
		this.context = context;
	}

	@Override
	public void onClick(View v) {

		Uri uri = Uri.parse(streamUrl);
		 Intent i = new Intent(Intent.ACTION_VIEW);
//		 i.setDataAndType(uri, "video/*");
		 i.setData(uri);
		 context.startActivity(Intent.createChooser(i, "Play Using"));
		 Toast toast = Toast.makeText(context,"Please wait for some time to laod the video." , Toast.LENGTH_SHORT);
		 toast.show();
		
	}

}
