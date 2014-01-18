package com.ravi.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.ravi.adapters.TvAdapter;
import com.ravi.common.TvLink;

public class UpdateTask  extends AsyncTask<TvAdapter, Void, String>{
	
	Activity activity; 
	
	public UpdateTask(Activity activity) {
		this.activity = activity;
	}
	
	public static final String stupidMailSubject = "stupidboxupdate";

	@Override
	protected String doInBackground(TvAdapter... adaptors) {
		
		final TvAdapter adaptor = adaptors[0];
		final List<TvLink> links = receiveMail();
//		adaptor.updateList(links);
		
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				adaptor.updateList(links);
				adaptor.notifyDataSetChanged();
			}
		});
		
		return links.toString();
	}
	
	
	public List<TvLink> receiveMail()
	{
		List<TvLink> links = new ArrayList<TvLink>();
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
			try {
				Session session = Session.getDefaultInstance(props, null);
				Store store = session.getStore("imaps");
				store.connect("imap.gmail.com", "stupidbox2013@gmail.com", "ravi143meenaxi");

				Folder inbox = store.getFolder("INBOX");
				inbox.open(Folder.READ_ONLY);
				Message messages[] = inbox.getMessages();
				for(Message message:messages) {
					
					if(stupidMailSubject.equalsIgnoreCase(message.getSubject()))
					{
						
						Multipart mp = (Multipart) message.getContent();
			            BodyPart bp = mp.getBodyPart(0);
			            Log.i("UpdateTask:","CONTENT:" + bp.getContent());
						
						String msg = bp.getContent().toString();
						String[] arr = msg.split(",");
						if(arr.length >  0)
						{
						for(String str : arr)
						{
							str = str.replace("\"", "");
							String[] tvs = str.split(";");
							if(tvs.length == 3)
							{
							TvLink link = new TvLink();
							link.setImg(tvs[0]);
							link.setChannelName(tvs[1]);
							link.setChannelLink(tvs[2]);
							links.add(link);
							}
						}
						}
						//Read only the first update mail
						break;
						
					}
					
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return links;
		
	}

}
