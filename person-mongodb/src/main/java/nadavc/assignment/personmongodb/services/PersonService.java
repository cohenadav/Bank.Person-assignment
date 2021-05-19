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


    public Person getPerson(int personId) {
        if (!isExists(personId)) {
            throw new ApiRequestException("Person with Id " + personId + " dose not found");
        }
        return getPersonChecked(personId);
    }


    public PersonsDetails getPersonsById(int[] personsId)  {
        List<Person> persons = new ArrayList<>();

        Arrays.stream(personsId).forEach(id -> {
            if (isExists(id)) {
                persons.add(getPersonChecked(id));
            }
        });

        return setPersonsList(persons);
    }


    public void addPerson(Person newPerson) {
        if (isExists(newPerson.getId())) {
            throw new ApiRequestException("Person with Id " + newPerson.getId() + " Alreadey exists");
        }
        savePerson(newPerson);
    }

    public void updatePerson(Person updatedPerson) {
        if (!isExists(updatedPerson.getId()) ) {
            throw new ApiRequestException("Person with Id " + updatedPerson.getId() + " dose not exists");
        }
        updateSavedPerson(updatedPerson);
    }


    public void deletePerson(int id) {

        if (!isExists(id)) {
            throw new ApiRequestException("personId "+ id + " wasn't found");
        }
        personRepository.deleteById(id);
    }


    private boolean isExists (int personId) {
        if (!personRepository.existsById(personId)) {
            return false;
        }
        return true;
    }




    /* -------------- private functions ----------------- */


    //WIP - add exception handle
    private Person getPersonChecked (int personId) {
        return personRepository.findById(personId).get();
    }

    private void savePerson(Person person) {
        personRepository.save(person);
    }

    private void updateSavedPerson(Person person) {
        personRepository.save(person);
    }

    private PersonsDetails setPersonsList(List<Person> persons) {
        PersonsDetails personsList = new PersonsDetails();
        personsList.setPersonList(persons);
        return personsList;
    }

}

