package one.digitalinnovation.personregistrationapi.service;

import one.digitalinnovation.personregistrationapi.dto.request.PersonDTO;
import one.digitalinnovation.personregistrationapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personregistrationapi.entity.Person;
import one.digitalinnovation.personregistrationapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                //.birthDate(personDTO.getBirthDate())
                //.phones(personDTO.getPhones())
                .build();
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
