package com.example.zkdemo.domain;

import java.math.BigDecimal;

public class Stock {

	public Stock(String name, long amount, BigDecimal price) {
		super();
		this.name = name;
		this.amount = amount;
		this.price = price;
	}

	public Stock() {
		super();
	}
	
	private String name = "unknown";
	private long amount = 0;
	private BigDecimal  price = new BigDecimal(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long ammount) {
		this.amount = ammount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Stock [name=" + name + ", ammount=" + amount + ", price=" + price + "]";
	}

}
