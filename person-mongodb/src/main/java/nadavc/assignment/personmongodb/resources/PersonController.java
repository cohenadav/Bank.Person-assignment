package nadavc.assignment.personmongodb.resources;

import nadavc.assignment.personmongodb.models.Person;
import nadavc.assignment.personmongodb.models.PersonsDetails;
import nadavc.assignment.personmongodb.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/persons")
    public PersonsDetails getAllPersons() {
        return personService.getAllPersons();
    }

    @RequestMapping("/persons/{id}")
    public Person getPerson(@PathVariable int id) {
        return personService.getPerson(id);
    }

    @RequestMapping("/personslist")
    public PersonsDetails getPersonsById(@RequestBody int[] personsId) {
        return personService.getPersonsById(personsId);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/persons/add")
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/persons/update")
    public void updatePerson(@RequestBody Person person){
        personService.updatePerson(person);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/persons/remove/{id}")
    public void deletePerson(@PathVariable int id){
        personService.deletePerson(id);
    }
}
