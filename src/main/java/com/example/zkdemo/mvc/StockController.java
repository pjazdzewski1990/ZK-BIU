package com.example.zkdemo.mvc;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import com.example.zkdemo.domain.Stock;
import com.example.zkdemo.service.StockManager;

public class StockController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	private StockManager stockManager = new StockManager();
	
	@Wire
	private Listbox stockList = new Listbox();
	
	public StockController(){  }

	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
 
        stockList.setModel(new ListModelList<Stock>(stockManager.list()));
    }
}
