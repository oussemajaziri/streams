package com.jaz.streams.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaz.streams.entities.Person;
import com.jaz.streams.services.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	@Autowired
    PersonService personService;
	
	@PostMapping("/new/person")
    public Person addPerson(@RequestBody Person person) throws IOException{
        return personService.savePerson(person);
    }
	
	
	@GetMapping("")
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }
	
	@GetMapping("/{id}")
    public Optional<Person> getPerson(@PathVariable Long id){
        return personService.getPersonById(id);
    }
	
	
	@GetMapping("/child/{age}")
    public boolean verifChildExistence(@PathVariable int age){
        List<Person> personList =  personService.getAllPersons();
        return personList.stream().anyMatch(person -> person.getAge().getYears()<age);
    }
	
	@GetMapping("/children")
    public List<Person> childrenList(){
        List<Person> personList =  personService.getAllPersons();
        return personList.stream().filter(person -> person.getAge().getYears()<18).collect(Collectors.toList());
    }
	
	@GetMapping("/children/names")
    public List<String> childrenNamesList(){
        List<Person> personList =  personService.getAllPersons();
        return personList.stream().filter(person -> person.getAge().getYears()<18).map(Person::getFirstName).collect(Collectors.toList());
    }
	
	@GetMapping("/nbr")
    public long getNumberOfPerson(){
        List<Person> personList =  personService.getAllPersons();
        return personList.stream().count();
    }
	
	
	private String namesList = "" ;

	@GetMapping("/list")
    public void getPersonsList(){
        List<Person> listPerson = personService.getAllPersons();
        LocalTime time = LocalTime.now();
        var pathFile = "C:\\Users\\ojaziri\\Desktop\\NOUVAUTES\\list"+time.getHour()+"h-"+time.getMinute()+"m-"+time.getSecond()+"s"+".txt";
        Path filePath= Paths.get(pathFile); 
        List<String> listName = listPerson.stream().map(Person::getFirstName).collect(Collectors.toList());
        try {
        	listName.forEach(nom->namesList=namesList.concat(nom)+"\n");
            Files.writeString(filePath, namesList,StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	namesList = "";
		}
    }
}
