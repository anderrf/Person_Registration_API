package one.digitalinnovation.personregistrationapi.service;

import one.digitalinnovation.personregistrationapi.dto.request.PersonDTO;
import one.digitalinnovation.personregistrationapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personregistrationapi.entity.Person;
import one.digitalinnovation.personregistrationapi.exception.PersonNotFoundException;
import one.digitalinnovation.personregistrationapi.mapper.PersonMapper;
import one.digitalinnovation.personregistrationapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = PersonMapper.INSTANCE.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(PersonMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return PersonMapper.INSTANCE.toDTO(person);
    }
}
