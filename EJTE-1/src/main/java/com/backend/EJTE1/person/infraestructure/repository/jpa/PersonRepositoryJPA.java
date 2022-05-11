package com.backend.EJTE1.person.infraestructure.repository.jpa;

import com.backend.EJTE1.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepositoryJPA extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.user = ?1")
    List<Person> findByUser(String user);
}
