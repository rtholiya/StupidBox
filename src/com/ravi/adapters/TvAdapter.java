package com.ravi.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.ravi.common.TVLinks;
import com.ravi.common.TvLink;
import com.ravi.common.Utils;
import com.ravi.listners.ClickListner;
import com.ravi.stupidbox.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TvAdapter extends BaseAdapter {
	
	List<TvLink> tvLinks = new ArrayList<TvLink>();
	List<TvLink> originTvLinks = new ArrayList<TvLink>();
	
	Context context;
	
	
	public TvAdapter(String[] links , Context context) {
		this.context = context;
		
		this.tvLinks.clear();
		this.originTvLinks.clear();
		this.tvLinks = TVLinks.parseTvLinks(links);
		this.originTvLinks.addAll(this.tvLinks);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tvLinks.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return tvLinks.get(index);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void updateList(List<TvLink> links)
	{
		if(links.size() > 0)
		{
			//Clear the list first
			tvLinks.clear();
			originTvLinks.clear();
			tvLinks.addAll(TVLinks.parseTvLinks(TVLinks.tvLinks));
			originTvLinks.addAll(TVLinks.parseTvLinks(TVLinks.tvLinks));
			tvLinks.addAll(links);
			Collections.sort(tvLinks,new Comparator<TvLink>() {

			@Override
			public int compare(TvLink lhs, TvLink rhs) {
				// TODO Auto-generated method stub
				return lhs.getChannelName().toLowerCase().compareTo(rhs.getChannelName().toLowerCase());
			}
		});
			originTvLinks.addAll(links);
			Collections.sort(originTvLinks,new Comparator<TvLink>() {

			@Override
			public int compare(TvLink lhs, TvLink rhs) {
				// TODO Auto-generated method stub
				return lhs.getChannelName().toLowerCase().compareTo(rhs.getChannelName().toLowerCase());
			}
		});
//			notifyDataSetChanged();
		}
		
	}
	
	
	public void filter(String str)
	{
		
		str = str.toLowerCase();
		tvLinks.clear();
		if(str.length() == 0)
		{
			tvLinks.addAll(originTvLinks);
		}
		else
		{
			for(TvLink lnk : originTvLinks)
			{
				if(lnk.getChannelName().toLowerCase().contains(str))
				{
					tvLinks.add(lnk);
				}
			}
		}
		
		notifyDataSetChanged();
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(null == v)
		{
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.content_item, null);
		}
		
		TvLink tvLink = this.tvLinks.get(position);
		
		ImageView tvIcon = (ImageView)v.findViewById(R.id.channel_icon);
		tvIcon.setImageResource(R.drawable.play_icon);
		//Do something with the icon
		TextView channelName = (TextView)v.findViewById(R.id.channelName);
		channelName.setText(tvLink.getChannelName());
		
		String url = tvLink.getChannelLink();
		
		List<View> touchebles = v.getTouchables();
		for(View view : touchebles)
		{
			view.setOnClickListener(new ClickListner(url, context));
		}
		
		return v;
	}

}
