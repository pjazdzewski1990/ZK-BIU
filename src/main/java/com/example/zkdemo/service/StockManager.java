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
		db.add(new Stock("Oracle", 1000, new BigDecimal(100)));
		db.add(new Stock("Microsoft", 1500, new BigDecimal(90)));
		db.add(new Stock("Google", 500, new BigDecimal(210)));
	}
	
	private List<Stock> db = new ArrayList<Stock>();

	public Stock get(int id){
		return db.get(id);
	}
	
	public int save(Stock _stock){
		db.add(_stock);
		return 1;
	}
	
	public List<Stock> list(){
		return db;
	}
	
}
