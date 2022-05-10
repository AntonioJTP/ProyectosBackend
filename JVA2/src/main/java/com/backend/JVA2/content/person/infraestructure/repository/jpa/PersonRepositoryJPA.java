package com.backend.JVA2.content.person.infraestructure.repository.jpa;

import com.backend.JVA2.content.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepositoryJPA extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.user = ?1")
    List<Person> findByUser(String user);
}
