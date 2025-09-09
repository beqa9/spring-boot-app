package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Person;
import ge.softlab.spring_boot_app.mappers.PersonMapper;
import ge.softlab.spring_boot_app.models.PersonModel;
import ge.softlab.spring_boot_app.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonModel> getAllPersons() {
        return personMapper.toModelList(personRepository.findByIsDeletedFalse());
    }

    @Transactional(readOnly = true)
    @Override
    public PersonModel getPersonById(Integer id) {
        Person person = personRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
        return personMapper.toModel(person);
    }

    @Transactional
    @Override
    public Person addPersonByModel(PersonModel personModel) {
        Person person = new Person();
        person.setFirstName(personModel.firstName());
        person.setLastName(personModel.lastName());
        person.setBirthDate(personModel.birthDate());
        return personRepository.save(person);
    }

    @Transactional
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

    @Transactional
    @Override
    public void deletePersonById(Integer id) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson != null) {
            existingPerson.setDeleted(true); // <-- mark as deleted, not removed
            personRepository.save(existingPerson);
        }
    }
}