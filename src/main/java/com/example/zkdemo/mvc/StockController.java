package com.example.zkdemo.mvc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Flash;
import org.zkoss.zul.Flashchart;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.PieModel;
import org.zkoss.zul.SimpleCategoryModel;
import org.zkoss.zul.SimplePieModel;
import org.zkoss.zul.Textbox;

import com.example.zkdemo.domain.Stock;
import com.example.zkdemo.service.StockManager;

public class StockController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	private StockManager stockManager = new StockManager();
	
	@Wire
	private Listbox stockList = new Listbox();
	
	//buyStock_*
	@Wire
	private Combobox buyStock_Combobox = new Combobox();
	@Wire
	private Textbox buyStock_Amount = new Textbox();
	@Wire
	private Button buyStock_Button = new Button();
	
	//newStock_*
	@Wire
	private Textbox newStock_Name = new Textbox();
	@Wire
	private Textbox newStock_Amount = new Textbox();
	@Wire
	private Textbox newStock_Price = new Textbox();
	
	//charts_*
	@Wire
	Flashchart charts_amount = new Flashchart();
	@Wire
	Flashchart charts_price = new Flashchart();
	
	//export_*
	
	public StockController(){ }

	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
 
        render();
    }

	private void render() {
		ListModelList<Stock> model = new ListModelList<Stock>(stockManager.list());
        //set main list 
        stockList.setModel(model);
        //set combobox 
        buyStock_Combobox.setModel(model);
        //draw charts
        PieModel pieData = new SimplePieModel();
        CategoryModel barData = new SimpleCategoryModel();
        for (Stock stock : model) {
			pieData.setValue(stock.getName(), stock.getAmount());
			barData.setValue(stock.getName(), stock.getName(), stock.getPrice());
		}
        charts_price.setModel(barData);
        charts_amount.setModel(pieData);
	}
	
	@Listen("onClick = #buyStock_Button")
	public void buyStock(){
		int ammount = Integer.parseInt(buyStock_Amount.getText());
		Stock stock = buyStock_Combobox.getSelectedItem().getValue();
		stock.setAmount(stock.getAmount()+ammount);
		
		double mod = ammount / stock.getPrice();
		stock.setPrice(stock.getPrice()-mod);
		
		stockManager.update(stock);
		
		render();
	}
	
	@Listen("onClick = #newStock_Button")
	public void newStock(){
		String name = newStock_Name.getText();
		int amount = Integer.parseInt(newStock_Amount.getText());
		double price = Double.parseDouble(newStock_Price.getText());
		
		stockManager.save(new Stock(name, amount, price));
		
		render();
	}
	
	@Listen("onClick = #export_Button")
	public void export() throws IOException{
		List<Stock> data = stockManager.list();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		//ss.getBook().write(bout);
		bout.close();
		Filedownload.save(bout.toByteArray(), 
			"application/vnd.ms-excel", "name.xls");
	}
}
