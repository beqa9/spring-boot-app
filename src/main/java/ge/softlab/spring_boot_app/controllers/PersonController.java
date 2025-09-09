package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.Person;
import ge.softlab.spring_boot_app.models.PersonModel;
import ge.softlab.spring_boot_app.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonModel> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping
    public Person addPerson(@RequestBody PersonModel personModel) {
        return personService.addPersonByModel(personModel);
    }

    @GetMapping("/{id}")
    public PersonModel getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id);
    }

    @PutMapping("/{id}/update")
    public Person updatePerson(@PathVariable Integer id, @RequestBody PersonModel personModel) {
        return personService.updatePersonByIdAndModel(id, personModel);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePersonById(@PathVariable Integer id) {
        personService.deletePersonById(id);
        return ResponseEntity.ok("Person with ID " + id + " deleted successfully.");
    }
}