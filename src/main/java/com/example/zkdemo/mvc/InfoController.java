package com.example.zkdemo.mvc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import com.example.zkdemo.domain.Info;
import com.example.zkdemo.domain.Stock;

public class InfoController extends SelectorComposer<Component> {

	@Wire
	private Listbox infoList = new Listbox();
	
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
 
        render();
    }
	
	private void render(){
		ListModelList<Info> model = new ListModelList<Info>(parseInfo(getResponse(url)));
        //set main list 
        infoList.setModel(model);
	}
	
	private List<Info> parseInfo(String json){
		List<Info> result = new ArrayList<Info>();
		return result;
	}
	
	public String getResponse(URL theURL){
		HttpURLConnection con;
	    String reMessage;
	    
	    try
	    {
	      //get the HTTP response
	      con = (HttpURLConnection)theURL.openConnection();
	      reMessage = con.getResponseMessage();
	      System.out.println("Wab resposne is " + reMessage.substring(0, 25));
	      return reMessage;
	    }
	    catch( IOException e )
	    {
	      System.out.println( "GetResponse.GetResponse - error opening or reading URL: " + e );
	      return "";
	    }
	}
}
