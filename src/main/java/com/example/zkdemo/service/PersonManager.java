package com.example.zkdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.zkdemo.domain.Person;

public class PersonManager {
	
	private List<Person> db = new ArrayList<Person>();
	
	public PersonManager() {
		db.add(new Person("Jan", 1987));
		db.add(new Person("Janina", 1981));
		db.add(new Person("Jarosław", 1953));
		db.add(new Person("Kazimierz", 1921));
	}

	public void add(Person person){
		Person newPerson = new Person(person.getFirstName(), person.getYob());
		db.add(newPerson);
	}
	
	public List<Person> getAllPersons(){
		return db;
	}
	
	public List<Person> findByName(String namePrefix){
		List<Person> result = new ArrayList<Person>();
		for(Person person: db){
			if (person.getFirstName().startsWith(namePrefix)){
				result.add(person);
			}
		}
		return result;
	}

}
