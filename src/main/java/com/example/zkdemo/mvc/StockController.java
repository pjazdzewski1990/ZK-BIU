package com.example.zkdemo.mvc;

import java.math.BigDecimal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
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
	
	public StockController(){  }

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
	}
	
	@Listen("onClick = #buyStock_Button")
	public void buyStock(){
		System.out.println("buyStock()");
		
		int ammount = Integer.parseInt(buyStock_Amount.getText());
		Stock stock = buyStock_Combobox.getSelectedItem().getValue();
		stock.setAmount(stock.getAmount()+ammount);
		
		BigDecimal mod = new BigDecimal(ammount).divide(stock.getPrice());
		stock.setPrice(stock.getPrice().add(mod));
		
		stockManager.update(stock);
		
		render();
	}
}
