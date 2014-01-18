package com.ravi.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetAllSeries {
	
	Pattern seriesPattern = Pattern.compile("^http://www.tvshow7.eu/watch-(.*)-online/");
	
	public Map<String,String> getSeriesNames()
	{
		Map<String,String> tvseries = new HashMap<String, String>();
		Connection conn = Jsoup.connect("http://www.tvshow7.eu/");
		Document doc;
		
		try {
			doc = conn.get();
			Elements elements = doc.select("a[href]");
			for(Element element : elements)
			{
				String link = element.absUrl("href");
				
				Matcher seriesMatcher = seriesPattern.matcher(link);
				if(seriesMatcher.matches())
				{
//					String seriesName = seriesMatcher.group(1);
//					seriesName = seriesName.replace("-", " ");
					tvseries.put(element.html(), link);
				}
			}
				} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tvseries;
	}

}
