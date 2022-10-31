package com.jaz.streams.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long socialNumber;
	String firstName;
	String lastName;
	LocalDate birthDate;
	
	
	public Period getAge() {
		return Period.between(this.birthDate,LocalDate.now());
	}
}
