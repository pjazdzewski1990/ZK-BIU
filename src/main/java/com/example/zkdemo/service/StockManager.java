package com.example.zkdemo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.zkdemo.domain.Stock;

public class StockManager {

	/**
	 * Handles persistance for Stock
	 * TODO: for the time being we don't use DB
	 */
	public StockManager() {
		db.add(new Stock("Oracle", 1000, 100));
		db.add(new Stock("Microsoft", 1500, 90));
		db.add(new Stock("Google", 500, 210));
	}
	
	private List<Stock> db = new ArrayList<Stock>();

	public Stock get(int id){
		return db.get(id);
	}
	
	public int save(Stock _stock){
		db.add(_stock);
		return 1;//return id
	}
	
	public List<Stock> list(){
		return db;
	}
	
	public int update(Stock st){
		for(int i=0; i<db.size(); i++){
			if(db.get(i).getName().equals(st.getName())){
				db.get(i).setAmount(st.getAmount());
				db.get(i).setPrice(st.getPrice());
				return i;
			}
		}
		return -1;	//return id of updated or -1
	}
}
