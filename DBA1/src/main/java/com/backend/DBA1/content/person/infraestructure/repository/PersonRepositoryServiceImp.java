package com.backend.DBA1.content.person.infraestructure.repository;

import com.backend.DBA1.content.person.domain.Person;
import com.backend.DBA1.content.person.infraestructure.repository.jpa.PersonRepositoryJPA;
import com.backend.DBA1.content.person.infraestructure.repository.services.PersonRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class PersonRepositoryServiceImp implements PersonRepositoryService {
    @Autowired
    PersonRepositoryJPA personRepositoryJPA;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Person save(Person person) {
        return personRepositoryJPA.save(person);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepositoryJPA.findById(id);
    }

    @Override
    public List<Person> findByUser(String user) {
        return personRepositoryJPA.findByUser(user);
    }

    @Override
    public List<Person> findAll() {
        return personRepositoryJPA.findAll();
    }

    @Override
    public void delete(Long id) {
        personRepositoryJPA.deleteById(id);
    }

    @Override
    public List<Person> getData(HashMap<String, Object> conditions, String order) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field, value) -> {
            switch (field) {
                case "user", "name", "surname" -> predicates.add(criteriaBuilder.like(root.get(field), "%" + value + "%"));
                case "createdDate" -> {
                    String condition = (String) conditions.get("condition");

                    switch (condition) {
                        case "greaterThan" -> predicates.add(criteriaBuilder.greaterThan(root.get(field), (Date) value));
                        case "lessThan" -> predicates.add(criteriaBuilder.lessThan(root.get(field), (Date) value));
                        case "equal" -> predicates.add(criteriaBuilder.equal(root.<Date>get(field), value));
                    }
                }
            }
        });

        if ("user".equals(order)) {
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(criteriaBuilder.asc(root.get("user")));
        } else if ("name".equals(order)) {
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(criteriaBuilder.asc(root.get("name")));
        } else {
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        }

        return entityManager.createQuery(query).getResultList();
    }
}
