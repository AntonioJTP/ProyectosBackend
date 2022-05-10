package com.backend.DBA2.content.person.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "person")
public class Person {

    @Id
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String city;

    @Override
    public String toString() {
        return String.format("\nPERSON: [id = %d, name = %s, surname = %s, age = %d, city = %s]", id, name, surname, age, city);
    }
}
