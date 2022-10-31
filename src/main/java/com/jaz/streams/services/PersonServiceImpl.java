package com.jaz.streams.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaz.streams.entities.Person;
import com.jaz.streams.repositories.PersonRepo;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepo personRepo;

	@Override
	public Person savePerson(Person p) throws IOException {
		personRepo.save(p);
		return p;
	}

	@Override
	public List<Person> getAllPersons() {
		return personRepo.findAll();
	}

	@Override
	public Optional<Person> getPersonById(Long id) {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			return person;
		} else {
			System.out.println("Person does not exist!");
			return person;
		}
	}


}
