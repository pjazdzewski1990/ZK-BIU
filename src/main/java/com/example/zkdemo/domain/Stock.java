package com.example.zkdemo.domain;

public class Stock {

	public Stock(String name, long amount, double price) {
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
	private double  price = 0;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return name;
	}

}
