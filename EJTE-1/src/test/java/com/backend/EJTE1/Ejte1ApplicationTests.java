package com.backend.EJTE1;

import com.backend.EJTE1.person.application.services.PersonService;
import com.backend.EJTE1.person.domain.Person;
import com.backend.EJTE1.person.infraestructure.controller.PersonController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import java.util.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@ContextConfiguration(classes = Ejte1Application.class)
@RunWith(SpringRunner.class)
public class Ejte1ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Before
    public void init() throws Exception {
        Person person = new Person(1L,"Synchrer", "sY26&nCP^0=", "Antonio Jose", "Tirado", "ajt@company.com",
                "ajt@outlook.com", "Jaen", false, new Date(), null, null);

        service.createPerson(person);
        when(service.findById(1L)).thenReturn(Optional.of(person));
    }

    @Test
    public void findPersonId() throws Exception {
        mockMvc.perform(get("/person/id/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.idPerson", Matchers.is(1L)))
                .andExpect((ResultMatcher) jsonPath("$.user", Matchers.is("Synchrer")))
                .andExpect((ResultMatcher) jsonPath("$.password", Matchers.is("sY26&nCP^0=")))
                .andExpect((ResultMatcher) jsonPath("$.name", Matchers.is("Antonio Jose")))
                .andExpect((ResultMatcher) jsonPath("$.surname", Matchers.is("Tirado")))
                .andExpect((ResultMatcher) jsonPath("$.companyEmail", Matchers.is("ajt@company.com")))
                .andExpect((ResultMatcher) jsonPath("$.personalEmail", Matchers.is("ajt@outlook.com")))
                .andExpect((ResultMatcher) jsonPath("$.city", Matchers.is("Jaen")))
                .andExpect((ResultMatcher) jsonPath("$.active", Matchers.is(false)))
                .andExpect((ResultMatcher) jsonPath("$.imageUrl", Matchers.is(null)));
    }

    @Test
    void findAllPerson() throws Exception {
        List<Person> people = Arrays.asList(
                new Person(1L,"Synchrer", "sY26&nCP^0=", "Antonio Jose", "Tirado", "ajt@company.com",
                        "ajt@outlook.com", "Jaen", false, new Date(), null, null),
                new Person(2L,"Synchrer", "sY26&nCP^0=", "Antonio Jose", "Tirado", "ajt@company.com",
                        "ajt@outlook.com", "Jaen", false, new Date(), null, null));

        when(service.findAll()).thenReturn(people);
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].idPerson", Matchers.is(1L)))
                .andExpect((ResultMatcher) jsonPath("$[0].user", Matchers.is("Synchrer")))
                .andExpect((ResultMatcher) jsonPath("$[0].password", Matchers.is("sY26&nCP^0=")))
                .andExpect((ResultMatcher) jsonPath("$[0].name", Matchers.is("Antonio Jose")))
                .andExpect((ResultMatcher) jsonPath("$[0].surname", Matchers.is("Tirado")))
                .andExpect((ResultMatcher) jsonPath("$[0].companyEmail", Matchers.is("ajt@company.com")))
                .andExpect((ResultMatcher) jsonPath("$[0].personalEmail", Matchers.is("ajt@outlook.com")))
                .andExpect((ResultMatcher) jsonPath("$[0].city", Matchers.is("Jaen")))
                .andExpect((ResultMatcher) jsonPath("$[0].active", Matchers.is(false)))
                .andExpect((ResultMatcher) jsonPath("$[0].imageUrl", Matchers.is(null)))
                .andExpect((ResultMatcher) jsonPath("$[0].idPerson", Matchers.is(2L)))
                .andExpect((ResultMatcher) jsonPath("$[0].user", Matchers.is("Synchrer")))
                .andExpect((ResultMatcher) jsonPath("$[0].password", Matchers.is("sY26&nCP^0=")))
                .andExpect((ResultMatcher) jsonPath("$[0].name", Matchers.is("Antonio Jose")))
                .andExpect((ResultMatcher) jsonPath("$[0].surname", Matchers.is("Tirado")))
                .andExpect((ResultMatcher) jsonPath("$[0].companyEmail", Matchers.is("ajt@company.com")))
                .andExpect((ResultMatcher) jsonPath("$[0].personalEmail", Matchers.is("ajt@outlook.com")))
                .andExpect((ResultMatcher) jsonPath("$[0].city", Matchers.is("Jaen")))
                .andExpect((ResultMatcher) jsonPath("$[0].active", Matchers.is(false)))
                .andExpect((ResultMatcher) jsonPath("$[0].imageUrl", Matchers.is(null)));

        verify(service, times(1)).findAll();
    }

    @Test
    void findPersonByUser() throws Exception {
        List<Person> people = Arrays.asList(
                new Person(1L,"Synchrer", "sY26&nCP^0=", "Antonio Jose", "Tirado", "ajt@company.com",
                        "ajt@outlook.com", "Jaen", false, new Date(), null, null),
                new Person(2L,"Synchrer", "sY26&nCP^0=", "Antonio Jose", "Tirado", "ajt@company.com",
                        "ajt@outlook.com", "Jaen", false, new Date(), null, null));

        when(service.findByUser("Synchrer")).thenReturn(people);
        mockMvc.perform(get("/person/user/Synchrer"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.user", Matchers.is("Synchrer")));
    }

    @Test
    void findAllPersonError() throws Exception {
        when(service.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(0)));
    }

    @Test
    void deletePerson() throws Exception {
        doNothing().when(service).deleteById(1L);
        mockMvc.perform(delete("/person/1"))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(1L);
    }
}
