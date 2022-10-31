package com.jaz.streams.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.jaz.streams.entities.Person;

public interface PersonService {

	Person savePerson(Person p)throws IOException;
	Optional<Person> getPersonById(Long id);
	List<Person> getAllPersons();
}
