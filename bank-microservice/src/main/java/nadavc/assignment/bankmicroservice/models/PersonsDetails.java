package nadavc.assignment.bankmicroservice.models;

import java.util.List;

public class PersonsDetails {
    private List<Person> personList;

    public PersonsDetails() {

    }

    public PersonsDetails(List<Person> personList) {
        this.personList = personList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
