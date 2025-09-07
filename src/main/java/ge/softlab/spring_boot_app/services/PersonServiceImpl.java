package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Person;
import ge.softlab.spring_boot_app.models.PersonModel;
import ge.softlab.spring_boot_app.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findByIsDeletedFalse();
    }

    @Override
    public Person getPersonById(Integer id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.filter(person -> !person.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }

    @Override
    public Person addPersonByModel(PersonModel personModel) {
        Person person = new Person();
        person.setFirstName(personModel.firstName());
        person.setLastName(personModel.lastName());
        person.setBirthDate(personModel.birthDate());
        return personRepository.save(person);
    }

    @Override
    public Person updatePersonByIdAndModel(Integer id, PersonModel personModel) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
        if (existingPerson != null) {
            existingPerson.setFirstName(personModel.firstName());
            existingPerson.setLastName(personModel.lastName());
            existingPerson.setBirthDate(personModel.birthDate());
            return personRepository.save(existingPerson);
        }
        return null;
    }

    @Override
    public void deletePersonById(Integer id) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson != null) {
            existingPerson.setDeleted(true); // <-- mark as deleted, not removed
            personRepository.save(existingPerson);
        }
    }
}