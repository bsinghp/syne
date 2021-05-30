package com.ai.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ai.entity.Person;
import com.ai.repository.PersonRepository;

@RestController
public class PersonController {
	@Autowired
	private PersonRepository repository;
	

	@RequestMapping(value = "/person/", method = RequestMethod.GET)
	public List<Person> getAllPerson() {
		return (List<Person>) repository.findAll();
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public Optional<Person> findById(@PathVariable("id") int id) {
		return repository.findById(id);
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public Person addPerson(@RequestBody Person person) {
		return repository.save(person);
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public Person updatePerson(@RequestBody Person person) {
		return repository.save(person);
	}
	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
	public String removePerson(@PathVariable("id") int id) {
		repository.deleteById(id);
		return "removed successfully";
	}

}
