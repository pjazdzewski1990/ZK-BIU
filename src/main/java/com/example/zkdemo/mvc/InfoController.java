package com.example.zkdemo.mvc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Timer;

import com.example.zkdemo.domain.Info;
import com.example.zkdemo.domain.Stock;

public class InfoController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Listbox info_list = new Listbox();
	@Wire
	private Progressmeter info_progress = new Progressmeter();
	@Wire
	private Timer info_progressTimer = new Timer(1000);
	
	private URL url;
	
	public InfoController() {
		try {
			url = new URL("http://api.nytimes.com/svc/search/v1/article?format=json&query=a&api-key=3b87d4f8c7dfbbabe3854306fa1a03f3:15:67217523");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
        
		info_progressTimer.addEventListener(Events.ON_TIMER, new EventListener<Event>() {  
	        @Override
			public void onEvent(Event evt) {  
	        	//System.out.println("tick " + info_progress.getValue());
	        	info_progress.setValue(info_progress.getValue()+1);
	        	if(info_progress.getValue() >= 100){
	        		info_progress.setValue(0);
	        		render();
	        	}
	        }
		});
		
        render();
    }
	
	private void render(){
		System.out.println("render()");
		ListModelList<Info> model = new ListModelList<Info>(parseInfo(getResponse(url)));
        //set main list 
        info_list.setModel(model);
	}
	
	private List<Info> parseInfo(String jsonText){
		List<Info> infos = new ArrayList<Info>();
		
		JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonText ); 
		JSONArray results = json.getJSONArray("results");
		for (int i=0; i<results.size(); i++) {
			JSONObject article = results.getJSONObject(i);
			infos.add(new Info(article.getString("body")+"..."));
		}
		
		Collections.shuffle(infos);
		return infos;
	}
	
	public String getResponse(URL theURL){
	    try {
	      //get the HTTP response
	      HttpURLConnection con = (HttpURLConnection)theURL.openConnection();
	      BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      String line;
	      StringBuilder response = new StringBuilder();
	      while ((line = reader.readLine()) != null) {
	          //System.out.println(line);
	          response.append(line.replaceAll("&(.*?);", " "));
	      }
	      //System.out.println("Web response is " + response);
	      return response.toString();
	    } catch( IOException e ) {
	      System.out.println( "GetResponse.GetResponse - error opening or reading URL: " + e );
	      return "";
	    }
	}
}
