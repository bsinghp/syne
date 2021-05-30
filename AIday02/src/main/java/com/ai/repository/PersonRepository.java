package com.ai.repository;

import org.springframework.data.repository.CrudRepository;

import com.ai.entity.Person;

//@EnableJpaRepositories
public interface PersonRepository extends CrudRepository<Person, Integer> {

	/*
	 * Person findByName(String name);
	 * 
	 * Person findByAge(String name);
	 * 
	 * Person findByNameAndAge(String name);
	 */

	/*
	 * List<Person> findAllByName(String name); List<Person> findAllByAge(String
	 * name); List<Person> findAllByNameAndAge(String name);
	 */
}
