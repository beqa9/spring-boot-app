package ge.softlab.spring_boot_app.mappers;

import ge.softlab.spring_boot_app.entities.Person;
import ge.softlab.spring_boot_app.models.PersonModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonModel toModel(Person person);

    List<PersonModel> toModelList(List<Person> persons);


}
