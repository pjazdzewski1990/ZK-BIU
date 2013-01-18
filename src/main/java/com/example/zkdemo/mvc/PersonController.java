package com.example.zkdemo.mvc;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.example.zkdemo.domain.Person;
import com.example.zkdemo.service.PersonManager;

public class PersonController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	private PersonManager personManager = new PersonManager();

	@Wire
	private Textbox keywordBox;
	@Wire
	private Listbox personListbox;
	@Wire
	private Label detailsFirstName;
	@Wire
	private Label detailsYob;

	@Listen("onClick = #searchButton")
	public void search() {
		String namePrefix = keywordBox.getValue();
		List<Person> persons = personManager.findByName(namePrefix);
		personListbox.setModel(new ListModelList<Person>(persons));
	}
	
	@Listen("onSelect = #personListbox")
	public void details(){
		Person selectedPerson = personListbox.getSelectedItem().getValue();
		detailsFirstName.setValue(selectedPerson.getFirstName());
		detailsYob.setValue("" + selectedPerson.getYob());
	}

}
