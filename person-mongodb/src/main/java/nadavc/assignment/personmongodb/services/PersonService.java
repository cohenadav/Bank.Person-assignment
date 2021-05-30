package nadavc.assignment.personmongodb.services;


import nadavc.assignment.personmongodb.exceptions.ApiRequestException;
import nadavc.assignment.personmongodb.models.Person;
import nadavc.assignment.personmongodb.models.PersonsDetails;
import nadavc.assignment.personmongodb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public PersonsDetails getAllPersons() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return setPersonsList(persons);
    }


    public Person getPerson(String personId) {
        if (!isExists(personId)) {
            throw new ApiRequestException("Person with Id " + personId + " dose not found");
        }
        return getPersonChecked(personId);
    }


    public PersonsDetails getPersonsById(String[] personsId)  {
        List<Person> persons = new ArrayList<>();

        Arrays.stream(personsId).forEach(id -> {
            if (isExists(id)) {
                persons.add(getPersonChecked(id));
            }
        });

        return setPersonsList(persons);
    }


    public Person addPerson(Person newPerson) {
        if (isExists(newPerson.getId())) {
            throw new ApiRequestException("Person with Id " + newPerson.getId() + " Alreadey exists");
        }
        return savePerson(newPerson);
    }

    public Person updatePerson(Person updatedPerson) {
        if (!isExists(updatedPerson.getId()) ) {
            throw new ApiRequestException("Person with Id " + updatedPerson.getId() + " dose not exists");
        }
        return updateSavedPerson(updatedPerson);
    }


    public String deletePerson(String id) {

        if (!isExists(id)) {
            throw new ApiRequestException("personId "+ id + " wasn't found");
        }
        personRepository.deleteById(id);

        if (!isExists(id)) {
            throw new ApiRequestException("personId "+ id + " Could not be deleted, try again later");
        }
        return id;
    }


    /* -------------- private functions ----------------- */

    private boolean isExists (String personId) {
        return personRepository.existsById(personId);
    }

    //WIP - add exception handle
    private Person getPersonChecked (String personId) {
        return personRepository.findById(personId).get();
    }

    private Person savePerson(Person person) {
        return personRepository.save(person);
    }

    private Person updateSavedPerson(Person person) {
        return personRepository.save(person);
    }

    private PersonsDetails setPersonsList(List<Person> persons) {
        PersonsDetails personsList = new PersonsDetails();
        personsList.setPersonList(persons);
        return personsList;
    }

}

