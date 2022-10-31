package com.jaz.streams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaz.streams.entities.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>{

}
