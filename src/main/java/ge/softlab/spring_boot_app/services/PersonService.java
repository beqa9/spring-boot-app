package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Person;
import ge.softlab.spring_boot_app.models.PersonModel;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();

    Person getPersonById(Integer id);

    Person addPersonByModel(PersonModel personModel);

    Person updatePersonByIdAndModel(Integer id, PersonModel personModel);

    void deletePersonById(Integer id);


}
