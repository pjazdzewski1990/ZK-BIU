package com.example.zkdemo.domain;

public class Info {

	private String text;
	
	public Info() {}
	
	public Info(String _text){
		text = _text;
	}

	public String getText() {
		return text;
	}

	public void setText(String _text) {
		this.text = _text;
	}

}
