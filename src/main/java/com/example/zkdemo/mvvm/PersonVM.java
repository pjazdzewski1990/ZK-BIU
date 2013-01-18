package com.example.zkdemo.mvvm;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

import com.example.zkdemo.domain.Person;
import com.example.zkdemo.service.PersonManager;

public class PersonVM {

	private PersonManager personManager = new PersonManager();

	private String keyword;
	private List<Person> personList;
	private Person selectedPerson;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	@Command
	@NotifyChange("personList")
	public void search() {
		if (keyword != null)
			personList = personManager.findByName(keyword);
		else
			personList = personManager.findByName("");
	}

}
