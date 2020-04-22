package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class PersonTest implements ModelTests {

    @Test
    void groupedAssertions() {
        //given
        Person person = new Person(1L, "Roy", "Roque");
        //then
        assertAll("Test Props Set",
                () -> assertEquals( "Roy", person.getFirstName()),
                () -> assertEquals("Roque", person.getLastName()));
    }

    @Test
    void groupedAssertionMsgs() {
        //given
        Person person = new Person(1L, "Roy", "Roque");
        //then
        assertAll("Test Props Set",
                () -> assertEquals("Roy", person.getFirstName(),"First Name Failed"),
                () -> assertEquals("Roque", person.getLastName(), "Last Name Failed"));
    }


}